package Altro;

import Utenti.Docente;

import java.util.Calendar;
import java.util.Date;

public class Voto {
    private final double voto;
    private final String materia;
    private final Docente docente;
    private final Date data;

    /**
     * Funzione che crea un voto con: voto, materia, docente e data.
     *
     * @param voto Voto valutazione.
     * @param materia Materia relativa alla valutazione.
     * @param docente Docente che ha assegnato la valutazione.
     * @param data Data in cui è stato aggiunta la valutazione.
     */
    public Voto(double voto, String materia, Docente docente, Date data) {
        this.voto = voto;
        this.materia = materia;
        this.docente = docente;
        this.data = data;
    }

    /**
     * Restituisce valore voto.
     *
     * @return Valore valutazione.
     */
    public double getValore() {
        return voto;
    }

    /**
     * Restituisce la materia associata al voto.
     *
     * @return Nome materia.
     */
    public String getMateria() {
        return materia;
    }

    /**
     * Restituisce il docente che ha aggiunto il voto.
     *
     * @return Docente che ha assegnato il voto.
     */
    public Docente getDocente() {
        return docente;
    }

    /**
     * Restituisce la data dell'assegnazione del voto.
     *
     * @return Data voto.
     */
    public Date getData(){
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
     * Funzione che restituisce il voto in formato stringa.
     *
     * @return Stringa che rappresenta la nota.
     */
    public String toString(){
        if(docente==null){
            return voto + " " + materia + " " + "docente_nonregistrato" + " " + getStringData();
        }
        return voto + " " + materia + " " + docente.getNome() + "_" + docente.getCognome() + " " + getStringData();
    }
}
