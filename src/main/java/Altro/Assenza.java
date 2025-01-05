package Altro;

import Utenti.Docente;

import java.util.Date;

public class Assenza {
    private String giustifica;
    private final Docente docente;
    private final Date data;

    /**
     * Funzione che crea un'assenza con il rispettivo docente e la data di essa.
     *
     * @param docente Docente dell'assenza.
     * @param data Data dell'assenza.
     */
    public Assenza(Docente docente, Date data) {
        this.docente = docente;
        this.data = data;
    }

    /**
     * Restituisce la giustifica se presente l'assenza.
     *
     * @return La giustifica dell'assenza.
     */
    public String getGiustifica() {
        return giustifica;
    }

    /**
     * Restituisce docente relativo all'assenza.
     *
     * @return Il docente dell'assenza.
     */
    public Docente getDocente() {
        return docente;
    }

    /**
     * Imposta giustifica per l'assenza.
     *
     * @param giustifica Giustifica da associare alla realativa assenza.
     */
    public void setGiustifica(String giustifica) {
        this.giustifica = giustifica;
    }

    /**
     * Restituisce data dell'assenza.
     *
     * @return Data assenza.
     */
    public Date getData(){
        return data;
    }

    /**
     * Restituisce il numero max di assenze consentite per uno studente.
     *
     * @return Numero max assenze.
     */
    public int getMaxAssense(){
        return 45;
    }
}