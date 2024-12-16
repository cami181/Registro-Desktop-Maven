package Altro;

import Utenti.Docente;

import java.util.Date;

public class Assenza {
    private String giustifica;
    private Docente docente;
    private Date data;

    public Assenza(Date data) {
        this.data = data;
    }

    public Assenza(String giustifica) {
        this.giustifica = giustifica;
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

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Date getData(){
        return data;
    }

    public int getMaxAssense(){
        return 45;
    }
}
