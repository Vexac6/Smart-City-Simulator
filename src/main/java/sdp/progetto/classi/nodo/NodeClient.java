package sdp.progetto.classi.nodo;

import sdp.progetto.classi.Casa;
import sdp.progetto.classi.MediaQuartiere;
import sdp.progetto.classi.RegistrationResponse;
import sdp.progetto.database.QuartiereDB;
import sdp.progetto.database.StatisticheDB;
import sdp.progetto.grpc.TokenData;
import sdp.progetto.risorse.GatewayHome;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/** Modulo del nodo che si occupa di comunicare col gateway tramite metodi REST **/
public class NodeClient {

    /** La Casa da registrare nel database **/
    private final Casa casa;

    /** Client jersey per configurare le richieste **/
    private final Client client;

    /** Indirizzi di destinazione per le varie richieste **/
    private final WebTarget home, network, statistics, thisHouse;

    /** Costruttore tipico **/
    public NodeClient(Casa casa, String gatewayIP, int gatewayPort) {
        this.casa = casa;
        client = ClientBuilder.newClient();
        home = client.target( "http://"
                + gatewayIP + ":" + gatewayPort
                + GatewayHome.ARTIFACT_PATH
                + GatewayHome.SERVLET_PATH);
        network = home.path(QuartiereDB.PATH);
        statistics = home.path(StatisticheDB.PATH);
        thisHouse = network.path(Integer.toString(casa.getId()));
    }

    /** Registra il nodo nel database. Se va a buon fine, ritorna una rappresentazione attuale del quartiere **/
    public RegistrationResponse registerOnGateway() {
        Invocation.Builder ib = network.request(MediaType.APPLICATION_JSON);
        Response registrazione = ib.post(Entity.entity(casa, MediaType.APPLICATION_JSON));
        if (registrazione.getStatus() == 201) {
            //System.out.println("Il Gateway ha registrato la tua casa nel quartiere");
            return registrazione.readEntity(RegistrationResponse.class);
        }
        else if (registrazione.getStatus() == 404) {
            System.out.println("Impossibile raggiungere il Gateway: Indirizzo sbagliato");
            System.exit(0);
        }
        else if (registrazione.getStatus() == 409) { // -> ID/Port già presente
            System.out.println(registrazione.readEntity(String.class));
            System.exit(0);
        }
        else { // Qualsiasi altro tipo di risposta è un errore
            System.err.print("Errore " + registrazione.getStatus() + ": -> ");
            System.err.println(registrazione.readEntity(String.class));
            System.exit(0);
        }
        return null; // Frammento di codice mai raggiunto
    }

    /** Rimuove il nodo dal database. Non necessita di avere il token per rimuoversi **/
    public void removeFromGateway() {
        Invocation.Builder ib = thisHouse.request(MediaType.APPLICATION_JSON);
        Response registrazione = ib.delete();
        if (registrazione.getStatus() == 200)  // -> Rimozione completata
            System.out.println(registrazione.readEntity(String.class));
        else if (registrazione.getStatus() == 404) { // -> Casa non trovata
            System.out.println(registrazione.readEntity(String.class));
            System.exit(0);
        }
        else { // Qualsiasi altro tipo di risposta è un errore
            System.err.print("Errore " + registrazione.getStatus() + ": -> ");
            System.err.println(registrazione.readEntity(String.class));
            System.exit(0);
        }
        client.close();
    }

    /** Inserisce la media di quartiere nel database delle statistiche **/
    public void postStatistics(TokenData token) {
        //System.out.println("Posto statistiche!");
        Invocation.Builder ib = statistics.request(MediaType.APPLICATION_JSON);
        Response r = ib.post(Entity.entity(new MediaQuartiere(token), MediaType.APPLICATION_JSON));
        System.out.println(r.readEntity(String.class));
    }

}
