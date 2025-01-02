package Controllore;

import Utenti.*;
import Credenziali.Credenziali;

import javax.print.Doc;
import java.util.ArrayList;

public class Controllore {
    // --- REGISTRAZIONE --- //
    public void registraStudente(Studente studente){
        String user = "s" + studente.getNome() + "." + studente.getCognome();
        String password = "";
        for(int i=0;i<user.length();i++) {
            if (user.charAt(i) != 'a' && user.charAt(i) != 'e' && user.charAt(i) != 'i' && user.charAt(i) != 'o' && user.charAt(i) != 'u' && user.charAt(i) != '.') {
                password += user.charAt(i);
            }
        }
        studente.setCredenziali(new Credenziali(user,password));
        //CARICA
    }

    public void registraDocente(Docente docente){
        String user = "d" + docente.getNome() + "." + docente.getCognome();
        String password = "";
        for(int i=0;i<user.length();i++) {
            if (user.charAt(i) != 'a' && user.charAt(i) != 'e' && user.charAt(i) != 'i' && user.charAt(i) != 'o' && user.charAt(i) != 'u' && user.charAt(i) != '.') {
                password += user.charAt(i);
            }
        }
        docente.setCredenziali(new Credenziali(user,password));
        //CARICA
    }

    public void registraGenitore(Genitore genitore){
        String user = "g" + genitore.getNome() + "." + genitore.getCognome();
        String password = "";
        for(int i=0;i<user.length();i++) {
            if (user.charAt(i) != 'a' && user.charAt(i) != 'e' && user.charAt(i) != 'i' && user.charAt(i) != 'o' && user.charAt(i) != 'u' && user.charAt(i) != '.') {
                password += user.charAt(i);
            }
        }
        genitore.setCredenziali(new Credenziali(user,password));
        //CARICA
    }

    public boolean registraClasse(int anno, String corso, char sezione){
        ArrayList<Classe> classi = new ArrayList<>();
        classi.add(new Classe(4,"inf",'a')); //PROVA

        for (Classe c: classi) {
            if(c.getAnno()==anno && c.getIndirizzo().equals(corso) && c.getSezione()==sezione) return false;
        }
        Classe classe = new Classe(anno,corso,sezione);
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
}