package Controllore;

import Altro.Assenza;
import Altro.Voto;
import Utenti.*;
import java.util.*;
import webserver.WebServer;

import javax.swing.*;

public class Controllore {
    // --- REGISTRAZIONE --- //

    /**
     * Registra nuovo studente.
     *
     * @param studente Studente da registrare.
     */
    public void registraStudente(Studente studente){
        Date data = new GregorianCalendar(2002, Calendar.DECEMBER,20).getTime(); //PROVA
        studente.getVoti().add(new Voto(7,"Informatica",new Docente("Laura","Fallini",data,"aaaa",new ArrayList<>(),new ArrayList<>()),data));
        studente.getVoti().add(new Voto(2,"Matematica",new Docente("Laura","Fallini",data,"aaaa",new ArrayList<>(),new ArrayList<>()),data));
        studente.getAssenze().add(new Assenza(new Docente("Laura","Fallini",data,"aaaa",new ArrayList<>(),new ArrayList<>()),data));
        studente.getAssenze().add(new Assenza(new Docente("Laura","Fallini",data,"aaaa",new ArrayList<>(),new ArrayList<>()),data));
        studente.getAssenze().add(new Assenza(new Docente("Laura","Fallini",data,"aaaa",new ArrayList<>(),new ArrayList<>()),data));

        WebServer.registerUser("registrazione",studente.getCredenziali().getUser(),studente.getCredenziali().getPassword(),new JTextArea());
        WebServer.sendRequestStudente("carica",studente,new JTextArea());
    }

    /**
     * Registra nuovo docente.
     *
     * @param docente Docente da registrare.
     */
    public void registraDocente(Docente docente){
        WebServer.registerUser("registrazione",docente.getCredenziali().getUser(),docente.getCredenziali().getPassword(),new JTextArea());
        WebServer.sendRequestDocente("registrazione",docente,new JTextArea());
    }

    /**
     * Registra nuovo genitore.
     *
     * @param genitore Genitore da registrare.
     */
    public void registraGenitore(Genitore genitore){
        WebServer.registerUser("registrazione",genitore.getCredenziali().getUser(),genitore.getCredenziali().getPassword(),new JTextArea());
        WebServer.sendRequestGenitore("registrazione",genitore,new JTextArea());
    }

    /**
     * Registra nuova classe se non è già presente nella lista.
     *
     * @param anno Anno classe.
     * @param corso Indirizzo corso della classe.
     * @param sezione Sezione classe.
     * @return true se la classe è stata registrata, false se già presente nella lista.
     */
    public boolean registraClasse(int anno, String corso, char sezione){
        ArrayList<Classe> classi = new ArrayList<>();
        classi.add(new Classe(4,"inf",'a')); //PROVA

        for (Classe c: classi) {
            if(c.getAnno()==anno && c.getIndirizzo().equals(corso) && c.getSezione()==sezione) return false;
        }
        Classe classe = new Classe(anno,corso,sezione);
        WebServer.sendRequestClasse("registrazione",classe,null);
        return true;
    }
    // --- FINE REGISTRAZIONE --- //

    //CODICE FISCALE------------------------------

    /**
     * Verifica validità del codice fiscale.
     *
     * @param cf Codice fiscale da verificare.
     * @return true se il codice fiscale è errato, false se corretto.
     */
    public boolean codiceFiscaleInvalido(String cf){
        //lunghezza di 16
        if(cf.length()!=16) return true;

        //NUMERI
        Integer[] in = new Integer[]{6,7,9,10,12,13,14};
        for (Integer i: in) {
            if(!Character.isDigit(cf.charAt(i))) return true;
        }

        //CARATTERI
        in = new Integer[]{0,1,2,3,4,5,8,11,15};
        for (Integer i: in) {
            if(Character.isDigit(cf.charAt(i))) return true;
        }

        return false;
    }

    /**
     * Verifica il codice fiscale se già presente nella lista o no.
     *
     * @param cf Codice fiscale da verificare.
     * @return true se il codice fiscale esiste già, false se è nuovo.
     */
    public boolean alreadyExistentCf(String cf){
        ArrayList<Persona> utenti = new ArrayList<>();
        utenti.add(new Studente("ciao","bro",null,"0000000000000000",null));

        for (Persona p: utenti) {
            if(p.getCF().equals(cf)) return true;
        }

        return false;
    }
    //CODICE FISCALE-------------------------------

    //GETTER------------------

    /**
     * Restituisce lista delle materie disponibili.
     *
     * @return Lista delle materie.
     */
    public ArrayList<String> getMaterie(){
        ArrayList<String> materie = new ArrayList<>();
        materie.add("Matematica");
        materie.add("Italiano");
        materie.add("Storia");
        materie.add("Informatica");
        materie.add("Sistemi");
        return materie;
    }

    /**
     * Restituisce lista degli indirizzi disponibili.
     *
     * @return Lista degli indirizzi.
     */
    public ArrayList<String> getIndirizzi(){
        ArrayList<String> indirizzi = new ArrayList<>();
        indirizzi.add("inf");
        indirizzi.add("tur");
        indirizzi.add("afm");
        indirizzi.add("cat");
        return indirizzi;
    }
    //GETTER-------------------

    //GETTER DAL WEB SERVER----------------------------------------------------------------------

    /**
     * Restituisce lista degli studenti registrati.
     *
     * @return Lista degli studenti registrati.
     */
    public ArrayList<Studente> getStudenti(){
        Date data = new GregorianCalendar(2002, Calendar.DECEMBER,20).getTime(); //PROVA
        Studente s = new Studente("c","co",data,"cccccc00cccc000c",new Classe(5,"inf",'B'));
        Studente s1 = new Studente("c","co2",data,"cccccc11c11c111c ",new Classe(4,"inf",'B'));
        s.getVoti().add(new Voto(3,"Italiano",null,data));
        s.getVoti().add(new Voto(6,"Informatica",null,data));
        s1.getVoti().add(new Voto(7,"Italiano",null,data));
        s1.getVoti().add(new Voto(7,"Informatica",null,data));
        s1.getAssenze().add(new Assenza(null,data));
        s1.getAssenze().add(new Assenza(null,data));
        s1.getAssenze().add(new Assenza(null,data));
        s1.getAssenze().add(new Assenza(null,data));
        s1.getAssenze().add(new Assenza(null,data));

        ArrayList<Studente> studenti = new ArrayList<>();
        studenti.add(s);
        studenti.add(s1);
        return studenti;
    }

    /**
     * Restituisce lista delle classi registrate.
     *
     * @return Lista delle classi registrate.
     */
    public ArrayList<Classe> getClassi(){
        Date data = new GregorianCalendar(2002, Calendar.DECEMBER,20).getTime(); //PROVA
        Studente s = new Studente("c","co",data,"cccccc00cccc000c",new Classe(5,"inf",'B'));
        Studente s1 = new Studente("c","co2",data,"cccccc11c11c111c ",new Classe(4,"inf",'B'));
        s1.getVoti().add(new Voto(7,"Informatica",null,data));
        s.getVoti().add(new Voto(3,"Italiano",null,data));
        s.getVoti().add(new Voto(6,"Informatica",null,data));
        s1.getVoti().add(new Voto(7,"Italiano",null,data));
        ArrayList<Classe> classi = new ArrayList<>();
        classi.add(new Classe(5,"inf",'B'));
        classi.add(new Classe(4,"inf",'B'));
        classi.get(0).getStudenti().add(s);
        classi.get(0).getStudenti().add(s1);
        return classi;
    }
}