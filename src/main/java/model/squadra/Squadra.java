package model.squadra;

public class Squadra {
    public Squadra(String nome, Double attacco, Double difesa) {
        this.nome = nome;
        this.attacco = attacco;
        this.difesa = difesa;
    }
    public Squadra() { }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getAttacco() { return attacco; }
    public void setAttacco(Double attacco) { this.attacco = attacco; }
    public Double getDifesa() { return difesa; }
    public void setDifesa(Double difesa) { this.difesa = difesa; }

    @Override
    public String toString() {
        return "Squadra{" +
                "nome='" + nome + '\'' +
                ", attacco=" + attacco +
                ", difesa=" + difesa +
                '}';
    }

    private String nome;
    private Double attacco;
    private Double difesa;

}
