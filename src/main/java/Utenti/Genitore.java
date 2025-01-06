package Utenti;

import java.util.Date;

public class Genitore extends Persona{
    private String cfFiglio;

    public Genitore(String nome, String congome, Date dataDiNascita, String CF, String figlio) {
        super(nome, congome, dataDiNascita, CF);
        this.cfFiglio = figlio;
    }

    public String getFiglio() {
        return cfFiglio;
    }

    public void setFiglio(String figlio){
        this.cfFiglio = figlio;
    }
}
