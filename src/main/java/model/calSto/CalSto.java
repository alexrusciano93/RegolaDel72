package model.calSto;

import model.calciatore.Calciatore;
import model.storico.Storico;

public class CalSto {
    public CalSto(Calciatore calciatore, Storico storico) {
        this.calciatore = calciatore;
        this.storico = storico;
    }
    public CalSto() { }

    public Calciatore getCalciatore() { return calciatore; }
    public void setCalciatore(Calciatore calciatore) { this.calciatore = calciatore; }
    public Storico getStorico() { return storico; }
    public void setStorico(Storico storico) { this.storico = storico; }

    public Boolean getRegola() {
        return regola;
    }

    public void setRegola(Boolean regola) {
        this.regola = regola;
    }

    @Override
    public String toString() {
        return "CalSto{" +
                "regola=" + regola +
                ", calciatore=" + calciatore +
                ", storico=" + storico +
                '}';
    }

    private Boolean regola;
    private Calciatore calciatore;
    private Storico storico;
}
