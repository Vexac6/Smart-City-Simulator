package sdp.progetto.classi.thread;

import com.google.protobuf.GeneratedMessageV3;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import sdp.progetto.classi.nodo.NodePeer;
import sdp.progetto.classi.WaitingQueue;
import sdp.progetto.grpc.*;

import java.util.concurrent.TimeUnit;

/**
 * Si occupa di gestire la coda di messaggi da spedire al prossimo nodo nella rete. Fintanto
 * che la coda è vuota, resta in attesa di nuovi messaggi o dello spegnimento del nodo.
 **/
public class QueueManager extends Thread {

    /** Canale di comunicazione verso il nodo next **/
    private ManagedChannel channel = null;

    /** Il thread che gestisce il token è figlio del QueueManager **/
    private final TokenManager tokenManager;

    /** Riferimento al Peer padre **/
    private final NodePeer nodo;

    /** La coda di messaggi da gestire **/
    private final WaitingQueue queue;

    /** Mutex esplicito per sincronizzare le operazioni critiche sul channel **/
    private final Object channelLock;

    /** Shortcut per avere a portata l'ID del nodo **/
    private final int nodeID;

    /** Quando è falso, il processo esce dal main loop **/
    private volatile boolean running;

    /** Se vero, ignora tutte le procedure di spedire messaggi, essendo l'ultimo rimasto ad uscire **/
    private volatile boolean lastNodeInQuartiere;

    /** Unico costruttore richiesto **/
    public QueueManager(NodePeer nodo, WaitingQueue queue, int nodeID) {
        this.queue = queue;
        this.nodo = nodo;
        this.nodeID = nodeID;
        channelLock = new Object();
        tokenManager = new TokenManager(this);
        running = true;
        start();
    }

    /** Consente a un LeavePropagator di cambiare il next **/
    public void setNodeNext(int id) {
        nodo.setNext(id);
    }

    /** Consente al Token Manager di utilizzare lo stesso canale per le grpc **/
    public ManagedChannel getChannel() { synchronized (channelLock) {return channel;} }

    /** Consente di cambiare il canale di connessione direttamente dal thread che chiama la grpc **/
    public void newChannelTarget(int id) {
        if (!nodo.getQuartiere().containsKey(id)) // Può accadere (in casi rari) di non conoscere la casa next
            newChannelTarget(nodo.getQuartiere().nextHouse(nodeID).targetAddress()); // Forwarding Automatico
        else newChannelTarget(nodo.getQuartiere().get(id).targetAddress());
    }

    /** Cambia il canale di connessione con uno nuovo (quando il next node cambia o esce) **/
    public void newChannelTarget(String newTarget) {
        synchronized (channelLock) {
            if (!running || (channel != null && channel.authority().equals(newTarget)))
                return; // Non cambia next se è in fase di spegnimento o se è già quello il suo next
            closeChannel();
            channel = ManagedChannelBuilder.forTarget(newTarget).usePlaintext().build();
        }
    }

    /** Chiude il canale di comunicazione **/
    private void closeChannel() {
        if (channel != null)
            try { channel.shutdown().awaitTermination(5, TimeUnit.SECONDS); }
            catch (InterruptedException e) {e.printStackTrace();}
    }

    /** Cambia il canale di connessione con uno nuovo (quando il next node cambia o esce) **/
    public void stopManager(boolean lastNodeInQuartiere) {
        this.lastNodeInQuartiere = lastNodeInQuartiere;
        running = false;
        if (getState() == State.WAITING)
            synchronized (queue) { queue.notify(); }
    }

    /** Ogniqualvolta la queue ha un messaggio, il manager lo spedisce al next **/
    @Override
    public void run() {
        while(running) {
            if (queue.hasMessage()) { // Check in caso di spegnimento del nodo senza messaggi in coda
                    dispatch(queue.poll()); // Solo il check è sync, ma non è un problema per la consistenza
            }
        }
        // Quando viene terminato running
        if (!lastNodeInQuartiere) { // Dispatcha i messaggi solo se ci sono altri nodi a cui mandarli
            int messagesInQueue = queue.size();
            for (int i = 0; i < messagesInQueue; i++)
                dispatch(queue.poll());
        }
        tokenManager.stopTokenManager();
        try {tokenManager.join();}
        catch (InterruptedException e) {e.printStackTrace();}
        closeChannel();
        //System.out.println("Manager spento");
    }

    /** Controlla il tipo di messaggio in testa alla coda e lo assegna a un propagator thread **/
    private void dispatch(GeneratedMessageV3 message) {
        if (message.getDescriptorForType() == JoinData.getDescriptor())
            new JoinPropagator(this, (JoinData) message);
        else if (message.getDescriptorForType() == LeaveData.getDescriptor())
            new LeavePropagator(this, (LeaveData) message);
        else if (message.getDescriptorForType() == UpdateData.getDescriptor())
            new UpdatePropagator(this, (UpdateData) message);
        else if (message.getDescriptorForType() == ElectionData.getDescriptor())
            new ElectionPropagator(this, (ElectionData) message);
        else if (message.getDescriptorForType() == TokenData.getDescriptor())
            tokenManager.acquireToken((TokenData) message);
        else {
            System.err.println("Messaggio di rete non riconosciuto");
            System.exit(-1);
        }
    }
}
