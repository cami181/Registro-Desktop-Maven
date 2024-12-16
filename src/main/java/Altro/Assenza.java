package Altro;

import Utenti.Docente;

public class Assenza {
    private String giustifica;
    private Docente docente;

    public Assenza(String giustifica) {
        this.giustifica = giustifica;
    }

    public Assenza() {
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
}
