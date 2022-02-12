package model.utils;

import model.calciatore.Calciatore;
import model.calciatore.CalciatoreDAO;
import model.voto.Voto;
import model.voto.VotoDAO;

import java.util.ArrayList;

public class SquadraService {
    private ArrayList<Calciatore> portieri,difensori,centrocampisti,attaccanti;
    private CalciatoreDAO calDAO=new CalciatoreDAO();
    private VotoDAO votoDAO=new VotoDAO();

    public SquadraService(){ }
    public SquadraService(ArrayList<Calciatore> portieri, ArrayList<Calciatore> difensori, ArrayList<Calciatore> centrocampisti, ArrayList<Calciatore> attaccanti) {
        this.portieri = portieri;
        this.difensori = difensori;
        this.centrocampisti = centrocampisti;
        this.attaccanti = attaccanti;
    }

    public ArrayList<Calciatore> getPortieri() { return portieri; }
    public void setPortieri(ArrayList<Calciatore> portieri) { this.portieri = portieri; }
    public ArrayList<Calciatore> getDifensori() { return difensori; }
    public void setDifensori(ArrayList<Calciatore> difensori) { this.difensori = difensori; }
    public ArrayList<Calciatore> getCentrocampisti() { return centrocampisti; }
    public void setCentrocampisti(ArrayList<Calciatore> centrocampisti) { this.centrocampisti = centrocampisti; }
    public ArrayList<Calciatore> getAttaccanti() { return attaccanti; }
    public void setAttaccanti(ArrayList<Calciatore> attaccanti) { this.attaccanti = attaccanti; }


    //CARICAMENTO CALCIATORI PER LA SQUADRA SELEZIONATA
    public void caricaPortieri(){ this.portieri=calDAO.doRetrieveBySceltoAndRuolo("P"); }
    public void caricaDifensori(){ this.difensori=calDAO.doRetrieveBySceltoAndRuolo("D"); }
    public void caricaCentrocampisti(){ this.centrocampisti=calDAO.doRetrieveBySceltoAndRuolo("C"); }
    public void caricaAttaccanti(){ this.attaccanti=calDAO.doRetrieveBySceltoAndRuolo("A"); }
    public void caricaSquadra(){
        this.portieri=calDAO.doRetrieveBySceltoAndRuolo("P");
        this.difensori=calDAO.doRetrieveBySceltoAndRuolo("D");
        this.centrocampisti=calDAO.doRetrieveBySceltoAndRuolo("C");
        this.attaccanti=calDAO.doRetrieveBySceltoAndRuolo("A");
    }

    //CARICAMENTO VOTI PER LA SQUADRA SELEZIONATA
    public void caricaVotiPortieri(int giornata,ArrayList<Voto> result){
        for(Calciatore x:portieri){
            Voto y=votoDAO.doRetrieveByCalciatoreAndGiornata(giornata,x.getCod());
            result.add(y);
        }
    }
    public void caricaVotiDifensori(int giornata,ArrayList<Voto> result){
        for(Calciatore x:difensori){
            Voto y=votoDAO.doRetrieveByCalciatoreAndGiornata(giornata,x.getCod());
            result.add(y);
        }
    }
    public void caricaVotiCentrocampisti(int giornata,ArrayList<Voto> result){
        for(Calciatore x:centrocampisti){
            Voto y=votoDAO.doRetrieveByCalciatoreAndGiornata(giornata,x.getCod());
            result.add(y);
        }
    }
    public void caricaVotiAttaccanti(int giornata,ArrayList<Voto> result){
        for(Calciatore x:attaccanti){
            Voto y=votoDAO.doRetrieveByCalciatoreAndGiornata(giornata,x.getCod());
            result.add(y);
        }
    }
    public ArrayList<Voto> caricaVotiSquadra(int giornata){
        ArrayList<Voto> result=new ArrayList<>();
        caricaVotiPortieri(giornata,result);
        caricaVotiDifensori(giornata,result);
        caricaVotiCentrocampisti(giornata,result);
        caricaVotiAttaccanti(giornata,result);
        return result;
    }


    @Override
    public String toString() {
        return "SquadraService{" +
                "portieri=" + portieri +
                ", difensori=" + difensori +
                ", centrocampisti=" + centrocampisti +
                ", attaccanti=" + attaccanti +
                '}';
    }
}
