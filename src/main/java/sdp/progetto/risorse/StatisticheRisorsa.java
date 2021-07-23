package sdp.progetto.risorse;

import sdp.progetto.classi.MediaQuartiere;
import sdp.progetto.database.StatisticheDB;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

@Path("statistiche")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class StatisticheRisorsa {

    /** Mostra l'elenco delle statistiche raccolte dalla creazione del quartiere (Browser only)<br>
     * • thread-safe • **/
    @GET
    public HashMap<Integer, MediaQuartiere> listOfStats() {
        return StatisticheDB.getInstance().getStatistiche();
    }

    /** Mostra la statistica corrispondente al numero di rilevazione inserito (Browser only)<br>
     * • thread-safe • **/
    @GET
    @Path("{id}")
    public Response getStatByID(@PathParam("id") int id) {
        synchronized (StatisticheDB.getInstance().getLock()) {
            if (!StatisticheDB.getInstance().contains(id))
                return Response.status(404).entity(
                        "La media di quartiere richiesta (" + id + ")  non è nel database.").build();
            return Response.ok().entity(StatisticheDB.getInstance().mediaQuartiere(id)).build();
        }
    }

    /** Inserisce una media di quartiere (statistica) nel database<br>
     * • thread-safe • **/
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addMeasurement(MediaQuartiere rilevazione) throws URISyntaxException {
        if(rilevazione == null)
            return Response.status(400).entity("Non è possibile inserire una misurazione vuota nel database.").build();
        synchronized (StatisticheDB.getInstance().getLock()) {
            //if (rilevazione.getAverage() == 1234) // Test part
                //try { System.out.println("Sleeping before post...");Thread.sleep(5000); }
                //catch (InterruptedException e) { e.printStackTrace(); }
            StatisticheDB.getInstance().post(rilevazione);
            URI newNode = new URI(GatewayHome.SERVLET_PATH + "/"
                    + StatisticheDB.PATH + "/"
                    + StatisticheDB.getInstance().getCounter());
            return Response.created(newNode).build();
        }
    }

    /** Mostra all'analista le ultime N statistiche nel database<br>
     * • thread-safe • **/
    @GET
    @Path("ultime_{n}")
    public Response lastN(@PathParam("n") int n) {
        synchronized (StatisticheDB.getInstance().getLock()) {
            if (n > StatisticheDB.getInstance().getCounter())
                return Response.status(400).entity(
                        "Hai richiesto troppe statistiche, non ce ne sono abbastanza!").build();
            else
                return Response.ok().entity(StatisticheDB.getInstance().prettyPrint(n)).build();
        }
    }

    /** Mostra all'analista la media e la deviazione standard delle ultime N statistiche<br>
     * • thread-safe • **/
    @GET
    @Path("ultime_{n}/analisi")
    public Response analyzeLastN(@PathParam("n") int n) {
        synchronized (StatisticheDB.getInstance().getLock()) {
            if (n > StatisticheDB.getInstance().getCounter())
                return Response.status(400).entity(
                        "Hai richiesto troppe statistiche, non ce ne sono abbastanza!").build();
            else
                return Response.ok().entity(StatisticheDB.getInstance().analyzeLastNStatistics(n)).build();
        }
    }

    /** Funzione per i test, ritorna l'ultima statistica inserita<br>
     * • thread-safe • **/
    @GET
    @Path("testing")
    public Response testReadLastInserted() {
        synchronized (StatisticheDB.getInstance().getLock()) {
            //try { System.out.println("Sleeping before read...");Thread.sleep(5000); }
            //catch (InterruptedException e) { e.printStackTrace(); }
            return Response.ok().entity(StatisticheDB.getInstance().lastNStatistics(1).get(0)).build();
        }
    }
}
