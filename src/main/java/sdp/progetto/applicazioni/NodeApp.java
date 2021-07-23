package sdp.progetto.applicazioni;


import sdp.progetto.classi.nodo.Nodo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Avvia il processo di un Nodo, lo registra nel database e lo aggiunge alla rete,
 * poi si mette in attesa di un input di terminazione da parte dell'utente.<br>
 * Argomenti da command line:<br>
 *   •  [N, M] con N e M interi: Avvia il nodo in locale usando N come ID e M come porta.<br>
 *   •  ["demo"] : Avvia il nodo in locale con ID e porta generati casualmente.<br>
 *   •  nessun argomento : Avvia il nodo e chiede all'utente di inserire i dati da standard input.
 */
public class NodeApp {

    // Regex per controllare l'inserimento di indirizzi IP validi
    // Source: Techiedelight.com
    private static final String IPV4_REGEX =
            "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private static final Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);

    /** Controlla se la stringa inserita è valida come indirizzo IP **/
    private static boolean validateIP(String ip) {
        if (ip.equals("localhost")) return true;
        Matcher matcher = IPV4_PATTERN.matcher(ip);
        return matcher.matches();
    }

    /**
     *  Menu di inizializzazione della casa. A ogni step viene fatto un controllo
     *  sui dati inseriti, e lo step viene ripetuto in caso di fallimento del controllo.
     *  Una volta inseriti tutti i 5 dati, il ciclo termina.
     **/
    private static Nodo initialize() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int id = 0, port=0, gatewayPort=0;
        String IPAddress="", gatewayIP="";
        int steps = 0;
        String temp;
        do {
            try {
                if (steps == 0) {
                    System.out.println("Inserisci l'ID univoco della tua casa: ");
                    temp = (br.readLine());
                    try { id = (Integer.parseInt(temp)); steps++; }
                    catch (NumberFormatException e) {System.out.println("Devi inserire un ID intero!");}
                }
                if (steps == 1) {
                    System.out.println("Inserisci l'indirizzo IP della tua postazione: ");
                    temp = br.readLine();
                    if (validateIP(temp)) { IPAddress = temp; steps++; }
                    else System.out.println("L'indirizzo IP inserito non è valido!");
                }
                if (steps == 2) {
                    System.out.println("Inserisci il numero di porta per comunicare con le altre case (da 49152 a 65535): ");
                    temp = (br.readLine());
                    try {
                        port = Integer.parseInt(temp);
                        if (port>49152 && port <65535) // Range di porti non registrati IANA
                            steps++;
                        else System.out.println("Questa porta potrebbe essere utilizzata da altre applicazioni, scegline un'altra.");
                    }
                    catch (NumberFormatException e) {System.out.println("Devi inserire un numero di porta intero!");}
                }
                if (steps == 3) {
                    System.out.println("Inserisci l'indirizzo IP del Gateway del tuo quartiere: ");
                    temp = br.readLine();
                    if (validateIP(temp)) { gatewayIP = temp; steps++; }
                    else System.out.println("L'indirizzo IP inserito non è valido!");
                }
                if (steps == 4) {
                    System.out.println("Inserisci il numero di porta di ascolto del Gateway: ");
                    temp = (br.readLine());
                    try { gatewayPort = Integer.parseInt(temp); steps++;}
                    catch (NumberFormatException e) {System.out.println("Devi inserire un numero di porta intero!");}
                }
            }
            catch (IOException e) {e.printStackTrace();}
        }
        while (steps != 5);
        return new Nodo(id, IPAddress, port, gatewayIP, gatewayPort);
    }

    public static void main (String[] args) {

        final Nodo thisNode; // La casa rappresentata da questo processo

        // Check se inizializzazione da command line (per la demo)
        if (args != null){
            if (args.length == 2) {
                try {
                    Integer.parseInt(args[0]);
                    Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Devi inserire due numeri interi (ID, port) per " +
                            "inizializzare una casa da linea di comando!");
                    System.exit(0);
                }
                /* ID = command line
                 * IPAddress = "localhost"
                 * port = command line
                 * dati del gateway rimangono invariati = localhost:8080
                 */
                thisNode = new Nodo(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            }
            // Inizializza la casa con valori casuali (accetta solo 'demo' come input)
            else thisNode = new Nodo(args[0]);
        }
        else { thisNode = initialize(); }
        thisNode.turnOn();

        //Main menu loop
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.println("La tua casa è in funzione correttamente e produce rilevazioni periodiche di PM10.");
        do {
            System.out.print("Premi 'Q' per spegnere le rilevazioni e uscire dalla rete: ");
            input = sc.nextLine();
            if (input.equalsIgnoreCase("q")) {
                Thread blocking = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        thisNode.turnOff();
                    }
                });
                blocking.start();
                try {blocking.join();} catch (InterruptedException e) {e.printStackTrace();}
                break;
            }
            else if (input.equalsIgnoreCase("debug")) {
                //System.out.println("Quartiere: " + thisNode.getPeer().getQuartiere());
                //System.out.println("Anello: Prossimo Nodo -> " + thisNode.getPeer().getNext());
                //System.out.println(thisNode.getPeer().getQueue());
            }
            else System.out.println("Comando non riconosciuto.");
        }
        while (true);
    }
}
