package sdp.progetto.classi;

import com.google.protobuf.GeneratedMessageV3;
import java.util.LinkedList;

/**
 * Una coda (Linked List) pensata per essere gestita da un manager,
 * che polla il primo elemento poi va in attesa di nuovi elementi (notificati da altri thread)
 */
public class WaitingQueue extends LinkedList<GeneratedMessageV3> {

    /** Metodo add() sincronizzato, inoltre sveglia il manager in attesa **/
    public synchronized void addMessage(GeneratedMessageV3 message) {
        add(message);
        notify();
    }

    /**
     * Ritorna vero se c'è almeno un messaggio nella coda, altrimenti mette
     * in attesa il manager fintanto che non arriverà un nuovo messaggio di rete
     **/
    public synchronized boolean hasMessage() {
        if (peek() == null)
            try {wait();} catch (InterruptedException e) { e.printStackTrace(); }
        return peek() != null;
    }
}
