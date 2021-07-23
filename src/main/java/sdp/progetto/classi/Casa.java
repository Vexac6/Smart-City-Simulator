package sdp.progetto.classi;

import sdp.progetto.grpc.HouseData;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Oggetto che può essere serializzato e immagazzinato nel database. Il quartiere è infatti
 * un insieme di Case, e non di Nodi, che sono oggetti più complessi con molte funzionalità di rete.
 **/
public class Casa {

    /** ID univoco per ogni nodo **/
    protected int id; // Il Gateway provvede al check
    /** Indirizzo IP del nodo (in locale nella demo) **/
    protected String IPAddress;
    /** Porta di comunicazione p2p con gli altri nodi **/
    protected int port; // Nella demo anche questo è univoco, essendo tutti i nodi in locale

    /* --- Getters e Setters per serializzazione --- */
    public int getId() { return id; }
    public String getIPAddress() { return IPAddress; }
    public int getPort() { return port; }

    public void setId(int id) { this.id = id; }
    public void setIPAddress(String IPAddress) { this.IPAddress = IPAddress; }
    public void setPort(int port) { this.port = port; }
    /* ---------------------------------------- */

    /** Costruttore di default -> necessario per la serializzazione **/
    public Casa() { }

    /** Costruttore principale della demo -> parametri sono gli args del main **/
    public Casa(int id, int port) {
        this.id = id;
        IPAddress = "localhost";
        this.port = port;
    }

    /** Costruttore per la demo -> imposta i parametri della casa in maniera casuale **/
    public Casa(String demo) {
        if (!demo.equalsIgnoreCase("demo")) {
            System.out.println("Avvia il Nodo con il parametro 'demo' per impostarlo casualmente");
            System.exit(0);
        }
        id = ThreadLocalRandom.current().nextInt(1, 1000);
        IPAddress = "localhost";
        port = ThreadLocalRandom.current().nextInt(4000, 6001);
    }

    /** Costruttore che deserializza un messaggio di update **/
    public Casa (HouseData data) {
        id = data.getId();
        IPAddress = data.getAddress();
        port = data.getPort();
    }

    /** Costruttore completo -> per eventuali utilizzi futuri in remoto **/
    public Casa(int id, String IPAddress, int port) {
        this.id = id;
        this.IPAddress = IPAddress;
        this.port = port;
    }


    /** Restituisce l'indirizzo di destinazione per comunicare con una casa **/
    public String targetAddress() {
        return IPAddress + ":" + port;
    }

    /** Serializza una casa per comunicare i suoi dati in un messaggio di rete **/
    public HouseData serialize() {
        return HouseData.newBuilder().setId(id).setAddress(IPAddress).setPort(port).build();
    }

    /** Mantiene la funzionalità di equals(), ma ignora le diverse istanze in memoria **/
    public boolean sameAs(Casa other) {
        return id == other.getId() && IPAddress.equals(other.getIPAddress()) && port == other.getPort();
    }

}
