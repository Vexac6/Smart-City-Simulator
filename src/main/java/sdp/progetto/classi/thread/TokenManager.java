package sdp.progetto.classi.thread;

import io.grpc.stub.StreamObserver;
import sdp.progetto.grpc.ACK;
import sdp.progetto.grpc.RingNetworkGrpc;
import sdp.progetto.grpc.TokenData;

/**
 * Thread che si occupa di inoltrare il token al next, per poi
 * mettersi in attesa che il token ritorni al nodo dopo un circolo
 * **/
public class TokenManager extends Thread {

    /** Il manager che manovra gli stati di questo thread **/
    private final QueueManager queueManager;

    /** Contiene le informazioni dell'ultimo token ricevuto **/
    private TokenData token;

    /** Quando è falso, il processo esce dal main loop **/
    private volatile boolean running;

    /** Vero quando c'è un token da passare al nodo successivo **/
    private volatile boolean hasToken;

    /** Costruttore classico **/
    public TokenManager(QueueManager queueManager) {
        this.queueManager = queueManager;
        running = true;
        hasToken = false;
        start();
    }

    @Override
    public void run() {
        while (running) {
            if (hasToken)
                passToken();
        }
        passTokenLastTime();
    }

    /** Consente di spegnere il thread dall'esterno **/
    public synchronized void stopTokenManager() {
        running = false;
        if (getState() == State.WAITING)
            notify();
    }

    /** Il manager invoca questo metodo quando ha un token nella coda, svegliando il thread **/
    public synchronized void acquireToken(TokenData token) {
        this.token = token;
        hasToken = true;
        notify();
    }

    /** Inoltra il token al nodo successivo e si mette in attesa del prossimo token **/
    private void passToken() {
        hasToken = false;
        RingNetworkGrpc.RingNetworkStub stub = RingNetworkGrpc.newStub(queueManager.getChannel());
        stub.passToken(token, new StreamObserver<ACK>() {
            @Override
            public void onNext(ACK ack) {}
            @Override
            public void onError(Throwable throwable) { }
            @Override
            public void onCompleted() { }
        });
        waitForToken();
    }

    /** Inoltra il token al nodo successivo per l'ultima volta prima di spegnersi **/
    private void passTokenLastTime() {
        if (hasToken) {
            RingNetworkGrpc.RingNetworkStub stub = RingNetworkGrpc.newStub(queueManager.getChannel());
            stub.passToken(token, new StreamObserver<ACK>() {
                @Override
                public void onNext(ACK ack) { }

                @Override
                public void onError(Throwable throwable) { }

                @Override
                public void onCompleted() { }
            });
        }
    }

    /** Attende il prossimo token **/
    private synchronized void waitForToken() {
        if (!hasToken)
            try { wait(); }
            catch (InterruptedException e) { e.printStackTrace(); }
    }
}
