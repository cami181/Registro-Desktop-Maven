package Altro;

import Utenti.Docente;
import java.util.Date;

public class Voto {
    private final double voto;
    private final String materia;
    private final Docente docente;
    private final Date data;

    public Voto(double voto, String materia, Docente docente, Date data) {
        this.voto = voto;
        this.materia = materia;
        this.docente = docente;
        this.data = data;
    }

    public double getValore() {
        return voto;
    }

    public String getMateria() {
        return materia;
    }

    public Docente getDocente() {
        return docente;
    }

    public Date getData(){
        return data;
    }
}
