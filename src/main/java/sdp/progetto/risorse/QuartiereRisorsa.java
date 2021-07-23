package sdp.progetto.risorse;

import sdp.progetto.classi.Casa;
import sdp.progetto.classi.Quartiere;
import sdp.progetto.database.QuartiereDB;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("quartiere")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class QuartiereRisorsa {

    /**
     * Mostra l'elenco di case del quartiere (Browser only)<br>
     * • thread-safe •
     **/
    @GET
    public Quartiere listOfNodes() {
        return QuartiereDB.getInstance().getQuartiere();
    }

    /**
     * Mostra al client analista il numero di case presenti nel quartiere<br>
     * • thread-safe •
     **/
    @GET
    @Path("membri")
    public int numberOfNodes() {return QuartiereDB.getInstance().members(); }

    /**
     * Mostra una specifica casa del quartiere (Browser only)<br>
     * • thread-safe •
     **/
    @GET
    @Path("{id}")
    public Response getNodeByID(@PathParam("id") int id) {
        synchronized (QuartiereDB.getInstance().getLock()) {
            if (!QuartiereDB.getInstance().contains(id))
                return Response.status(404).entity("La casa con ID " + id + " non è presente nella rete.").build();
            return Response.ok().entity(QuartiereDB.getInstance().house(id)).build();
        }
    }

    /**
     * Inserisce una casa nel database, poi manda al client una rappresentazione del quartiere,
     * che userà per costruirsi la propria rappresentazione interna.<br>
     * • thread-safe •
     **/
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addNode(Casa casa) throws URISyntaxException {
        if(casa == null)
            return Response.status(400).entity("Non è possibile inserire un nodo vuoto nella rete.").build();
        synchronized (QuartiereDB.getInstance().getLock()) {
            //if (casa.getIPAddress().equalsIgnoreCase("test"))
                //try {System.out.println("Insert"+casa.getId()+" sleeping...");Thread.sleep(5000);}
                //catch (InterruptedException e) {e.printStackTrace();}
            if (QuartiereDB.getInstance().contains(casa.getId()))
                return Response.status(409).entity("ID già registrato nella rete, registrazione respinta.").build();
            // Questa parte è fondamentale durante il testing/demo in locale -> evita di avere porte duplicate
            if (QuartiereDB.getInstance().portIsTaken(casa.getPort()))
                return Response.status(409).entity("<DEMO> Porta già in uso per un'altra casa, registrazione respinta.").build();
            QuartiereDB.getInstance().post(casa);
            URI newNode = new URI(GatewayHome.SERVLET_PATH + "/"
                    + QuartiereDB.PATH + "/"
                    + casa.getId());
            // Risponde alla registrazione ritornando l'elenco delle altre case al nodo
            return Response.created(newNode).entity(QuartiereDB.getInstance().provideResponse()).build();
        }
    }

    /**
     * Rimuove una casa dal database.<br>
     * • thread-safe •
     **/
    @DELETE
    @Path("{id}")
    public Response removeNode(@PathParam("id") int id) {
        synchronized (QuartiereDB.getInstance().getLock()) {
            //if (QuartiereDB.getInstance().getQuartiere().getHouse(id).getIPAddress().equalsIgnoreCase("test"))
                //try {System.out.println("Remove"+id+" sleeping...");Thread.sleep(5000);}
                //catch (InterruptedException e) {e.printStackTrace();}
            if (!QuartiereDB.getInstance().contains(id))
                return Response.status(404).entity("La casa con ID " + id + " non è presente nella rete.").build();
            QuartiereDB.getInstance().delete(id);
            return Response.status(200).entity("La casa " + id + " è stata rimossa dalla rete.").build();
        }
    }
}
