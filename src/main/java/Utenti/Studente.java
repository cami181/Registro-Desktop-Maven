package Utenti;
import Altro.*;

import java.util.*;

public class Studente extends Persona{
    private ArrayList<Voto> voti;
    private ArrayList<Nota> note;
    private ArrayList<Assenza> assenze;

    private Classe classe;

    /**
     * Funzione che serve per creare un nuovo studente.
     *
     * @param nome Nome studente.
     * @param congome Cognome studente.
     * @param dataDiNascita Data di nascita studente.
     * @param CF Codice fiscale studente.
     * @param classe Classe studente.
     */
    public Studente(String nome, String congome, Date dataDiNascita, String CF, Classe classe) {
        super(nome, congome, dataDiNascita, CF);

        this.classe = classe;

        this.voti = new ArrayList<>();
        this.note = new ArrayList<>();
        this.assenze = new ArrayList<>();
    }

    /**
     * Funzione che restituisce la lista dei voti dello studente.
     *
     * @return Nome studente.
     */
    public ArrayList<Voto> getVoti() {
        return voti;
    }

    /**
     * Funzione che restituisce i voti dello studente in formato stringa.
     *
     * @return Voti come stringa.
     */
    public String getStringVoti(){
        String s = "";
        for (Voto v: voti) {
            s += v.toString() + ";";
        }
        return s;
    }

    /**
     * Funzione che restituisce le note dello studente in formato stringa.
     *
     * @return Note come stringa.
     */
    public String getStringNote(){
        String s = "";
        for (Nota n: note) {
            s += n.toString() + ";";
        }
        return s;
    }

    /**
     * Funzione che restituisce le assenze dello studente in formato stringa.
     *
     * @return Assenze come stringa.
     */
    public String getStringAssenze(){
        String s = "";
        for (Assenza a: assenze) {
            s += a.toString() + ";";
        }
        return s;
    }

    /**
     * Funzione che imposta i voti dello studente in una lista.
     * @param voti Voti dello studente da impostare nella lista.
     */
    public void setVoti(ArrayList<Voto> voti) {
        this.voti = voti;
    }

    /**
     * Funzione che imposta le note dello studente in una lista.
     * @param note Note dello studente da impostare nella lista.
     */
    public void setNote(ArrayList<Nota> note) {
        this.note = note;
    }

    /**
     * Funzione che imposta le assenze dello studente in una lista.
     * @param assenze Assenze dello studente da impostare nella lista.
     */
    public void setAssenze(ArrayList<Assenza> assenze) {
        this.assenze = assenze;
    }

    /**
     * Funzione che restituisce la lista delle note dello studente.
     *
     * @return Note studente.
     */
    public ArrayList<Nota> getNote() {
        return note;
    }

    /**
     * Funzione che restituisce la lista delle assenze dello studente.
     *
     * @return Assenze studente.
     */
    public ArrayList<Assenza> getAssenze() {
        return assenze;
    }

    /**
     * Funzione che restituisce la classe a cui appartiene lo studente.
     *
     * @return Classe dello studente.
     */
    public Classe getClasse() {
        return classe;
    }

    /**
     * Funzione che imposta la classe allo studente.
     * @param classe Classe da impostare allo studente.
     */
    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    /**
     * Funzione che restituisce la media mensile dello studente.
     * @param mese Mese delle medie che si vuole sapere.
     * @return Media mensile dello studente.
     */
    public double getMediaMensile(int mese){
        double media = 0;
        int qt = 0;
        for (Voto v: voti) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(v.getData());
            if(calendar.get(Calendar.MONTH)==mese){ // gennaio=0, febbraio=1..... dicembre=11
                media += v.getValore();
                qt++;
            }
        }

        if(qt==0){ //non ci sono voti
            return 0;
        }
        else{
            return media/qt;
        }
    }

    /**
     * Funzione che restituisce la media di ogni materia dello studente.
     * @param materia Materia che di cui ci interessa sapere la media.
     * @return Media delle materie dello studente.
     */
    public double getMediaMateria(String materia){
        double media = 0;
        int qt = 0;
        for (Voto v: voti) {
            if(v.getMateria().equals(materia)){
                media += v.getValore();
                qt++;
            }
        }

        if(qt==0){ //non ci sono voti
            return 0;
        }
        else{
            return media/qt;
        }
    }

    /**
     * Funzione che restituisce le assenze mensili dello studente.
     * @param mese Mese di cui si vuole conoscere le assenze fatte dallo studente.
     * @return Assenze mensili dello studente.
     */
    public int getAssenzeMensili(int mese){
        int assenze = 0;
        for (Assenza a: this.assenze) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(a.getData());
            if(calendar.get(Calendar.MONTH)==mese){
                assenze++;
            }
        }
        return assenze;
    }
}
