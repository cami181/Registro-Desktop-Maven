package Utenti;
import Altro.*;

import java.util.ArrayList;
import java.util.Date;

public class Studente extends Persona{
    private ArrayList<Voto> voti;
    private ArrayList<String> note;
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

    public ArrayList<String> getNote() {
        return note;
    }

    public ArrayList<Assenza> getAssenze() {
        return assenze;
    }

    public Classe getClasse() {
        return classe;
    }

    public double getMedia(){
        double media = 0;
        for (Voto v: voti) {
            media += v.getValore();
        }
        return media/voti.size();
    }
}
