package sdp.progetto.test;

import org.junit.BeforeClass;
import org.junit.Test;
import sdp.progetto.classi.Casa;
import sdp.progetto.classi.RegistrationResponse;
import sdp.progetto.database.QuartiereDB;
import sdp.progetto.risorse.GatewayHome;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class GatewayQuartiereTest {

    /**
     * Righe di codice da commentare / scommentare per il corretto funzionamento:
     * - In generale bisogna far partire il server, ovviamente -
     * - QuartiereRisorsa 59-61
     * - QuartiereRisorsa 84-86
     **/

    private static WebTarget quartiere;

    @BeforeClass
    public static void setup() {
        Client client = ClientBuilder.newClient();
        WebTarget home = client.target(GatewayHome.HOME
                + GatewayHome.ARTIFACT_PATH
                + GatewayHome.SERVLET_PATH);
        quartiere = home.path(QuartiereDB.PATH);
    }

    private RegistrationResponse registerHouse(Casa casa) {
        System.out.println("Insert"+casa.getId());
        Invocation.Builder ib = quartiere.request(MediaType.APPLICATION_JSON);
        Response registrazione = ib.post(Entity.entity(casa, MediaType.APPLICATION_JSON));
        if (registrazione.getStatus() == 201)
            return registrazione.readEntity(RegistrationResponse.class);
        else
            return new RegistrationResponse(); // Ritorna una response vuota come segno di errore
    }

    private void removeHouse(int id) {
        System.out.println("Remove"+id);
        WebTarget thisHouse = quartiere.path(Integer.toString(id));
        Invocation.Builder ib = thisHouse.request(MediaType.APPLICATION_JSON);
        ib.delete();
    }


    @Test
    public void doubleInsertionFirstShouldntKnowSecond() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // La sleep consente al thread di iscriversi per secondo
                try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
                registerHouse(new Casa(2, "address", 2222));
            }
        });

        thread1.start();
        // Si iscrive per primo, ma a causa dell'IP test dorme per 5 secondi durante l'iscrizione
        RegistrationResponse returned = registerHouse(new Casa(1, "test", 1111));
        try {
            thread1.join();
        } catch (InterruptedException e) {e.printStackTrace();}

        assertFalse(returned.getQuartiere().containsHouse(2));
    }

    @Test
    public void doubleInsertionSecondShouldKnowFirst() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Si iscrive per primo, ma a causa dell'IP test dorme per 5 secondi durante l'iscrizione
                registerHouse(new Casa(3, "test", 3333));
            }
        });
        thread1.start();
        // La sleep consente al thread di iscriversi per secondo
        try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
        RegistrationResponse returned = registerHouse(new Casa(4, "address", 4444));
        try {
            thread1.join();
        } catch (InterruptedException e) {e.printStackTrace();}

        assertTrue(returned.getQuartiere().containsHouse(3));
    }

    @Test
    public void insertionBeforeRemovalShouldKnowRemovedNode() {
        registerHouse(new Casa(99, "blabla", 9999));
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Questo thread dorme quanto basta per fare la delete sempre per secondo
                try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
                removeHouse(99);
            }
        });

        thread1.start();
        // Questa istruzione ha una sleep di 5 secondi a causa dell'IP di test
        RegistrationResponse returned = registerHouse(new Casa(6, "test", 6666));
        try {
            thread1.join();
        } catch (InterruptedException e) {e.printStackTrace();}
        assertTrue(returned.getQuartiere().containsHouse(99));
    }

    @Test
    public void insertionAfterRemovalShouldntKnowRemovedNode() {
        registerHouse(new Casa(100, "blabla", 10000));
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Questa istruzione ha una sleep di 5 secondi a causa dell'ID di test (>=100)
                removeHouse(100);
            }
        });

        thread1.start();
        // Il main dorme quanto basta per fare la delete sempre per prima
        try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
        RegistrationResponse returned = registerHouse(new Casa(7, "address", 7777));
        try {
            thread1.join();
        } catch (InterruptedException e) {e.printStackTrace();}
        assertFalse(returned.getQuartiere().containsHouse(100));
    }


}