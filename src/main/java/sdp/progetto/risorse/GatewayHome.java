package sdp.progetto.risorse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/** Homepage del gateway, è la risorsa root dell'applicazione **/
@Path("/")
public class GatewayHome {

    public static final String HOME = "http://localhost:8080";
    public static final String ARTIFACT_PATH = "/smartcity";
    public static final String SERVLET_PATH = "/rest";

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String plainTextHome() {
        return "Gateway Home Page";
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public String XMLHome() {
        return "<?xml version=\"1.0\"?>" + "<home> Gateway Home Page" + "</home>";
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String htmlHome() {
        return "<html> " + "<title>" + "Smart City Network Home Page" + "</title>"
                + "<body><h1>" + "Gateway Home Page" + "</body></h1>" + "</html> ";
    }
}
