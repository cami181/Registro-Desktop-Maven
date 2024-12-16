package Utenti;

import java.util.Date;

public class Genitore extends Persona{
    private Studente figlio;

    public Genitore(String nome, String congome, Date dataDiNascita, String CF, Studente figlio) {
        super(nome, congome, dataDiNascita, CF);

        this.figlio = figlio;
    }

    public Studente getFiglio() {
        return figlio;
    }
}
