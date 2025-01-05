package Utenti;
import Altro.*;

import java.util.*;

public class Studente extends Persona{
    private ArrayList<Voto> voti;
    private ArrayList<Nota> note;
    private ArrayList<Assenza> assenze;

    private Classe classe;

    public Studente(String nome, String congome, Date dataDiNascita, String CF, Classe classe) {
        super(nome, congome, dataDiNascita, CF);

        this.classe = classe;

        this.voti = new ArrayList<>();
        this.note = new ArrayList<>();
        this.assenze = new ArrayList<>();
    }

    public ArrayList<Voto> getVoti() {
        return voti;
    }

    public ArrayList<Nota> getNote() {
        return note;
    }

    public ArrayList<Assenza> getAssenze() {
        return assenze;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public double getMediaMensile(int mese){
        double media = 0;
        int qt = 0;
        for (Voto v: voti) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(v.getData());

            if(calendar.get(Calendar.MONTH)==mese){ // gennaio=0, febbraio=1..... dicembre=11
                media += v.getValore();
                qt++;
            }
        }

        if(qt==0){ //non ci sono voti
            return 0;
        }
        else{
            return media/qt;
        }
    }

    public double getMediaMateria(String materia){
        double media = 0;
        int qt = 0;
        for (Voto v: voti) {
            if(v.getMateria().equals(materia)){
                media += v.getValore();
                qt++;
            }
        }

        if(qt==0){ //non ci sono voti
            return 0;
        }
        else{
            return media/qt;
        }
    }

    public int getAssenzeMensili(int mese){
        int assenze = 0;
        for (Assenza a: this.assenze) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(a.getData());
            if(calendar.get(Calendar.MONTH)==mese){
                assenze++;
            }
        }
        return assenze;
    }
}
