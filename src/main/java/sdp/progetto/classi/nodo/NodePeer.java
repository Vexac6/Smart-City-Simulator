package sdp.progetto.classi.nodo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import sdp.progetto.classi.Casa;
import sdp.progetto.classi.Quartiere;
import sdp.progetto.classi.RegistrationResponse;
import sdp.progetto.classi.WaitingQueue;
import sdp.progetto.classi.thread.QueueManager;
import sdp.progetto.grpc.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/** Il modulo del Nodo che si occupa di interagire con la rete P2P **/
public class NodePeer {

    private final Nodo nodo; // Il nodo a cui questa classe fa riferimento
    private final Quartiere quartiere; // L'insieme delle altre Case nel network
    private int next; // Prossimo nodo a cui inviare i messaggi di rete
    private final int seniority; // Determina l'anzianità del nodo, serve a eleggere il founder
    private boolean isFounder; // Determina se il nodo è l'attuale founder della rete

    private final QueueManager manager; // Gestisce la propagazione dei messaggi multi-threaded
    private final WaitingQueue queue; // Coda di messaggi che il manager spedirà al nodo successivo
    private Timer timer; // In caso il nodo fosse il founder, gestisce gli Update
    private final Server server; // Ogni peer è sia client che server

    // Controllano se ci sono determinati messaggi in circolo, per evitare inconsistenze
    private volatile boolean leaveAround, updateAround, electionAround;
    // Si occupano di bloccare il peer in attesa che i messaggi in circolo tornino al mittente
    private final Object leaveLock, updateLock, electionLock;

    // ----- Getters & Setters ----- //
    public int getNext() { return next; }
    public void setNext(int id) { next = id; } // Lo invoca il manager a seguito della Leave del proprio next
    public int getSeniority() { return seniority; }
    public synchronized Quartiere getQuartiere() { return quartiere; } //TODO
    public WaitingQueue getQueue() {return queue;}
    public boolean isFounder() {return isFounder;}
    // ----------------------------- //

    /** Cambia il next e crea un nuovo canale di comunicazione col nuovo next **/
    private void changeNext(int id) {
        next = id;
        manager.newChannelTarget(next);
    }

    /** Cambia il next e crea un nuovo canale di comunicazione col nuovo next **/
    private void changeNext(int id, String emergencyAddress) {
        next = id;
        manager.newChannelTarget(emergencyAddress);
    }

    /** Inizializza tutti gli elementi di rete e li accende **/
    public NodePeer(Nodo node, RegistrationResponse response) {
        nodo = node;
        next = nodo.getId();
        seniority = response.getSeniority();
        quartiere = response.getQuartiere();
        queue = new WaitingQueue();
        manager = new QueueManager(this, queue, nodo.getId());
        server = ServerBuilder.forPort(nodo.getPort()).addService(new RingNetworkGrpcImpl()).build();
        try {
            server.start();
            //addHook();
        } catch (IOException e) {e.printStackTrace();}
        leaveAround = false;
        updateAround = false;
        electionAround = false;
        leaveLock = new Object();
        updateLock = new Object();
        electionLock = new Object();
    }

