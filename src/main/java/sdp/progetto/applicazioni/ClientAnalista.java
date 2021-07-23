package sdp.progetto.applicazioni;

import sdp.progetto.database.QuartiereDB;
import sdp.progetto.database.StatisticheDB;
import sdp.progetto.risorse.GatewayHome;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Scanner;

/**
 * Si interfaccia con il Gateway e consente all'utente di analizzare le statistiche
 * registrate dal quartiere.
 */
public class ClientAnalista {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        WebTarget home = client.target( GatewayHome.HOME
                + GatewayHome.ARTIFACT_PATH
                + GatewayHome.SERVLET_PATH);
        WebTarget statistiche = home.path(StatisticheDB.PATH);
        WebTarget members = home.path(QuartiereDB.PATH).path("membri");
        Response response;
        int lastN;
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.println("Benvenuto nell'applicazione per gli analisti.");
        do {
            System.out.println(menuInterface());
            System.out.print(askInput());
            input = sc.nextLine();
            switch (input) {
                case "1": {
                    response = members.request(MediaType.APPLICATION_JSON).get();
                    System.out.println((char)27 + "[35m"
                            + "\nAl momento il quartiere contiene "
                            + response.readEntity(Integer.class) + " case.\n"
                            + (char)27 + "[0m");
                    break;
                }
                case "2": {
                    System.out.print(askNStats());
                    input = sc.nextLine();
                    try { lastN = Integer.parseInt(input);}
                    catch (NumberFormatException e) {System.out.println("Devi inserire un valore intero per N!"); break;}
                    if (lastN <= 0) {System.out.println("Scegli un numero positivo di statistiche!"); break;}
                    response = statistiche.path("ultime_"+lastN)
                            .request(MediaType.APPLICATION_JSON)
                            .get();
                    String toColor = response.readEntity(String.class);
                    System.out.println((char)27 + "[32m" + toColor + (char)27 + "[0m\n");
                    break;
                }
                case "3": {
                    System.out.print(askNStats());
                    input = sc.nextLine();
                    try { lastN = Integer.parseInt(input);}
                    catch (NumberFormatException e) {System.out.println("Devi inserire un valore intero per N!"); break;}
                    if (lastN <= 0) {System.out.println("Scegli un numero positivo di statistiche!"); break;}
                    response = statistiche.path("ultime_"+lastN).path("analisi")
                            .request(MediaType.APPLICATION_JSON)
                            .get();
                    String toColor = response.readEntity(String.class);
                    System.out.println((char)27 + "[34m" + toColor + (char)27 + "[0m");
                    break;
                }
                case "4": { client.close(); sc.close(); System.exit(0);}
                default: System.out.println("Input non valido!");
            }
        }
        while (true);
    }

    private static String menuInterface() {
        return  "-------------------------------------------------------------------\n" +
                "|(1)\tNumero di nodi presenti nella rete                        |\n" +
                "|(2)\tUltime [n] statistiche con relativi timestamp             |\n" +
                "|(3)\tDeviazione standard e media delle ultime [n] statistiche  |\n" +
                "|(4)\tChiudi l'applicazione                                     |\n" +
                "-------------------------------------------------------------------";
    }

    private static String askInput() {
        return  "Digita il numero corrispondente all'opzione scelta: ";
    }

    private static String askNStats() {
        return  "Quante delle ultime statistiche? [n] = ";
    }
}
