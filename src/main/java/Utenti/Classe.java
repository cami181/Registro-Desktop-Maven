package Utenti;

import java.util.ArrayList;

public class Classe {
    private final int anno;
    private final String indirizzo;
    private final char sezione;

    private final ArrayList<Studente> studenti;

    public Classe(int anno, String indirizzo, char sezione) {
        this.anno = anno;
        this.indirizzo = indirizzo;
        this.sezione = sezione;
        studenti = new ArrayList<>();
    }

    public int getAnno() {
        return anno;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public char getSezione() {
        return sezione;
    }

    public ArrayList<Studente> getStudenti() {
        return studenti;
    }

    public double getMediaClasse(){
        double media = 0;
        for (Studente s: studenti) {
            media += s.getMedia();
        }
        return media/studenti.size();
    }

    @Override
    public String toString(){
        return String.valueOf(anno) + sezione + " " + indirizzo;
    }
}
