package sdp.progetto.classi;

import sdp.progetto.simulatori.Buffer;
import sdp.progetto.simulatori.Measurement;

/**
 * Classe per immagazzinare i Measurement raccolti dal simulatore di PM10.<br>
 * Elabora la media locale con la tecnica della sliding window.
 **/
public class SlidingWindow implements Buffer {

    /** Array che contiene le rilevazioni **/
    private final Measurement[] buffer;

    /** La lunghezza massima del buffer **/
    private final int length;

    /** Indica quante misurazioni servono prima di calcolare una nuova media locale **/
    private final int overlap;

    /** Contatore delle rilevazioni **/
    private int counter;

    /** La media locale ATTUALE delle rilevazioni **/
    private Measurement localAverage;

    /** Vero se c'è una nuova media locale e non è stata consumata **/
    private volatile boolean hasAverage;

    /**
     * Costruttore per il buffer con tecnica della sliding window
     * @param length Numero di rilevazioni nel buffer prima di iniziare a sovrascriverle
     * @param overlap Numero di rilevazioni necessarie a calcolare una media locale
     */
    public SlidingWindow (int length, int overlap) {
        counter = 0;
        this.length = length;
        this.overlap = overlap;
        buffer = new Measurement[length];
        hasAverage = false;
    }

    public boolean hasAverage() { return hasAverage; }

    @Override
    public void addMeasurement(Measurement measurement) {
        buffer[counter % length] = measurement;
        if ((counter + 1) % overlap == 0 && counter >= length - 1)
            localAverage = produceAverage();
        counter++;
        //System.out.println(counter + ": " + buffer[(counter-1)%length]);
    }

    /** Produce un Measurement che è media dei valori raccolti nella finestra attuale **/
    private Measurement produceAverage() {
        hasAverage = true;
        double avgValue = 0;
        for (int i=0; i<length; i++)
            avgValue += buffer[i].getValue();
        avgValue /= length;
        //System.out.println("Media Prodotta: "+avgValue);
        return new Measurement(buffer[0].getId(), buffer[0].getType(), avgValue, System.currentTimeMillis());
    }

    /** Consente di accedere dall'esterno all'ultima media locale prodotta **/
    public Measurement getLocalAverage() {
        hasAverage = false;
        return localAverage;
    }
}
