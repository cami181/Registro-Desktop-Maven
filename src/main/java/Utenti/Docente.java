package Utenti;

import java.util.*;

public class Docente extends Persona{
    private ArrayList<Classe> classi;
    private ArrayList<String> materie;

    public Docente(String nome, String congome, Date dataDiNascita, String CF, ArrayList<Classe> classe, ArrayList<String> materie) {
        super(nome, congome, dataDiNascita, CF);

        this.classi = classe;
        this.materie = materie;
    }

    public ArrayList<Classe> getClassi() {
        return classi;
    }

    public ArrayList<String> getMaterie() {
        return materie;
    }

    public void setClassi(ArrayList<Classe> classi) {
        this.classi = classi;
    }

    public void setMaterie(ArrayList<String> materie) {
        this.materie = materie;
    }

    public String getStringMaterie(){
        String s = "";
        for (String tmp: materie) {
            s += tmp + ";";
        }
        return s;
    }
    public String getStringClassi(){
        String s = "";
        for (Classe c: classi) {
            s += c.toString() + ";";
        }
        return s;
    }
}
