package sdp.progetto.classi.thread;

import com.google.protobuf.GeneratedMessageV3;

/** Un thread che si occupa semplicemente di inoltrare un messaggio **/
public abstract class Propagator extends Thread {

    /** Il manager che provvede a fornire il canale di comunicazione **/
    protected final QueueManager manager;

    /** Il messaggio di rete da propagare **/
    protected final GeneratedMessageV3 message;

    /** Costruttore generico **/
    public Propagator(QueueManager manager, GeneratedMessageV3 message)
    {
        this.manager = manager;
        this.message = message;
        start();
    }

    @Override
    public void run() {
        propagate();
    }

    /**
     * Abstract: La funzione deve invocare la grpc adatta a trasmettere un
     * messaggio di rete di un certo tipo al nodo successivo
     **/
    protected abstract void propagate();
}
