package sdp.progetto.classi.nodo;

import sdp.progetto.classi.SlidingWindow;
import sdp.progetto.grpc.Statistics;
import sdp.progetto.grpc.TokenData;
import sdp.progetto.simulatori.Measurement;
import sdp.progetto.simulatori.PM10Simulator;

/** Modulo di un nodo che si occupa di effettuare rilevazioni e di scriverle nel token **/
public class NodeSurveyor {

    /** Il simulatore di rilevazioni **/
    private final PM10Simulator simulator;

    /** Il buffer che contiene le rilevazioni **/
    private final SlidingWindow buffer;

    /** ID del nodo **/
    private final int nodeID;

    /** Costruttore generico -> per utilizzi futuri **/
    public NodeSurveyor(int nodeID, int length, int overlap) {
        this.nodeID = nodeID;
        buffer = new SlidingWindow(length,overlap);
        simulator = new PM10Simulator(buffer);
        simulator.start();
    }

    /** Costruttore hardcodato -> Demo **/
    public NodeSurveyor(int nodeID) {
        this.nodeID = nodeID;
        buffer = new SlidingWindow(12,6);
        simulator = new PM10Simulator(buffer);
        simulator.start();
    }

    /** Riempie il token con la media locale calcolata dalla Sliding Window **/
    public TokenData fillToken(TokenData token) {
        return TokenData.newBuilder()
                .mergeFrom(token)
                .putStats(nodeID, serialize(buffer.getLocalAverage()))
                .build();
    }

    public boolean hasLocalAverage() {
        return buffer.hasAverage();
    }

    /** Spegne il simulatore **/
    public void stopSimulator() {
        simulator.stopMeGently();
    }

    /** Traduce una rilevazione in un messaggio di rete **/
    private Statistics serialize (Measurement measurement) {
        return Statistics.newBuilder()
                .setValue(measurement.getValue())
                .setTimestamp(measurement.getTimestamp())
                .build();
    }

}
