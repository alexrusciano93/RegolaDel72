package model.consigli;


import model.calciatore.Calciatore;
import model.calendario.Calendario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        for(String x:moduliPossibili){// Per ogni modulo trovo i migliori in base a difficolta e media
            supporto.clear();
            String[] modulo=x.split("-");
            regola.aggiornaLista(regola.getPor());
            Modulo.trova(regola.getPor(),difficoltaP,1,supporto);
            player=Integer.parseInt(modulo[0]);
            regola.aggiornaLista(regola.getDif());
            Modulo.trova(regola.getDif(),difficoltaD,player,supporto);
            player=Integer.parseInt(modulo[1]);
            regola.aggiornaLista(regola.getCen());
            Modulo.trova(regola.getCen(),difficoltaC,player,supporto);
            player=Integer.parseInt(modulo[2]);
            regola.aggiornaLista(regola.getAtt());
            Modulo.trova(regola.getAtt(),difficoltaA,player,supporto);
            Double totale=0.0;
            for(Calciatore c: supporto){
                totale+=c.getMedia();
            }   //Calcolo totale del modulo preso in considerazione
            System.out.println("Modulo:"+x+" Totale:"+totale);
            if (totale>totaleConsigliati){
                totaleConsigliati=totale;
                moduloConsigliato=x;
                consigliati.clear();
                for(Calciatore c:supporto)
                    consigliati.add(c);
            } //Salvo modulo e lista calciatori consigliati
        }
        return consigliati;
    }
    private static void trova(ArrayList<Calciatore> lista,HashMap<Integer,Double> diff, int quanti, ArrayList<Calciatore> consigliati) {
        ArrayList<Calciatore> best = new ArrayList<>();
        Collections.sort(lista,new ComparatorDecrescente()); //ordino la lista dei candidati per la media decrescente
        if (quanti==lista.size()){
            for (int i=0; i<lista.size(); i++)
                best.add(lista.get(i));
        }else {
            for (int i = 0; i < quanti; i++)  //Inserisco "quanti" Calciatori in Best
                best.add(lista.get(i));
            int altri = lista.size() - quanti;
            if (altri > 0) {//Se vi sono altri Calciatori in Lista
                for (int i = quanti; i < lista.size(); i++) { // Per ogni Calciatore rimasto
                    Calciatore x = lista.get(i);
                    Double diffX = diff.get(x.getCod());
                    for (int j = 0; j < best.size(); j++) { // Per ogni Calciatore in Best
                        Calciatore y = best.get(j);
                        Double diffY = diff.get(y.getCod());
                        Double intornoMAX=diffY+0.5;
                        Double intornoMIN=diffY-0.5;
                        if (diffX<=intornoMAX && diffX>=intornoMIN){ //Se la difficoltà di 1 degli altri è nell'intorno
                            if (x.getQuotazione()>y.getQuotazione()) { //Se la quotazione di 1 degli altri è >
                                best.remove(j);
                                best.add(j,x);
                                break;
                            }
                        }
                        if (diffX<intornoMIN){ //Se Difficoltà di 1 degli altri è Minore di 1 best
                                best.remove(j);
                                best.add(j,x);
                                break;
                        }
                       /*
                        if (diffX < diffY) {
                            best.remove(j);
                            best.add(j, x);
                            break;
                        }  //Se Difficoltà di X è Minore di Y
                        if (diffX == diffY) {
                            if (x.getMedia() > y.getMedia()) {
                                best.remove(j);
                                best.add(j, x);
                                break;
                            }
                        } //Se Difficoltà di X è uguale a Difficoltà di Y -> Controllo la Media tra i due
                        */
                    }
                }
            }       //per ognuno che rimane controllo se è migliore dei Best inseriti.
        }
        for (Calciatore c : best) {
            consigliati.add(c);
        }
    }


    private Regola72 regola;
    private static final ArrayList<String> moduli = new ArrayList<>(Arrays.asList("3-4-3", "3-5-2", "4-4-2", "4-5-1", "4-3-3"));
    private ArrayList<String> moduliPossibili;
    private HashMap<Integer,Double> difficoltaP,difficoltaD,difficoltaC,difficoltaA;
    private String moduloConsigliato;

}
