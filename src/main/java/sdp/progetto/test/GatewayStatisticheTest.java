package sdp.progetto.test;

import org.junit.BeforeClass;
import org.junit.Test;
import sdp.progetto.classi.Casa;
import sdp.progetto.classi.MediaQuartiere;
import sdp.progetto.classi.RegistrationResponse;
import sdp.progetto.database.StatisticheDB;
import sdp.progetto.risorse.GatewayHome;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GatewayStatisticheTest {

    /**
     * Righe di codice da commentare / scommentare per il corretto funzionamento:
     * - In generale bisogna far partire il server, ovviamente -
     * - StatisticheRisorsa 46-48
     * - StatisticheRisorsa 85-95
     **/

    private static WebTarget statistiche, readTest;

    @BeforeClass
    public static void setup() {
        Client client = ClientBuilder.newClient();
        WebTarget home = client.target(GatewayHome.HOME
                + GatewayHome.ARTIFACT_PATH
                + GatewayHome.SERVLET_PATH);
        statistiche = home.path(StatisticheDB.PATH);
        readTest = statistiche.path("testing");

        List<Integer> testParticipants = new ArrayList<>();
        testParticipants.add(1); testParticipants.add(2); testParticipants.add(3);
        long testTime = System.currentTimeMillis();
        MediaQuartiere media1 = new MediaQuartiere(testParticipants, 20, testTime, testTime+999);
        MediaQuartiere media2 = new MediaQuartiere(testParticipants, 21, testTime+1000, testTime+1999);
        MediaQuartiere media3 = new MediaQuartiere(testParticipants, 22, testTime+2000, testTime+2999);
        MediaQuartiere media4 = new MediaQuartiere(testParticipants, 23, testTime+3000, testTime+3999);
        Invocation.Builder ib = statistiche.request(MediaType.APPLICATION_JSON);
        ib.post(Entity.entity(media1, MediaType.APPLICATION_JSON));
        ib.post(Entity.entity(media2, MediaType.APPLICATION_JSON));
        ib.post(Entity.entity(media3, MediaType.APPLICATION_JSON));
        ib.post(Entity.entity(media4, MediaType.APPLICATION_JSON));
    }

    private void registerStat(MediaQuartiere media) {
        System.out.println("Inserimento");
        statistiche.request(MediaType.APPLICATION_JSON).post(Entity.entity(media, MediaType.APPLICATION_JSON));
    }

    private MediaQuartiere readLastStat() {
        System.out.println("Lettura");
        Response resp = readTest.request(MediaType.APPLICATION_JSON).get();
        return resp.readEntity(MediaQuartiere.class);
    }


    @Test
    public void insertionBeforeAnalyzeShouldBeRead() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Integer> testParticipants = new ArrayList<>();
                testParticipants.add(4); testParticipants.add(5); testParticipants.add(6);
                // Inserendo 1234, il thread dorme 5 secondi durante la registrazione
                registerStat(new MediaQuartiere(testParticipants, 1234,
                        System.currentTimeMillis(), System.currentTimeMillis()));
            }
        });

        thread1.start();
        // La lettura arriva sicuramente dopo l'inserimento
        try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
        MediaQuartiere returned = readLastStat();
        try {
            thread1.join();
        } catch (InterruptedException e) {e.printStackTrace();}

        assertTrue(returned.getAverage() == 1234);
    }

    @Test
    public void insertionAfterAnalyzeShouldntBeRead() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // L'inserimento arriva sicuramente dopo la lettura
                try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
                List<Integer> testParticipants = new ArrayList<>();
                testParticipants.add(4); testParticipants.add(5); testParticipants.add(6);
                registerStat(new MediaQuartiere(testParticipants, 999,
                        System.currentTimeMillis(), System.currentTimeMillis()));
            }
        });

        thread1.start();
        // La lettura avviene mentre il thread dorme
        MediaQuartiere returned = readLastStat();
        try {
            thread1.join();
        } catch (InterruptedException e) {e.printStackTrace();}
        assertFalse(returned.getAverage() == 999);
    }


}