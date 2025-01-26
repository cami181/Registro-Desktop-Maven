package Utenti;

import java.util.ArrayList;

public class Classe {
    private final int anno;
    private final String indirizzo;
    private final char sezione;
    private final ArrayList<Studente> studenti;

    /**
     * Funzione che inzializza una nuova classe.
     *
     * @param anno Anno della classe.
     * @param indirizzo Indirizzo della classe.
     * @param sezione Sezione della classe.
     */
    public Classe(int anno, String indirizzo, char sezione) {
        this.anno = anno;
        this.indirizzo = indirizzo;
        this.sezione = sezione;
        studenti = new ArrayList<>();
    }

    /**
     * Restituisce l'anno della classe.
     *
     * @return Anno classe.
     */
    public int getAnno() {
        return anno;
    }

    /**
     * Restituisce l'indirizzo della classe.
     *
     * @return Indirizzo classe.
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Restituisce la sezione della classe.
     *
     * @return Sezione classe.
     */
    public char getSezione() {
        return sezione;
    }

    /**
     * Restituisce la lista degli studenti della classe.
     *
     * @return Lista studenti classe.
     */
    public ArrayList<Studente> getStudenti() {
        return studenti;
    }

    /**
     * Metodo che serve per calcolare la media mensile degli studenti della classe.
     *
     * @param mese Mese riferito alla media da calcolare (1 = gennaio, 2 = febbraio etc...).
     * @return Media mensile studenti nel mese specificato.
     */
    public double getMediaMensile(int mese){
        double media = 0;
        int qt = 0;
        for (Studente s: studenti) {
            if(s.getMediaMensile(mese)>=0){
                media += s.getMediaMensile(mese);
                qt++;
            }
        }

        if(qt==0){ //non ci sono studenti
            return 0;
        }
        else{
            return media/studenti.size();
        }
    }

    /**
     * Metodo che serve per calcolare la media di ciascuna di tutti gli studenti della classe.
     *
     * @param materia Materia per la quale calcolare la media.
     * @return Media materia per gli studenti della classe.
     */
    public double getMediaMateria(String materia){
        double media = 0;
        int qt = 0;
        for (Studente s: studenti) {
            if(s.getMediaMateria(materia)>=0){
                media += s.getMediaMateria(materia);
                qt++;
            }
        }

        if(qt==0){ //non ci sono studenti
            return 0;
        }
        else{
            return media/qt;
        }
    }

    /**
     * Metodo che serve per restituire la classe in formato stringa.
     * Include: anno, sezione e indirizzo (tutto in minuscolo).
     *
     * @return Stringa che rappresenta la classe.
     */
    @Override
    public String toString(){
        return (String.valueOf(anno) + sezione + indirizzo).toLowerCase();
    }
}
