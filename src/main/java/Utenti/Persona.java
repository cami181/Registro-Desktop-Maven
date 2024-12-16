package Utenti;

import Credenziali.Credenziali;

import java.util.Date;

public class Persona {
    private String nome;
    private String cognome;

    private Date dataDiNascita;

    private String CF;

    private Credenziali credenziali;


    public Persona(String nome, String congome, Date dataDiNascita, String CF) {
        this.nome = nome;
        this.cognome = congome;
        this.dataDiNascita = dataDiNascita;
        this.CF = CF;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public String getCF() {
        return CF;
    }

    public Credenziali getCredenziali() {
        return credenziali;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public void setCF(String CF) {
        this.CF = CF;
    }

    public void setCredenziali(Credenziali credenziali) {
        this.credenziali = credenziali;
    }
}
