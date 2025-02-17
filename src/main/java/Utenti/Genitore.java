package Utenti;

import java.util.Date;

public class Genitore extends Persona{
    private Studente figlio;

    /**
     * Funzione che serve per creare un nuovo genitore.
     *
     * @param nome Nome genitore.
     * @param congome Cognome genitore.
     * @param dataDiNascita Data di nascita genitore.
     * @param CF Codice fiscale genitore.
     * @param figlio Studente associato al genitore.
     */
    public Genitore(String nome, String congome, Date dataDiNascita, String CF, Studente figlio) {
        super(nome, congome, dataDiNascita, CF);
        this.figlio = figlio;
    }

    /**
     * Funzione che restituisce lo studente appartenente al genitore.
     *
     * @return Studente associato al genitore.
     */
    public Studente getFiglio() {
        return figlio;
    }

    /**
     * Funzione che imposta lo studente associato al genitore.
     *
     * @param figlio Studente da associare al genitore.
     */
    public void setFiglio(Studente figlio){
        this.figlio = figlio;
    }
}
