package Utenti;

import Altro.Assenza;
import Altro.Voto;

import java.util.ArrayList;
import java.util.Calendar;

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

    public double getMediaMensile(int mese){
        double media = 0;
        for (Studente s: studenti) {
            media += s.getMediaMensile(mese);
        }

        if(studenti.isEmpty()){ //non ci sono studenti
            return 0;
        }
        else{
            return media/studenti.size();
        }
    }

    public double getMediaMateria(String materia){
        double media = 0;
        int qt = 0;
        for (Studente s: studenti) {
            media += s.getMediaMateria(materia);
            qt++;
        }

        if(qt==0){ //non ci sono studenti
            return 0;
        }
        else{
            return media/qt;
        }
    }

    @Override
    public String toString(){
        return (String.valueOf(anno) + sezione + " " + indirizzo).toLowerCase();
    }
}
