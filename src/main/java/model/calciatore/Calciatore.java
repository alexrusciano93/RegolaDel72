package model.calciatore;

public class Calciatore {
    public Calciatore() { }
    public Calciatore(String nome, String ruolo, String squadra, int quotazione, int cod) {
        this.nome = nome;
        this.ruolo = ruolo;
        this.squadra = squadra;
        this.quotazione = quotazione;
        this.cod = cod;
    }

    public Calciatore(String nome, String ruolo, String squadra, int quotazione, Boolean scelto, int cod) {
        this.nome = nome;
        this.ruolo = ruolo;
        this.squadra = squadra;
        this.quotazione = quotazione;
        this.scelto = scelto;
        this.cod = cod;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getRuolo() { return ruolo; }
    public void setRuolo(String ruolo) { this.ruolo = ruolo; }
    public String getSquadra() { return squadra; }
    public void setSquadra(String squadra) { this.squadra = squadra; }
    public int getQuotazione() { return quotazione; }
    public void setQuotazione(int quotazione) { this.quotazione = quotazione; }
    public Boolean getScelto() { return scelto; }
    public void setScelto(Boolean scelto) { this.scelto = scelto; }
    public int getCod() { return cod; }
    public void setCod(int cod) { this.cod = cod; }

    @Override
    public String toString() {
        return "Calciatore{" +
                "nome='" + nome + '\'' +
                ", ruolo='" + ruolo + '\'' +
                ", squadra='" + squadra + '\'' +
                ", quotazione=" + quotazione +
                ", scelto=" + scelto +
                ", cod=" + cod +
                '}';
    }

    private String nome;
    private String ruolo;
    private String squadra;
    private int quotazione;
    private Boolean scelto;
    private int cod;
}
