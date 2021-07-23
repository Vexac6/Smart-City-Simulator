package sdp.progetto.database;

import sdp.progetto.classi.Casa;
import sdp.progetto.classi.Quartiere;
import sdp.progetto.classi.RegistrationResponse;

/**
 * Database per un quartiere, costruito con il singleton pattern
 **/
public class QuartiereDB {

    /** l'URI associato alla risorsa nel package risorse **/
    public static final String PATH = "quartiere";

    /** Mutex esplicito per la sincronizzazione **/
    private final Object LOCK;

    /** L'insieme di case del database è immagazzinato in questa variabile **/
    private final Quartiere quartiere;

    /**
     * Contatore degli inserimenti fatti dalla creazione del database.<br>
     * Viene passato a un nodo che si registra nella rete come suo valore di seniority
     **/
    private int membersCounter;

    // ------ Singleton Pattern -------

    private static class QuartiereDBInstance {
        private static final QuartiereDB INSTANCE = new QuartiereDB();
    }

    public static QuartiereDB getInstance() {
        return QuartiereDBInstance.INSTANCE;
    }

    private QuartiereDB() {
        LOCK = new Object();
        quartiere = new Quartiere();
        membersCounter = 0;
    }
    // ---------------------------------

    public Object getLock() { return LOCK; }
    public int getMembersCounter() { synchronized (LOCK) {return membersCounter;} }
    public Quartiere getQuartiere() { synchronized (LOCK) {return quartiere;} }

    /** Incrementa il contatore in maniera sincronizzata **/
    private void increaseMembers() { synchronized (LOCK) {membersCounter++;} }

    /** Ritorna la Casa con ID inserito **/
    public Casa house(int id) { return quartiere.getHouse(id); }

    /** Ritorna vero se il quartiere contiene la casa indicata **/
    public boolean contains(int id) { return quartiere.containsHouse(id); }

    /** Ritorna il numero di case attualmente attive nel quartiere **/
    public int members() { return quartiere.members(); }

    /** Crea una risposta ad-hoc per la registrazione di una casa **/
    public RegistrationResponse provideResponse() {
        return new RegistrationResponse(membersCounter, new Quartiere(quartiere));
    }

    /** Ritorna vero se una casa sta già usando la porta indicata **/
    public boolean portIsTaken(int port) {
        for (Casa c : quartiere.houses())
            if (port == c.getPort())
                return true;
        return false;
    }

    /** Inserisce una casa nel quartiere e aumenta il valore di seniority **/
    public void post(int key, Casa value) {
        quartiere.putHouse(key, value);
        increaseMembers();
    }

    /** Inserisce una casa nel quartiere e aumenta il valore di seniority **/
    public void post(Casa value) {
        quartiere.putHouse(value);
        increaseMembers();
    }

    /** Rimuove una casa dal quartiere **/
    public void delete(int id) {
        quartiere.removeHouse(id);
    }

}
