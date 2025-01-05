package Altro;

import Utenti.Docente;

import java.util.Date;

public class Nota {
    private Date data;
    private Docente docente;
    private String motivo;

    /**
     * Funazione che crea una nota con: data, docente e motivo.
     *
     * @param data Data scrittura nota.
     * @param docente Docente che scrive la nota.
     * @param motivo Motivo della nota.
     */
    public Nota(Date data, Docente docente, String motivo) {
        this.data = data;
        this.docente = docente;
        this.motivo = motivo;
    }

    /**
     * Restituisce la data in cui è stata scritta la nota.
     *
     * @return Data nota.
     */
    public Date getData() {
        return data;
    }

    /**
     * Restituisce il docente che ha scritto la nota.
     *
     * @return Docente della nota.
     */
    public Docente getDocente() {
        return docente;
    }

    /**
     * Restituisce il motivo della nota.
     *
     * @return Motivo nota.
     */
    public String getMotivo() {
        return motivo;
    }
}
