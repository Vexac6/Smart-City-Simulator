package sdp.progetto.classi;

import sdp.progetto.grpc.TokenData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/** Classe serializzabile per immagazzinare le medie calcolate dalla rete di nodi **/
public class MediaQuartiere {

    /** Ogni media ha un ID, ma è assegnato dal gateway **/
    private int id;

    /** La lista dei nodi partecipanti alla media **/
    private List<Integer> participants;

    /** Il valore della media **/
    private double average;

    /** Il primo e l'ultimo timestamp delle rilevazioni che hanno contribuito alla media **/
    private long fromTime, toTime;

    /** Costruttore vuoto, necessario per serializzare **/
    public MediaQuartiere() { }

    /** Costruttore usato nei test **/
    public MediaQuartiere (List<Integer> participants, double average, long fromTime, long toTime) {
        this.participants = participants;
        this.average = average;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /** Costruttore che converte il contenuto di un token in una Media di quartiere **/
    public MediaQuartiere (TokenData token) {
        participants = new ArrayList<>();
        participants.addAll(token.getStatsMap().keySet());
        computeAverage(token);
        computeTimestamps(token);
    }

    // Getters e Setters necessari per la serializzazione //
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public List<Integer> getParticipants() { return participants; }
    public void setParticipants(List<Integer> participants) { this.participants = participants; }
    public double getAverage() { return average; }
    public void setAverage(double average) { this.average = average; }
    public long getFromTime() { return fromTime; }
    public void setFromTime(long fromTime) { this.fromTime = fromTime; }
    public long getToTime() { return toTime; }
    public void setToTime(long toTime) { this.toTime = toTime; }
    // Getters e Setters necessari per la serializzazione //

    /** Calcola la media di tutte le rilevazioni in un token **/
    private void computeAverage (TokenData token) {
        double avg = 0;
        for (int key : token.getStatsMap().keySet())
            avg += token.getStatsMap().get(key).getValue();
        average = avg / token.getStatsCount();
    }

    /** Trova il timestamp più vecchio e quello più recente **/
    private void computeTimestamps (TokenData token) {
        List<Long> timestamps = new ArrayList<>();
        for (int key : token.getStatsMap().keySet())
            timestamps.add(token.getStatsMap().get(key).getTimestamp());
        fromTime = Collections.min(timestamps);
        toTime = Collections.max(timestamps);
    }

    /** Stampa i timestamp in millisecondi in una maniera leggibile **/
    private String prettyTimestamp() {
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timestamp = new SimpleDateFormat("HH:mm:ss.SSSSS");
        Date first = new Date(fromTime);
        Date last = new Date(toTime);
        return date.format(first) + " <" + timestamp.format(first) + " - " + timestamp.format(last) + ">";
    }

    @Override
    public String toString() {
        return "Rilevazione "+ id +" (" + prettyTimestamp() + ") -> " + average + "\n" +
               "\t\t╚═> Nodi partecipanti: " + participants;
    }
}
