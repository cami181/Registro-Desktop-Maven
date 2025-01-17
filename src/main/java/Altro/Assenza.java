package Altro;

import Utenti.Docente;

import java.util.Calendar;
import java.util.Date;

public class Assenza {
    private boolean giustifica;
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
     * Funzione che crea un'assenza associando docente, data e giustifica.
     *
     * @param docente Docente associato all'assenza.
     * @param data Data assenza.
     * @param giustifica Indica se l'assenza è giustificata.
     */
    public Assenza(Docente docente, Date data, boolean giustifica){
        this.docente = docente;
        this.data = data;
        this.giustifica = giustifica;
    }

    /**
     * Restituisce la giustifica se presente l'assenza.
     *
     * @return La giustifica dell'assenza.
     */
    public boolean getGiustifica() {
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
    public void setGiustifica(boolean giustifica) {
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
     * Funzione che restituisce la data dell'assenza in formato giorno-mese-anno.
     *
     * @return Data assenza come stringa formattata.
     */
    public String getStringData(){
        Calendar calendar = Calendar. getInstance();
        calendar.setTime(data);
        return calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.YEAR);
    }

    /**
     * Funzione che restituisce l'assenza in formato stringa.
     *
     * @return Stringa che rappresenta l'assenza.
     */
    public String toString(){
        if(docente==null){
            if(giustifica){
                return getStringData() + " " +  "docente_nonregistrato" + " true";
            }
            return getStringData() + " " + "docente_nonregistrato" + " false";
        }
        else{
            if(giustifica){
                return getStringData() + " " + docente.getNome() + "_" + docente.getCognome() + " true";
            }
            return getStringData() + " " + docente.getNome() + "_" + docente.getCognome() + " false";
        }
    }
}