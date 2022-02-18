package model.consigli;


import model.calciatore.Calciatore;
import model.calendario.Calendario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Modulo {
    public Modulo(){
        regola=new Regola72();
        moduliPossibili=new ArrayList<>();
    }
    public Modulo(Regola72 regola) {
        this.regola = regola;
        moduliPossibili=new ArrayList<>();
    }

    public String getModuloConsigliato() { return moduloConsigliato; }

    public void controlloModuli(){
        int player=0;
        for(String x:moduli){
            String[] modulo=x.split("-");
            player=Integer.parseInt(modulo[0]);
            if (regola.getDif().size()>=player){
                player=Integer.parseInt(modulo[1]);
                if(regola.getCen().size()>=player){
                    player=Integer.parseInt(modulo[2]);
                    if (regola.getAtt().size()>=player)
                        moduliPossibili.add(x);
                }
            }
        }  //controllo Moduli in base ai giocatori disponibili;
    }
    public void controlloCalendario(ArrayList<Calendario> giornata){
        difficoltaP=new HashMap<>();
        ArrayList<Calciatore> por=regola.getPor();
        Modulo.itera(por,giornata,difficoltaP);
        difficoltaD=new HashMap<>();
        ArrayList<Calciatore> dif=regola.getDif();
        Modulo.itera(dif,giornata,difficoltaD);
        difficoltaC=new HashMap<>();
        ArrayList<Calciatore> cen=regola.getCen();
        Modulo.itera(cen,giornata,difficoltaC);
        difficoltaA=new HashMap<>();
        ArrayList<Calciatore> att=regola.getAtt();
        Modulo.itera(att,giornata,difficoltaA);
    }
    private static void itera (ArrayList<Calciatore> lista,ArrayList<Calendario> giornata,HashMap<Integer,Double> diff){
        for(Calciatore x: lista){
            Double quoziente=CalcoloDifficoltà.calcola(giornata,x);
            diff.put(x.getCod(),quoziente);
        }
    }
    public ArrayList<Calciatore> calcoloModuli(){
        ArrayList<Calciatore> consigliati=new ArrayList<>();
        ArrayList<Calciatore> supporto=new ArrayList<>();
        Double totaleConsigliati=0.0;
        int player=0;
        for(String x:moduliPossibili){         // Per ogni modulo trovo i migliori in base a difficolta e media
            supporto.clear();
            String[] modulo=x.split("-");
            Modulo.trova(regola.getPor(),difficoltaP,1,supporto);
            player=Integer.parseInt(modulo[0]);
            Modulo.trova(regola.getDif(),difficoltaD,player,supporto);
            player=Integer.parseInt(modulo[1]);
            Modulo.trova(regola.getCen(),difficoltaC,player,supporto);
            player=Integer.parseInt(modulo[2]);
            Modulo.trova(regola.getAtt(),difficoltaA,player,supporto);
            Double totale=0.0;
            for(Calciatore c: supporto){
                totale+=c.getMedia();
            }   //Calcolo totale del modulo preso in considerazione
            if (totale>totaleConsigliati){
                totaleConsigliati=totale;
                moduloConsigliato=x;
                consigliati=supporto;
            } //Salvo modulo e lista calciatori consigliati
        }
        return consigliati;
    }
    private static void trova(ArrayList<Calciatore>lista,HashMap<Integer,Double> diff, int quanti, ArrayList<Calciatore> consigliati) {
        ArrayList<Calciatore> best = new ArrayList<>();
        for (int i=0; i<quanti; i++)  //metto i primi "quanti"
            best.add(lista.get(i));

        int altri=lista.size()-quanti;
        if (altri>0) {                //se ci sono Altri itero su di loro
            for (int i = quanti; i < lista.size(); i++) {
                    Calciatore x=lista.get(i);
                    Double diffX=diff.get(x.getCod());
                    for(Calciatore y:best){
                        Double diffY=diff.get(y.getCod());
                        if (diffX<diffY){
                            best.remove(i);
                            best.add(i,x);
                        }  //SE DIFFX è MINORE SOSTITUISCO
                        if (diffX==diffY){
                            if (x.getMedia()>y.getMedia()) {
                                best.remove(i);
                                best.add(i, x);
                            }
                        } //SE DIFFX è UGUALE A DIFFY CONTROLLO LA MEDIA
                    }
            }
        }       //per ognuno che rimane controllo se è migliore dei Best inseriti.

        for(Calciatore x: best){
            consigliati.add(x);
        }
    }


    private Regola72 regola;
    private static final ArrayList<String> moduli = new ArrayList<>(Arrays.asList("3-4-3", "3-5-2", "4-4-2", "4-5-1", "4-3-3"));
    private ArrayList<String> moduliPossibili;
    private HashMap<Integer,Double> difficoltaP,difficoltaD,difficoltaC,difficoltaA;
    private String moduloConsigliato;

}
