package sdp.progetto.classi.nodo;

import sdp.progetto.classi.Casa;

/**
 * Un Nodo è una Casa con funzionalità di rete, che partecipa al network p2p.<br>
 * La classe principale coordina tutti i diversi moduli.
 **/
public class Nodo extends Casa {

    /** Indirizzo IP del gateway **/
    private final String gatewayIP;

    /** Porta di comunicazione del gateway **/
    private final int gatewayPort;

    /** Modulo che comunica col gateway **/
    private NodeClient client;
    /** Funzionalità di client-server per i servizi rpc **/
    private NodePeer peer;
    /** Funzionalità di simulatore per i rilevamenti di PM10**/
    private NodeSurveyor surveyor;

    public NodePeer getPeer() { return peer; }
    public NodeClient getClient() { return client; }
    public NodeSurveyor getSurveyor() { return surveyor; }

    /** Costruttore vuoto -> inutilizzato **/
    public Nodo() {
        super();
        gatewayIP = "localhost";
        gatewayPort = 8080;
    }

    /** Costruttore principale della demo -> parametri sono gli args del main **/
    public Nodo (int id, int port) {
        super(id, port);
        gatewayIP = "localhost";
        gatewayPort = 8080;
    }

    /** Costruttore di test -> Imposta i parametri della casa in maniera casuale **/
    public Nodo (String test) {
        super(test);
        gatewayIP = "localhost";
        gatewayPort = 8080;
    }

    /** Costruttore completo -> per eventuali utilizzi futuri in remoto **/
    public Nodo (int id, String IPAddress, int port, String gatewayIP, int gatewayPort) {
        super(id, IPAddress, port);
        this.gatewayIP = gatewayIP;
        this.gatewayPort = gatewayPort;
    }

    /** Registra il nodo sul database e accende le varie componenti **/
    public void turnOn() {
        client = new NodeClient(castToCasa(), gatewayIP, gatewayPort);
        peer = new NodePeer(this, client.registerOnGateway());
        surveyor = new NodeSurveyor(id);
        peer.signInNetwork();
    }

    /** Il nodo viene rimosso dal database e abbandona la rete ad anello **/
    public void turnOff() {
        System.out.println("Uscita dal sistema, attendere...");
        client.removeFromGateway();
        surveyor.stopSimulator();
        if (peer.getQuartiere().members() > 1) {
            if (peer.isFounder()) {
                //System.out.println("Rimozione di un nodo fondante, attendere...");
                peer.stopUpdates();
                peer.startElection(); // -> BLOCKING
            }
            peer.leaveNetwork(); // -> BLOCKING
        }
        peer.cleanStop();
    }

    /** Jackson vorrebbe che il Nodo che inserisco nel DB fosse serializzabile, ma a me basta una Casa **/
    private Casa castToCasa() {
        return new Casa(id, IPAddress, port);
    }
}


