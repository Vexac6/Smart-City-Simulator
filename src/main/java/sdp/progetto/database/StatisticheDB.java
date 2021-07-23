package sdp.progetto.database;

import sdp.progetto.classi.MediaQuartiere;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StatisticheDB {

    /** l'URI associato alla risorsa nel package risorse **/
    public static final String PATH = "statistiche";

    /** Mutex esplicito per la sincronizzazione **/
    private final Object LOCK;

    /** L'insieme delle statistiche del quartiere è immagazzinato in questa variabile **/
    private final HashMap<Integer, MediaQuartiere> statistiche;

    /** Contatore degli inserimenti fatti dalla creazione del database.<br>
     *  Ogniqualvolta una media di quartiere viene inserita, incrementa.
     **/
    private int statsCounter;

    // ------ Singleton Pattern -------

    private static class StatisticheDBInstance {
        private static final StatisticheDB INSTANCE = new StatisticheDB();
    }

    public static StatisticheDB getInstance() { return StatisticheDBInstance.INSTANCE; }

    private StatisticheDB() {
        LOCK = new Object();
        statistiche = new HashMap<>();
        statsCounter = 0;
    }
    // ---------------------------------

    public Object getLock() { return LOCK; }
    public HashMap<Integer, MediaQuartiere> getStatistiche() { synchronized (LOCK) {return statistiche;} }
    public int getCounter() { synchronized (LOCK) {return statsCounter;} }

    /** Ritorna la misurazione relativa all'ID inserito **/
    public MediaQuartiere mediaQuartiere(int id) { return statistiche.get(id); }

    /** Inserisce una media di quartiere con un ID progressivo, che sarà reperibile a quell'ID **/
    public void post(MediaQuartiere value) {
        statsCounter++;
        value.setId(statsCounter);
        statistiche.put(statsCounter, value);
    }

    /** Ritorna vero se il database contiene la rilevazione con l'ID richiesto **/
    public boolean contains(int key) {
        return statistiche.containsKey(key);
    }

    /**
     * Ritorna una lista delle ultime N statistiche inserite nel database.<br>
     * • thread-safe •
     **/
    public List<MediaQuartiere> lastNStatistics(int n) {
        synchronized (LOCK) {
            List<MediaQuartiere> lastStats = new ArrayList<>();
            for (n-- ; n >= 0; n--)
                lastStats.add(StatisticheDB.getInstance().mediaQuartiere(statsCounter - n));
            return lastStats;
        }
    }

    /**
     * Ritorna una stringa contenente media aritmetica e scarto quadratico medio delle ultime N statistiche.<br>
     * • thread-safe •
     **/
    public String analyzeLastNStatistics(int n) {
        List<MediaQuartiere> lastStats = lastNStatistics(n);
        double media = 0, deviazione = 0;

        // Calcolo della media
        for (MediaQuartiere rilevazione : lastStats)
            media += rilevazione.getAverage();
        media /= lastStats.size();

        // Calcolo della deviazione standard
        for (MediaQuartiere rilevazione : lastStats)
            deviazione += (Math.pow((rilevazione.getAverage() - media), 2) / lastStats.size());
        deviazione = Math.sqrt(deviazione);

        return "\nLa media aritmetica delle ultime " + n + " statistiche è " + media + ".\n" +
               "La deviazione standard delle ultime " + n + " statistiche è " + deviazione + ".\n";
    }

    /**
     * Stampa in modo carino la lista delle ultime N rilevazioni
     * • thread-safe •
     **/
    public String prettyPrint(int n) {
        List<MediaQuartiere> lista = lastNStatistics(n);
        String result = "";
        for (MediaQuartiere rilevazione : lista)
            result = result.concat("\n • " + rilevazione.toString());
        return result;
    }

}
