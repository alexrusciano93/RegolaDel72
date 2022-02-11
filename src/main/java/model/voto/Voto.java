package model.voto;

import model.calciatore.Calciatore;

public class Voto {
    public Voto() { }
    public Voto(double voto, int nGiornata, Calciatore calciatore) {
        this.voto = voto;
        this.nGiornata = nGiornata;
        this.calciatore = calciatore;
    }
    public Voto(double voto, int nGiornata) {
        this.voto = voto;
        this.nGiornata = nGiornata;
    }

    public double getVoto() { return voto; }
    public void setVoto(double voto) { this.voto = voto; }
    public int getnGiornata() { return nGiornata; }
    public void setnGiornata(int nGiornata) { this.nGiornata = nGiornata; }
    public Calciatore getCalciatore() { return calciatore; }
    public void setCalciatore(Calciatore calciatore) { this.calciatore = calciatore; }

    @Override
    public String toString() {
        return "Voto{" +
                "voto=" + voto +
                ", nGiornata=" + nGiornata +
                ", calciatore=" + calciatore +
                '}';
    }

    private double voto;
    private int nGiornata;
    private Calciatore calciatore;
}