    /** Consente di spegnere il server in caso di spegnimento improvviso del programma (e di bug) **/
    private void addHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                //System.err.println("Spegnimento del server -> La JVM è stata spenta");
                cleanStop();
                //System.err.println("Server spento");
            }
        });
    }

    /**
     * Inserisce il nodo nel network ad anello.<br>
     * Se il nodo è il founder della rete, genera il token e avvia gli update,
     * altrimenti manda la Join agli altri membri del quartiere
     **/
    public void signInNetwork() {
        if (seniority == 1) { // Il nodo con seniority 1 è considerato il founder
            founderDuties();
            generateToken();
        }
        else {
            isFounder = false;
            changeNext(quartiere.nextID(nodo.getId()));
            JoinData myData = JoinData.newBuilder() // Lancia la propria Join
                    .setSender(nodo.getId())
                    .setNewcomer(HouseData.newBuilder(nodo.serialize()))
                    .build();
            queue.addMessage(myData); // Spedisce la Join al prossimo nodo
        }
    }

    /**
     * Abbandona il network ad anello.<br>
     * Prima di uscire, attende che non ci siano update in circolazione e
     * che la sua Leave sia tornata indietro
     **/
    public void leaveNetwork() {
        leaveAround = true;
        synchronized (updateLock) { // Aspetta che l'update sia tornato indietro, se c'è (non ne può generare altri)
            while (updateAround) {
                try {updateLock.wait(); updateAround = false;}
                catch (InterruptedException e) {e.printStackTrace();}
            }
        }
        LeaveData myData = LeaveData.newBuilder()
                .setSender(nodo.getId())
                .setNext(next)
                .setEmergencyAddress(nodo.targetAddress())
                .build();
        queue.addMessage(myData);
        synchronized (leaveLock) { // Attende il ritorno della sua leave prima di uscire
            while (leaveAround) {
                try {leaveLock.wait(); leaveAround = false;}
                catch (InterruptedException e) {e.printStackTrace();}
            }
        }
    }

    /** Quando un nodo diventa il fondatore, avvia un thread che ciclicamente crea degli Update **/
    private void startUpdaterThread(final long timespan) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateAround = true; // Il founder non può abbandonare durante un update
                UpdateData message = UpdateData.newBuilder()
                                .setSender(nodo.getId())
                                .addAllHouses(quartiere.serialize())
                                .build();
                queue.addMessage(message);
            }
        }, timespan, timespan);
    }

    /** Se un fondatore vuole lasciare, spegne il timer degli update **/
    public void stopUpdates() {
        // Nel caso ci sia un update in circolo, aspetta il suo ritorno
        synchronized (updateLock) {
            while (updateAround) {
                try { updateLock.wait(); updateAround = false; }
                catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
        timer.cancel();
    }

    /** Prima di abbandonare la rete, il fondatore avvia un'elezione per trovare un nuovo fondatore **/
    public void startElection() {
        electionAround = true;
        ElectionData election = ElectionData.newBuilder()
                .setSender(nodo.getId())
                .setElected(false)
                .setSeniority(Integer.MAX_VALUE)
                .build();
        queue.addMessage(election);
        synchronized (electionLock) {
            while (electionAround) {
                try {electionLock.wait(); electionAround = false;}
                catch (InterruptedException e) {e.printStackTrace();}
            }
        }
    }

    /** Avvia il thread che si occupa degli update **/
    private void founderDuties() {
        isFounder = true; // Diventa il founder
        if (next == nodo.getId()) // Se è next di sè stesso è perchè è l'unico nodo nella rete
            changeNext(nodo.getId()); // Crea un canale di comunicazione con sè stesso
        timer = new Timer();
        startUpdaterThread(60000); // Lancia un update ogni minuto
    }

    /** Genera il token per la prima volta **/
    private void generateToken() {
        TokenData token = TokenData.newBuilder().build();
        queue.addMessage(token);
    }

    /** Spegne il Peer e tutti i sottomoduli **/
    public void cleanStop() {
        if (server != null) {
            try {
                server.shutdown();
                server.awaitTermination();
            } catch (InterruptedException e) { e.printStackTrace(); }
            finally { server.shutdownNow();}
        }
        manager.stopManager(quartiere.members() == 1); // Se è l'ultimo membro, spegne direttamente
        System.out.println("Nodo spento");
    }

    /**------------------------------------------------------------------------------------------------------------
    ** ----------------------------------------  CLASSE PER LE GRPC  ----------------------------------------------
    ** ------------------------------------------------------------------------------------------------------------ */

    private class RingNetworkGrpcImpl extends RingNetworkGrpc.RingNetworkImplBase {

        /**
         * Aggiorna il proprio quartiere aggiungendo il nodo contenuto nel messaggio,
         * cambia il next se necessario, infine inoltra la join al prossimo nodo.
         **/
        @Override
        public void join(JoinData request, StreamObserver<ACK> responseObserver) {
            //System.out.println("Ricevuta Join"+request.getSender());
            if (nodo.getId() != request.getSender()) { // Se sono il mittente, termino la catena
                checkJoin(request); // Aggiorno tutte le informazioni del caso
                queue.addMessage(request); // Propago il messaggio al prossimo nodo
            }
            responseObserver.onNext(ACK.newBuilder().setOK(true).build());
            responseObserver.onCompleted();
        }

        /**
         * Aggiorna il proprio quartiere rimuovendo il nodo contenuto nel messaggio,
         * cambia il next se necessario, infine inoltra la leave al prossimo nodo.
         **/
        @Override
        public void leave(LeaveData request, StreamObserver<ACK> responseObserver) {
            //System.out.println("Ricevuta Leave"+request.getSender());
            if (nodo.getId() == request.getSender()) {
                //System.out.println("Catena di Leave terminata. Spegnimento...");
                // Segnala al client di cambiare next, indicandogli il prossimo valido
                responseObserver.onNext(ACK.newBuilder().setOK(false).setNextNodeID(next).build());
                synchronized (leaveLock) {leaveAround = false; leaveLock.notify();}
            }
            else {
                checkLeave(request); // Aggiorna tutte le informazioni del caso
                queue.addMessage(request);
                responseObserver.onNext(ACK.newBuilder().setOK(true).build());
            }
            responseObserver.onCompleted();
        }

        @Override
        public void update(UpdateData request, StreamObserver<ACK> responseObserver) {
            //System.out.println("Ricevuta Update");
            if (nodo.getId() == request.getSender())
                synchronized (updateLock) {updateAround = false; updateLock.notify();}
            else {
                checkUpdate(request); // Aggiorna tutte le informazioni del caso
                queue.addMessage(request); // Propaga il messaggio al prossimo nodo
            }
            responseObserver.onNext(ACK.newBuilder().setOK(true).build());
            responseObserver.onCompleted();
        }

        @Override
        public void election(ElectionData request, StreamObserver<ACK> responseObserver) {
            //System.out.println("Ricevuta Election -> Seniority: "+seniority());
            electionAround = true; // Chi riceve il messaggio sa che c'è un elezione in corso
            if (nodo.getId() == request.getSender()) {
                if (request.getElected()) {
                    //System.out.println("Election terminata -> Nuovo Founder: "+seniority());
                    synchronized (electionLock) {electionAround = false; electionLock.notify();}
                }
                else queue.addMessage(request.toBuilder().setElected(true).build()); // Propaga, ma annunciando un eletto
            }
            else checkElection(request); // Controlla l'elezione e inoltra il messaggio
            responseObserver.onNext(ACK.newBuilder().setOK(true).build());
            responseObserver.onCompleted();
        }

        @Override
        public void passToken(TokenData request, StreamObserver<ACK> responseObserver) {
            //System.out.println("Token destinato a "+ next);
            if (checkFullToken(request)) {
                //System.out.println("Token pieno! Posto statistiche");
                nodo.getClient().postStatistics(request);
                queue.addMessage(request.toBuilder().clearStats().build());
            }
            else if (nodo.getSurveyor().hasLocalAverage()) {
                //System.out.println("Token Update");
                TokenData newToken = nodo.getSurveyor().fillToken(request);
                queue.addMessage(newToken);
            }
            else queue.addMessage(request);

            responseObserver.onNext(ACK.newBuilder().setOK(true).build());
            responseObserver.onCompleted();
        }

        /**
         * Aggiorna il quartiere aggiungendo il nuovo nodo entrato, eventualmente cambia
         * il next e avvia il token non appena entra il secondo nodo<br>
         * • thread-safe •
         **/
        private void checkJoin(JoinData request) {
            synchronized (quartiere) {
                if (!quartiere.containsKey(request.getSender())) {
                    //System.out.println("Aggiorno quartiere");
                    quartiere.putHouse(new Casa(request.getNewcomer()));
                }

                if (quartiere.nextID(nodo.getId()) == request.getSender())
                    changeNext(request.getSender());

                //if (quartiere.members() == 2)
                    //generateToken();
            }
        }

        /**
         * Aggiorna il quartiere rimuovendo il nodo uscente<br>
         * • thread-safe •
         **/
        private void checkLeave(LeaveData request) {
            synchronized (quartiere) {
                if (quartiere.containsKey(request.getSender()))
                    quartiere.removeHouse(request.getSender());
                else // Se non ho mai conosciuto il nodo che sta lasciando, potrebbe essere il mio next!
                    if (nodo.getId() < request.getSender() && request.getSender() < next) //
                        changeNext(request.getSender(), request.getEmergencyAddress());
            }
        }

        /**
         * Aggiorna il quartiere se necessario con le informazioni provenienti dal founder<br>
         * • thread-safe •
         **/
        private void checkUpdate(UpdateData request) {
            synchronized (quartiere) {
                Quartiere correct = new Quartiere(request.getHousesList());
                if (!quartiere.sameAs(correct)) {
                    quartiere.addMissing(correct);
                    quartiere.removeExcess(correct);
                    if (next != quartiere.nextID(nodo.getId())) // In caso di doppio inserimento sullo stesso punto
                        changeNext(quartiere.nextID(nodo.getId()));
                }
            }
        }

        /** Aggiorna il messaggio di elezione o controlla se è diventato il nuovo founder **/
        private void checkElection(ElectionData request) {
            if (leaveAround) queue.addMessage(request); // Se il nodo sta abbandonando, non controlla neanche l'elezione
            else if (request.getElected()) {
                electionAround = false;
                if (seniority == request.getSeniority())
                    founderDuties();
                queue.addMessage(request);
            }
            else // Se è la prima volta che leggo questa elezione
                if (seniority < request.getSeniority()) // Se sono più degno rispetto a quello attuale
                    queue.addMessage(request.toBuilder().setSeniority(seniority).build());
                else queue.addMessage(request); // Passa avanti senza modificare
        }

        /** Controlla se il token è pieno di statistiche o manca qualcuno **/
        private boolean checkFullToken(TokenData token) {
            return quartiere.members() > 1 && token.getStatsMap().keySet().containsAll(quartiere.keySet());
            // Caso particolare: il token contiene più rilevazioni che case nel nostro attuale quartiere
            // -> Si considera come pieno, per il quartiere ci penserà l'update fra un po'
        }
    }
}
