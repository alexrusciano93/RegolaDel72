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

    @Override
    public String toString() {
        return "CalSto{" +
                "calciatore=" + calciatore +
                ", storico=" + storico +
                '}';
    }

    private Calciatore calciatore;
    private Storico storico;
}
