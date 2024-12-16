package Altro;

import Utenti.Docente;

import java.util.Date;

public class Nota {
    private Date data;
    private Docente docente;
    private String motivo;

    public Nota(Date data, Docente docente, String motivo) {
        this.data = data;
        this.docente = docente;
        this.motivo = motivo;
    }

    public Date getData() {
        return data;
    }

    public Docente getDocente() {
        return docente;
    }

    public String getMotivo() {
        return motivo;
    }
}
