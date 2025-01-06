package Altro;

import Utenti.Docente;

import java.util.Calendar;
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
     * Funzione che restituisce data nota in formato giorno-mese-anno.
     *
     * @return Data nota formattata in stringa.
     */
    public String getStringData(){
        Calendar calendar = Calendar. getInstance();
        calendar.setTime(data);
        return calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.YEAR);
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

    /**
     * Funzione che restituisce la nota in formato stringa.
     *
     * @return Stringa che rappresenta la nota.
     */
    public String toString(){
        if(docente==null){
            return getStringData() + "*" + "docente_nonregistrato" + "*Motivo_" + motivo;
        }
        return getStringData() + "*" + docente.getNome() + "_" + docente.getCognome() + "*Motivo_" + motivo;
    }
}
