package model.consigli;

import model.calciatore.Calciatore;
import model.calendario.Calendario;
import model.squadra.Squadra;

import java.util.ArrayList;

public class CalcoloDifficoltÃ  {
    public static Double calcola(ArrayList<Calendario> giornata, Calciatore x){
        String squadra=x.getSquadra();
        String ruolo=x.getRuolo();
        Integer quotazione=x.getQuotazione();
        Double difficolta=0.0;
        Squadra avversario=new Squadra();
        for(Calendario partita:giornata){
            String casa,trasferta;
            casa=partita.getCasa().getNome();
            trasferta=partita.getTrasferta().getNome();
            if (squadra.equalsIgnoreCase(casa))
                avversario = partita.getTrasferta();
            if (squadra.equalsIgnoreCase(trasferta))
                avversario = partita.getCasa();
        } //devo capire con chi gioca

        Double attacco,difesa;
        attacco=avversario.getAttacco();
        difesa=avversario.getDifesa();        //prendo valori attacco e difesa squadra avversaria

        switch (ruolo){
            case "A":
                difficolta=difesa;
                break;
            case "C":
                difficolta=difesa;
                break;
            case "D":
                difficolta=attacco;
                break;
            case "P":
                difficolta=attacco;
                break;
        }                   //controllo A -> difesa || D -> attacco || C -> difesa || P -> attacco

        if(quotazione>=25){
            difficolta-=2;
        }
        if(quotazione>=20){
            difficolta-=1.5;
        }
        if(quotazione>=15){
            difficolta-=1;
        }

        return difficolta;
    }


}
