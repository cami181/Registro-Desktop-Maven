package Controllore;

import Utenti.*;
import Credenziali.Credenziali;

import java.util.ArrayList;

public class Controllore {
    // ---- INIZIO MODIFCA UTENTI ---- //
    public void modificaStudente(Studente studente_temp, Studente studente) {
        studente.setNome(studente_temp.getNome());
        studente.setCognome(studente_temp.getCognome());
        studente.setCF(studente_temp.getCF());
        studente.setDataDiNascita(studente_temp.getDataDiNascita());
        studente.setCredenziali(studente_temp.getCredenziali());
    }

    public void modificaDocente(Docente docente_temp, Docente docente) {
        docente.setNome(docente_temp.getNome());
        docente.setCognome(docente_temp.getCognome());
        docente.setCF(docente_temp.getCF());
        docente.setDataDiNascita(docente_temp.getDataDiNascita());
        docente.setCredenziali(docente_temp.getCredenziali());
    }

    public void modificaGenitore(Genitore genitore_temp, Genitore genitore) {
        genitore.setNome(genitore_temp.getNome());
        genitore.setCognome(genitore_temp.getCognome());
        genitore.setCF(genitore_temp.getCF());
        genitore.setDataDiNascita(genitore_temp.getDataDiNascita());
        genitore.setCredenziali(genitore_temp.getCredenziali());
    }
    // ---- FINE MODIFCA UTENTI ---- //

    // --- REGISTRAZIONE --- //
    public void registrazione(Persona persona) {
        String user = persona.getNome() + "." + persona.getCognome();
        String password = "";
        for(int i=0;i<user.length();i++){
            if(user.charAt(i)!='a' && user.charAt(i)!='e' && user.charAt(i)!='i' && user.charAt(i)!='o' && user.charAt(i)!='u' && user.charAt(i)!='.'){
                password += user.charAt(i);
            }
        }
        Credenziali credenziali = new Credenziali(user,password);

        if(persona instanceof Studente) {
            Studente s = (Studente) persona;
            s.setCredenziali(credenziali);
        } else if(persona instanceof Genitore) {
            Genitore g = (Genitore) persona;
            g.setCredenziali(credenziali);
        } else {
            Docente d = (Docente) persona;
            d.setCredenziali(credenziali);
        }
    }
    // --- FINE REGISTRAZIONE --- //

    //CODICE FISCALE------------------------------
    public boolean checkCodiceFiscale(String cf){
        //lunghezza di 16
        if(cf.length()!=16) return false;

        //NUMERI
        Integer[] in = new Integer[]{6,7,9,10,12,13,14};
        for (Integer i: in) {
            if(!Character.isDigit(cf.charAt(i))) return false;
        }

        //CARATTERI
        in = new Integer[]{0,1,2,3,4,5,8,11,15};
        for (Integer i: in) {
            if(Character.isDigit(cf.charAt(i))) return false;
        }

        return true;
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