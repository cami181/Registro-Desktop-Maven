package Altro;

import Utenti.Docente;

import java.util.Date;

public class Assenza {
    private String giustifica;
    private final Docente docente;
    private final Date data;

    public Assenza(Docente docente, Date data) {
        this.docente = docente;
        this.data = data;
    }

    public String getGiustifica() {
        return giustifica;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setGiustifica(String giustifica) {
        this.giustifica = giustifica;
    }

    public Date getData(){
        return data;
    }

    public int getMaxAssense(){
        return 45;
    }
}