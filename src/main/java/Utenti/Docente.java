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
}
