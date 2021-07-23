package sdp.progetto.classi;

import sdp.progetto.grpc.HouseData;
import java.util.*;

/**
 * Una HashMap di Case che utilizza come chiavi gli ID delle stesse.<br>
 * Tutti i metodi non atomici della classe sono thread-safe, in quanto è
 * progettata per un ambiente multi-thread.<br>
 * • thread-safe •
 **/
public class Quartiere extends HashMap<Integer, Casa> {

    /** Costruttore vuoto -> Impiegato nel database **/
    public Quartiere() {}

    /** Costruttore che traduce un messaggio di update in un quartiere **/
    public Quartiere(List<HouseData> houses) {
        for (HouseData casa : houses)
            put(casa.getId(), new Casa(casa));
    }

    /** Costruttore per esportare il quartiere del database in uno locale **/
    public Quartiere(Quartiere database) {
        for (Casa casa : database.houses())
            putHouse(casa);
    }

    /** Metodo size() sincronizzato **/
    public synchronized int members() { return size(); }

    /** Metodo get() sincronizzato **/
    public synchronized Casa getHouse(int id) { return get(id); }

    /** Metodo put() sincronizzato **/
    public synchronized void putHouse(Casa casa) { put(casa.getId(), casa); }

    /** Metodo put() sincronizzato **/
    public synchronized void putHouse(int id, Casa casa) { put(id, casa); }

    /** Metodo remove() sincronizzato **/
    public synchronized void removeHouse(int id) {
        remove(id);
    }

    /** Metodo contains() sincronizzato **/
    public synchronized boolean containsHouse(int id) { return containsKey(id); }

    /** Metodo contains() sincronizzato **/
    public synchronized boolean containsHouse(Casa casa) { return containsKey(casa.getId()); }

    /** Metodo keySet() sincronizzato **/
    public synchronized Set<Integer> idSet() { return keySet(); }

    /** Metodo values() sincronizzato **/
    public synchronized Collection<Casa> houses() { return values(); }

    /** Aggiunge le case mancanti rispetto a un Update ricevuto **/
    public synchronized void addMissing(Quartiere correct) {
        for (int key : correct.keySet())
            if (!containsKey(key) || !get(key).sameAs(correct.get(key)))
                put(key, correct.get(key));
    }

    /** Rimuove le case in eccesso rispetto a un Update ricevuto **/
    public synchronized void removeExcess(Quartiere correct) {
        List<Integer> keysToRemove = new ArrayList<>();
        for (int key : keySet())
            if (!correct.containsKey(key) || !get(key).sameAs(correct.get(key)))
                keysToRemove.add(key);
        for (int key : keysToRemove)
            remove(key);
    }


    /** Restituisce la prossima casa nel quartiere rispetto all'ID inserito **/
    public synchronized Casa nextHouse(int id) {
        if (id == Collections.max(keySet()))
            return get(Collections.min(keySet()));
        else {
            int temp = Integer.MAX_VALUE;
            for (int key : keySet())
                if (key > id && key < temp)
                    temp = key;
            return get(temp);
        }
    }

    /** Restituisce l'ID della prossima casa nel quartiere rispetto all'ID inserito **/
    public int nextID(int id) {
        return nextHouse(id).getId();
    }

    /** Ritorna vero se i due quartieri si equivalgono **/
    public synchronized boolean sameAs(Quartiere other) {
        // Non basta controllare le chiavi, una casa potrebbe cambiare dati ma mantenere lo stesso ID
        if (!(keySet().containsAll(other.keySet()) && other.keySet().containsAll(keySet()))) return false;
        for (int key : keySet())
            if (!get(key).sameAs(other.get(key)))
                return false;
        return true;
    }

    /** Serializza un quartiere per comunicare i suoi dati in un messaggio di rete **/
    public synchronized ArrayList<HouseData> serialize() {
        ArrayList<HouseData> temp = new ArrayList<>();
        for (Casa casa : values())
            temp.add(casa.serialize());
        return temp;
    }

}

