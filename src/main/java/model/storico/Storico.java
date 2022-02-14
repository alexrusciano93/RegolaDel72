package model.storico;

import model.calciatore.Calciatore;

import java.util.ArrayList;

public class Storico {
    public Storico(int nGiornata, Double totale, ArrayList<Calciatore> squadra) {
        this.nGiornata = nGiornata;
        this.totale = totale;
        this.squadra = squadra;
    }
    public Storico(int nGiornata, Double totale) {
        this.nGiornata = nGiornata;
        this.totale = totale;
    }
    public Storico(){ }

    public int getnGiornata() { return nGiornata; }
    public void setnGiornata(int nGiornata) { this.nGiornata = nGiornata; }
    public Double getTotale() { return totale; }
    public void setTotale(Double totale) { this.totale = totale; }
    public ArrayList<Calciatore> getSquadra() { return squadra; }
    public void setSquadra(ArrayList<Calciatore> squadra) { this.squadra = squadra; }

    @Override
    public String toString() {
        return "Storico{" +
                "nGiornata=" + nGiornata +
                ", totale=" + totale +
                ", squadra=" + squadra +
                '}';
    }

    private int nGiornata;
    private Double totale;
    ArrayList<Calciatore> squadra;
}
