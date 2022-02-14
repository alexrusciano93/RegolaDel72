package model.consigli;

import model.calciatore.Calciatore;
import model.calciatore.CalciatoreDAO;
import model.voto.Voto;
import java.util.ArrayList;
import java.util.Collections;

public class Regola72 {
    public Regola72(){ }
    public Regola72(ArrayList<Calciatore> por, ArrayList<Calciatore> dif, ArrayList<Calciatore> cen, ArrayList<Calciatore> att) {
        this.por = por;
        this.dif = dif;
        this.cen = cen;
        this.att = att;
    }


    public void setGiornata1(ArrayList<Voto> giornata1) { this.giornata1 = giornata1; }
    public void setGiornata2(ArrayList<Voto> giornata2) { this.giornata2 = giornata2; }
    public void setGiornata3(ArrayList<Voto> giornata3) { this.giornata3 = giornata3; }
    public void setGiornata4(ArrayList<Voto> giornata4) { this.giornata4 = giornata4; }


    public ArrayList<Calciatore> aggiornaLista(ArrayList<Calciatore> lista){
        ArrayList<Calciatore> supporto = new ArrayList<>();
        for (Calciatore x:lista){
            supporto.add(x);
        }
        lista.clear();
        for (Calciatore x:supporto) {
            x = cDAO.doRetrieveByCod(x.getCod());
            lista.add(x);
        }
        return lista;
    }
    public void aggiornamento(){
        for (int i=0; i<25; i++) {
            Calciatore x=new Calciatore();
            Double a,b,c,d;
            int n = 4;
            a = giornata1.get(i).getVoto(); b = giornata2.get(i).getVoto();
            c = giornata3.get(i).getVoto(); d = giornata4.get(i).getVoto();
            if (a == 0.0) n--; if (b == 0.0) n--;
            if (c == 0.0) n--; if (d == 0.0) n--;
            boolean trovato=false;
            if (giornata1.get(i).getCalciatore() != null){
                 x= cDAO.doRetrieveByCod(giornata1.get(i).getCalciatore().getCod());
                 trovato=true;
            }
            if (giornata2.get(i).getCalciatore() != null && !trovato){
                x = cDAO.doRetrieveByCod(giornata2.get(i).getCalciatore().getCod());
                trovato=true;
            }
            if (giornata3.get(i).getCalciatore() != null && !trovato){
                x = cDAO.doRetrieveByCod(giornata3.get(i).getCalciatore().getCod());
                trovato=true;
            }
            if (giornata4.get(i).getCalciatore() != null && !trovato){
                x = cDAO.doRetrieveByCod(giornata4.get(i).getCalciatore().getCod());
                trovato=true;
            }
            if (n>=2) {
                x.setMedia((a + b + c + d) / n);
                cDAO.doChanges(x);
            }
        }
    }
    public ArrayList<Calciatore> search(){
        Calciatore x;
        //portieri
        por=this.aggiornaLista(por);
        Collections.sort(por,new ComparatorDecrescente());
        consigliati.add(por.get(0));
        System.out.println(por);
        //difensori
        dif=this.aggiornaLista(dif);
        Collections.sort(dif,new ComparatorDecrescente());
        for (int i=0; i<3; i++)
            consigliati.add(dif.get(i));
        //centrocampisti
        cen=this.aggiornaLista(cen);
        Collections.sort(cen,new ComparatorDecrescente());
        for (int i=0; i<4; i++)
            consigliati.add(cen.get(i));
        //attaccanti
        att=this.aggiornaLista(att);
        Collections.sort(att,new ComparatorDecrescente());
        for (int i=0; i<3; i++)
            consigliati.add(att.get(i));
        return consigliati;
    }


    private ArrayList<Voto> giornata1,giornata2,giornata3,giornata4;
    private ArrayList<Calciatore> por,dif,cen,att;
    private ArrayList<Calciatore> consigliati =new ArrayList<>();
    private static final CalciatoreDAO cDAO = new CalciatoreDAO();
}
