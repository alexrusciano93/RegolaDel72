package model.calendario;

import model.squadra.Squadra;

public class Calendario {
    public Calendario(int nGiornata, Squadra casa, Squadra trasferta) {
        this.nGiornata = nGiornata;
        this.casa = casa;
        this.trasferta = trasferta;
    }
    public Calendario() { }

    public int getnGiornata() { return nGiornata; }
    public void setnGiornata(int nGiornata) { this.nGiornata = nGiornata; }
    public Squadra getCasa() { return casa; }
    public void setCasa(Squadra casa) { this.casa = casa; }
    public Squadra getTrasferta() { return trasferta; }
    public void setTrasferta(Squadra trasferta) { this.trasferta = trasferta; }

    @Override
    public String toString() {
        return "Calendario{" +
                "nGiornata=" + nGiornata +
                ", casa=" + casa +
                ", trasferta=" + trasferta +
                '}';
    }

    private int nGiornata;
    private Squadra casa;
    private Squadra trasferta;
}
