package sdp.progetto.classi;

/**
 * Crea una risposta ad-hoc per la registrazione di un nodo, ritornando
 * sia un intero (seniority) che il quartiere.
 */
public class RegistrationResponse {

    private int seniority;
    private Quartiere quartiere;

    /** Costruttore vuoto usato per la serializzazione e per gli errori nei test **/
    public RegistrationResponse() {
        seniority = 0;
        quartiere = null;
    }

    /** Costruttore tipico **/
    public RegistrationResponse(int seniority, Quartiere quartiere) {
        this.seniority = seniority;
        this.quartiere = quartiere;
    }

    // Getters e setters necessari per la serializzazione //
    public int getSeniority() { return seniority; }
    public void setSeniority(int seniority) { this.seniority = seniority; }
    public Quartiere getQuartiere() { return quartiere; }
    public void setQuartiere(Quartiere quartiere) { this.quartiere = quartiere; }
    // Getters e setters necessari per la serializzazione //

}
