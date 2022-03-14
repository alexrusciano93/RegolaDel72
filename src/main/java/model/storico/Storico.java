package model.storico;

import model.calciatore.Calciatore;

import java.util.ArrayList;

public class Storico {
    public Storico(int nGiornata, Double totalePredetto, Double totaleVero, ArrayList<Calciatore> squadra) {
        this.nGiornata = nGiornata;
        this.totalePredetto = totalePredetto;
        this.totaleVero = totaleVero;
        this.squadra = squadra;
    }

    public Storico(int nGiornata, Double totalePredetto, ArrayList<Calciatore> squadra) {
        this.nGiornata = nGiornata;
        this.totalePredetto = totalePredetto;
        this.squadra = squadra;
    }
    public Storico(int nGiornata, Double totalePredetto) {
        this.nGiornata = nGiornata;
        this.totalePredetto = totalePredetto;
    }
    public Storico(){ }

    public int getnGiornata() { return nGiornata; }
    public void setnGiornata(int nGiornata) { this.nGiornata = nGiornata; }
    public Double getTotalePredetto() { return totalePredetto; }
    public void setTotalePredetto(Double totalePredetto) { this.totalePredetto = totalePredetto; }
    public ArrayList<Calciatore> getSquadra() { return squadra; }
    public void setSquadra(ArrayList<Calciatore> squadra) { this.squadra = squadra; }
    public Double getTotaleVero() { return totaleVero; }
    public void setTotaleVero(Double totaleVero) { this.totaleVero = totaleVero; }

    public Boolean getRegola() {
        return regola;
    }

    public void setRegola(Boolean regola) {
        this.regola = regola;
    }

    @Override
    public String toString() {
        return "Storico{" +
                "nGiornata=" + nGiornata +
                ", totalePredetto=" + totalePredetto +
                ", totaleVero=" + totaleVero +
                ", regola=" + regola +
                ", squadra=" + squadra +
                '}';
    }

    private int nGiornata;
    private Double totalePredetto;
    private Double totaleVero;
    private Boolean regola;
    ArrayList<Calciatore> squadra;
}
