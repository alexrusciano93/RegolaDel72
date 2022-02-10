package model.voto;

import model.calciatore.Calciatore;

public class Voto {
    public Voto() { }
    public Voto(int voto, int nGiornata, Calciatore calciatore) {
        this.voto = voto;
        this.nGiornata = nGiornata;
        this.calciatore = calciatore;
    }
    public Voto(int voto, int nGiornata) {
        this.voto = voto;
        this.nGiornata = nGiornata;
    }

    public int getVoto() { return voto; }
    public void setVoto(int voto) { this.voto = voto; }
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

    private int voto;
    private int nGiornata;
    private Calciatore calciatore;
}
