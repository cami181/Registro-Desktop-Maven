package Controllore;

import Altro.Assenza;
import Altro.Nota;
import Altro.Voto;
import Credenziali.Credenziali;
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
        /*Date data = new GregorianCalendar(2002, Calendar.DECEMBER,20).getTime();
        Date data2 = new GregorianCalendar(2002, Calendar.SEPTEMBER,20).getTime();
        studente.getVoti().add(new Voto(7,"Informatica",new Docente("Laura","Fallini",data,"aaaa",new ArrayList<>(),new ArrayList<>()),data));
        studente.getVoti().add(new Voto(2,"Matematica",new Docente("Laura","Fallini",data2,"aaaa",new ArrayList<>(),new ArrayList<>()),data2));
        //studente.getNote().add(new Nota(data,new Docente("Laura","Fallini",data,"aaaa",new ArrayList<>(),new ArrayList<>()),"Zavorra"));
        studente.getAssenze().add(new Assenza(new Docente("Laura","Fallini",data,"aaaa",new ArrayList<>(),new ArrayList<>()),data));
        studente.getAssenze().add(new Assenza(new Docente("Laura","Fallini",data2,"aaaa",new ArrayList<>(),new ArrayList<>()),data));
        studente.getAssenze().add(new Assenza(new Docente("Laura","Fallini",data,"aaaa",new ArrayList<>(),new ArrayList<>()),data));*/

        WebServer.registerUser("registrazione",studente.getCredenziali().getUser(),studente.getCredenziali().getPassword(),new JTextArea());
        WebServer.creaEliminaStudente("carica",studente,new JTextArea());
    }

    /**
     * Registra nuovo docente.
     *
     * @param docente Docente da registrare.
     */
    public void registraDocente(Docente docente){
        WebServer.registerUser("registrazione",docente.getCredenziali().getUser(),docente.getCredenziali().getPassword(),new JTextArea());
        WebServer.creaEliminaDocente("carica",docente,new JTextArea());
    }

    /**
     * Registra nuovo genitore.
     *
     * @param genitore Genitore da registrare.
     */
    public void registraGenitore(Genitore genitore){
        WebServer.registerUser("registrazione",genitore.getCredenziali().getUser(),genitore.getCredenziali().getPassword(),new JTextArea());
        WebServer.creaEliminaGenitore("carica",genitore,new JTextArea());
    }

    /**
     * Registra nuova classe.
     *
     * @param anno Anno classe.
     * @param corso Indirizzo corso della classe.
     * @param sezione Sezione classe.
     */
    public void registraClasse(int anno, String corso, char sezione){
        Classe classe = new Classe(anno,corso,sezione);
        WebServer.creaEliminaClasse("carica",classe,new JTextArea());
    }
    // --- FINE REGISTRAZIONE --- //

    // --- ELIMINAZIONE --- //

    /**
     * Metodo che permette di eliminare un utente dal sistema.
     *
     * @param user Nome dell'utente da eliminare.
     * @param password Password dell'utente da eliminare.
     */
    public void eliminaUtente(String user, String password){
        WebServer.registerUser("elimina",user,password,new JTextArea());
    }

    /**
     * Metodo che permette di eliminare uno studente dal sistema.
     *
     * @param studente Studente da eliminare.
     */
    public void eliminaStudente(Studente studente){
        eliminaUtente(studente.getCredenziali().getUser(),studente.getCredenziali().getPassword());
        if(studente.getClasse()==null){
            studente.setClasse(new Classe(0,"000",'0'));
        }
        WebServer.creaEliminaStudente("elimina",studente,new JTextArea());
    }

    /**
     * Metodo che permette di eliminare un docente dal sistema.
     *
     * @param docente Studente da eliminare.
     */
    public void eliminaDocente(Docente docente){
        eliminaUtente(docente.getCredenziali().getUser(),docente.getCredenziali().getPassword());
        WebServer.creaEliminaDocente("elimina",docente,new JTextArea());
    }

    /**
     * Metodo che permette di eliminare un genitore dal sistema.
     *
     * @param genitore Studente da eliminare.
     */
    public void eliminaGenitore(Genitore genitore){
        eliminaUtente(genitore.getCredenziali().getUser(),genitore.getCredenziali().getPassword());
        Date data = new GregorianCalendar(0, Calendar.JANUARY,1).getTime();
        if(genitore.getFiglio()==null){
            genitore.setFiglio(new Studente("nome","cognome",data,"0",new Classe(0,"0",'0')));
        }
        WebServer.creaEliminaGenitore("elimina",genitore,new JTextArea());
    }

    /**
     * Metodo che permette di eliminare una classe dal sistema.
     *
     * @param anno Anno della classe da eliminare.
     * @param indirizzo Indirizzo della classe da eliminare.
     * @param sezione Sezione della classe da eliminare.
     */
    public void eliminaClasse(int anno, String indirizzo, char sezione){
        WebServer.creaEliminaClasse("elimina",new Classe(anno,indirizzo,sezione),new JTextArea());
    }
    // --- ELIMINAZIONE --- //

    //CONTROLLI------------------------------

    /**
     * Verifica che la classe non esista già
     * @param anno anno della classe
     * @param indirizzo indirizzo della classe
     * @param sezione sezione della classe
     * @return true se la classe esiste già, false se non esiste
     */
    public boolean alreadyExistentClass(int anno, String indirizzo, char sezione){
        Classe tmp = new Classe(anno,indirizzo,sezione);
        for (Classe c: getClassi()) {
            if(c.toString().equals(tmp.toString())) return true;
        }
        registraClasse(anno, indirizzo, sezione);
        return false;
    }

    /**
     * Verifica validità del codice fiscale.
     *
     * @param cf Codice fiscale da verificare.
     * @return true se il codice fiscale è errato, false se corretto.
     */
    public boolean codiceFiscaleInvalido(String cf){
        cf = cf.trim().toLowerCase();
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
        cf = cf.trim().toLowerCase();
        ArrayList<Persona> utenti = new ArrayList<>();
        utenti.add(new Studente("ciao","bro",null,"0000000000000000",null));

        for (Persona p: utenti) {
            if(p.getCF().equals(cf)) return true;
        }

        return false;
    }
    //CONTROLLI-------------------------------

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
        /*Date data = new GregorianCalendar(2002, Calendar.DECEMBER,20).getTime(); //PROVA
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
        s1.getAssenze().add(new Assenza(null,data));*/

        ArrayList<Studente> studenti = new ArrayList<>();

        String s = WebServer.getStudenti();
        String[] s2 = s.split("\\[");
        if(s2.length<2){
            return studenti;
        }
        String[] s3 = s2[1].split("]");

        String[] studentiSplit = s3[0].split("\\{");

        for (String tmp: studentiSplit) {
            tmp = tmp.split("}")[0];
            String[] temp_split = tmp.split(",");
            if(temp_split.length>1) {
                String username = temp_split[0].split(":")[1].substring(1);
                username = username.substring(0,username.length()-1);
                String password = temp_split[1].split(":")[1].substring(1);
                password = password.substring(0,password.length()-1);
                String nome = temp_split[2].split(":")[1].substring(1);
                nome = nome.substring(0,nome.length()-1);
                String cognome = temp_split[3].split(":")[1].substring(1);
                cognome = cognome.substring(0,cognome.length()-1);
                //data
                String dataString = temp_split[4].split(":")[1].substring(1);
                dataString = dataString.substring(0,dataString.length()-1);
                int giorno = Integer.parseInt(dataString.split("-")[0]);
                int mese = Integer.parseInt(dataString.split("-")[1]);
                int anno = Integer.parseInt(dataString.split("-")[2]);
                Calendar c = Calendar.getInstance();
                c.set(anno, mese, giorno);
                Date data = c.getTime();

                String cf = temp_split[5].split(":")[1].substring(1);
                cf = cf.substring(0,cf.length()-1);

                String classeStringa = temp_split[6].split(":")[1].substring(1);
                classeStringa = classeStringa.substring(0,classeStringa.length()-1);
                Classe classe = null;
                for (Classe x: getClassi()) {
                    if(x.toString().equals(classeStringa)){
                        classe = x;
                    }
                }
                //voti
                String votiStringa = temp_split[7].split(":")[1].substring(1);
                votiStringa = votiStringa.substring(0,votiStringa.length()-1);
                ArrayList<Voto> voti = new ArrayList<>();

                for (String v: votiStringa.split(";")) {
                    if(!v.isEmpty()){
                        double valore = Double.parseDouble(v.split(" ")[0]);
                        String materia = v.split(" ")[1];
                        String nomeDoc = v.split(" ")[2].split("_")[0];
                        String cognomeDoc = v.split(" ")[2].split("_")[1];
                        Docente docente = null;
                        for (Docente d: getDocenti()) {
                            if(nomeDoc.equals(d.getNome()) && cognomeDoc.equals(d.getCognome())){
                                docente = d;
                                break;
                            }
                        }
                        String dataVoto = temp_split[4].split(":")[1].substring(1);
                        dataVoto = dataVoto.substring(0,dataVoto.length()-1);
                        int gv = Integer.parseInt(dataVoto.split("-")[0]);
                        int mv = Integer.parseInt(dataVoto.split("-")[1]);
                        int av = Integer.parseInt(dataVoto.split("-")[2]);
                        Calendar cv = Calendar.getInstance();
                        cv.set(av, mv, gv);
                        System.out.println(av + " " + mv + " " + gv);
                        Date datavoto = cv.getTime();
                        voti.add(new Voto(valore,materia,docente,datavoto));
                    }
                }

                //note
                String noteStringa = temp_split[8].split(":")[1].substring(1);
                noteStringa = noteStringa.substring(0,noteStringa.length()-1);
                ArrayList<Nota> note = new ArrayList<>();
                for (String n: noteStringa.split(";")) {
                    if(!n.isEmpty()){
                        String dataString2 = n.split("\\*")[0];
                        int gv = Integer.parseInt(dataString2.split("-")[0]);
                        int mv = Integer.parseInt(dataString2.split("-")[1]);
                        int av = Integer.parseInt(dataString2.split("-")[2]);
                        Calendar cv = Calendar.getInstance();
                        cv.set(av, mv, gv);
                        Date dataNota = cv.getTime();
                        String docenteNome = n.split("\\*")[1].split("_")[0];
                        String docenteCognome = n.split("\\*")[1].split("_")[1];
                        Docente docente = null;
                        for (Docente d: getDocenti()) {
                            if(docenteNome.equals(d.getNome()) && docenteCognome.equals(d.getCognome())){
                                docente = d;
                                break;
                            }
                        }
                        String motivo = n.split("\\*")[2].split("_")[1].trim();
                        note.add(new Nota(dataNota,docente,motivo));
                    }
                }

                //assenze
                String assenzeStringa = temp_split[9].split(":")[1].substring(1);
                assenzeStringa = assenzeStringa.substring(0,assenzeStringa.length()-1);
                ArrayList<Assenza> assenze = new ArrayList<>();
                for (String a: assenzeStringa.split(";")) {
                    if(!a.isEmpty()){
                        String dataAssenza = a.split(" ")[0];
                        int gv = Integer.parseInt(dataAssenza.split("-")[0]);
                        int mv = Integer.parseInt(dataAssenza.split("-")[1]);
                        int av = Integer.parseInt(dataAssenza.split("-")[2]);
                        Calendar cv = Calendar.getInstance();
                        cv.set(av, mv, gv);
                        Date dataNota = cv.getTime();

                        String docenteNome = a.split(" ")[1].split("_")[0];
                        String docenteCognome = a.split(" ")[1].split("_")[1];
                        Docente docente = null;
                        for (Docente d: getDocenti()) {
                            if(docenteNome.equals(d.getNome()) && docenteCognome.equals(d.getCognome())){
                                docente = d;
                                break;
                            }
                        }
                        String giustifica = a.split(" ")[2];
                        if(giustifica.equals("true")){
                            assenze.add(new Assenza(docente,dataNota,true));
                        }
                        else{
                            assenze.add(new Assenza(docente,dataNota,false));
                        }
                    }
                }
                Studente studente = new Studente(nome,cognome,data,cf,classe);
                studente.setCredenziali(new Credenziali(username,password));
                studente.setVoti(voti);
                studente.setNote(note);
                studente.setAssenze(assenze);

                studenti.add(studente);
            }
        }
        return studenti;
    }

    /**
     * Restituisce lista delle classi registrate.
     *
     * @return Lista delle classi registrate.
     */
    public ArrayList<Classe> getClassi(){
        /*Date data = new GregorianCalendar(2002, Calendar.DECEMBER,20).getTime(); //PROVA
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
        classi.get(0).getStudenti().add(s1);*/

        ArrayList<Classe> classi = new ArrayList<>();

        String c = WebServer.getClassi();
        String[] s2 = c.split("\\[");
        if(s2.length<2){
            return classi;
        }
        String[] s3 = s2[1].split("]");

        String[] classiSplit = s3[0].split("\\{");

        for (String tmp: classiSplit) {
            tmp = tmp.split("}")[0];
            String[] temp_split = tmp.split(",");
            if(temp_split.length>1){
                int anno = Integer.parseInt(String.valueOf(temp_split[0].split(":")[1].charAt(1)));
                String indirizzo = temp_split[1].split(":")[1].substring(1,4);
                char sezione = temp_split[2].split(":")[1].charAt(1);
                Classe cl = new Classe(anno,indirizzo,sezione);
                classi.add(cl);
            }
        }
        return classi;
    }

    /**
     * Restituisce lista dei genitori registrati.
     *
     * @return Lista dei genitori registrati.
     */
    public ArrayList<Genitore> getGenitori(){
        String s = WebServer.getGenitori();
        ArrayList<Genitore> genitori = new ArrayList<>();

        String[] s2 = s.split("\\[");

        if(s2.length<2){
            return genitori;
        }

        String[] s3 = s2[1].split("]");
        String[] genitoriSplit = s3[0].split("\\{");

        for (String tmp: genitoriSplit) {
            tmp = tmp.split("}")[0];
            String[] temp_split = tmp.split(",");
            if(temp_split.length>1){
                String username = temp_split[0].split(":")[1].substring(1);
                username = username.substring(0,username.length()-1);

                String password = temp_split[1].split(":")[1].substring(1);
                password = password.substring(0,password.length()-1);
                String nome = temp_split[2].split(":")[1].substring(1);
                nome = nome.substring(0,nome.length()-1);
                String cognome = temp_split[3].split(":")[1].substring(1);
                cognome = cognome.substring(0,cognome.length()-1);
                //data
                String dataString = temp_split[4].split(":")[1].substring(1);
                dataString = dataString.substring(0,dataString.length()-1);
                int giorno = Integer.parseInt(dataString.split("-")[0]);
                int mese = Integer.parseInt(dataString.split("-")[1]);
                int anno = Integer.parseInt(dataString.split("-")[2]);
                Calendar c = Calendar.getInstance();
                c.set(anno, mese, giorno);
                Date data = c.getTime();

                String cf = temp_split[5].split(":")[1].substring(1);
                cf = cf.substring(0,cf.length()-1);
                //cf figlio
                String cfFiglio = temp_split[6].split(":")[1].substring(1);
                cfFiglio = cfFiglio.substring(0,cfFiglio.length()-1);
                Studente figlio = null;
                for (Studente st: getStudenti()) {
                    if(cfFiglio.equals(st.getCF())){
                        figlio = st;
                        break;
                    }
                }

                Genitore g = new Genitore(nome,cognome,data,cf,figlio);
                g.setCredenziali(new Credenziali(username,password));
                genitori.add(g);
            }
        }
        return genitori;
    }

    /**
     * Restituisce lista dei docenti registrati.
     *
     * @return Lista dei docenti registrati.
     */
    public ArrayList<Docente> getDocenti(){
        String s = WebServer.getDocenti();

        ArrayList<Docente> docenti = new ArrayList<>();

        String[] s2 = s.split("\\[");

        if(s2.length<2){
            return docenti;
        }

        String[] s3 = s2[1].split("]");

        String[] docentiSplit = s3[0].split("\\{");
        for (String tmp: docentiSplit) {
            tmp = tmp.split("}")[0];
            String[] temp_split = tmp.split(",");
            if(temp_split.length>1){
                String username = temp_split[0].split(":")[1].substring(1);
                username = username.substring(0,username.length()-1);

                String password = temp_split[1].split(":")[1].substring(1);
                password = password.substring(0,password.length()-1);
                String nome = temp_split[2].split(":")[1].substring(1);
                nome = nome.substring(0,nome.length()-1);
                String cognome = temp_split[3].split(":")[1].substring(1);
                cognome = cognome.substring(0,cognome.length()-1);
                //data
                String dataString = temp_split[4].split(":")[1].substring(1);
                dataString = dataString.substring(0,dataString.length()-1);
                int giorno = Integer.parseInt(dataString.split("-")[0]);
                int mese = Integer.parseInt(dataString.split("-")[1]);
                int anno = Integer.parseInt(dataString.split("-")[2]);
                Calendar c = Calendar.getInstance();
                c.set(anno, mese, giorno);
                Date data = c.getTime();

                String cf = temp_split[5].split(":")[1].substring(1);
                cf = cf.substring(0,cf.length()-1);

                //array
                ArrayList<Classe> classi = new ArrayList<>();
                String classiString = temp_split[6].split(":")[1].substring(1);
                classiString = classiString.substring(0,classiString.length()-1);
                for (String classe: classiString.split(";")) {
                    if(!classe.isEmpty()){
                        for (Classe x: getClassi()) {
                            if(x.toString().equals(classe)){
                                classi.add(x);
                            }
                        }
                    }
                }

                ArrayList<String> materie = new ArrayList<>();
                String materieString = temp_split[7].split(":")[1].substring(1);
                materieString = materieString.substring(0,materieString.length()-1);
                for (String materia: materieString.split(";")) {
                    if(!materia.isEmpty()){
                        materie.add(materia);
                    }
                }

                Docente docente = new Docente(nome,cognome,data,cf,classi,materie);
                docente.setCredenziali(new Credenziali(username,password));
                docenti.add(docente);
            }
        }
        return docenti;
    }
}