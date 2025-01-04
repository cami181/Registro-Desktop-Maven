package Controllore;

import Altro.Assenza;
import Altro.Voto;
import Utenti.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Controllore {
    // --- REGISTRAZIONE --- //
    public void registraStudente(Studente studente){
        //CARICA
    }

    public void registraDocente(Docente docente){
        //CARICA
    }

    public void registraGenitore(Genitore genitore){
        //CARICA
    }

    public boolean registraClasse(int anno, String corso, char sezione){
        ArrayList<Classe> classi = new ArrayList<>();
        classi.add(new Classe(4,"inf",'a')); //PROVA

        for (Classe c: classi) {
            if(c.getAnno()==anno && c.getIndirizzo().equals(corso) && c.getSezione()==sezione) return false;
        }
        Classe classe = new Classe(anno,corso,sezione); //DA REGISTRARE
        return true;
    }
    // --- FINE REGISTRAZIONE --- //

    //CODICE FISCALE------------------------------
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
    public ArrayList<String> getMaterie(){
        ArrayList<String> materie = new ArrayList<>();
        materie.add("Matematica");
        materie.add("Italiano");
        materie.add("Storia");
        materie.add("Informatica");
        materie.add("Sistemi");
        return materie;
    }

    public ArrayList<String> getIndirizzi(){
        ArrayList<String> indirizzi = new ArrayList<>();
        indirizzi.add("inf");
        indirizzi.add("tur");
        indirizzi.add("afm");
        indirizzi.add("cat");
        return indirizzi;
    }

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

    //GETTER-------------------

    //GETTER DAL WEB SERVER----------------------------------------------------------------------
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
}