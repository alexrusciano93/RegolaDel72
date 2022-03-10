package application;

import model.calSto.CalSto;
import model.calSto.CalStoDAO;
import model.calciatore.CalciatoreDAO;
import model.consigli.Regola72;
import model.calciatore.Calciatore;
import model.storico.Storico;
import model.storico.StoricoDAO;
import model.utils.SquadraService;
import model.voto.Voto;
import model.voto.VotoDAO;
import org.apache.poi.ss.usermodel.Chart;
import org.jfree.chart.ChartUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "RegolaServlet", value = "/RegS/*")
public class RegolaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("impossibileCalcolare",false);
        ArrayList<Voto> votiSquadra1 = new ArrayList<>(), votiSquadra2 = new ArrayList<>();
        ArrayList<Voto> votiSquadra3 = new ArrayList<>(), votiSquadra4 = new ArrayList<>();
        ArrayList<Calciatore> indisponibili=new ArrayList<>();
        SquadraService ss=new SquadraService();
        StoricoDAO stoDAO=new StoricoDAO(); CalStoDAO csDAO=new CalStoDAO();
        VotoDAO votoDAO=new VotoDAO(); CalciatoreDAO calDAO=new CalciatoreDAO();
        Regola72 reg=new Regola72();
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path) {
            case "/indisponibili":
                session.setAttribute("indisponibili",indisponibili);
                request.getRequestDispatcher("/WEB-INF/interface/indisponibili.jsp").forward(request, response);
                break;
            case "/regola":
                indisponibili= (ArrayList<Calciatore>) session.getAttribute("indisponibili");
                String[] param=request.getParameterValues("indisponibile");
                if (param!=null) {
                    for (int i = 0; i < param.length; i++) {
                        String x = param[i];
                        int codice = Integer.parseInt(x);
                        Calciatore c = calDAO.doRetrieveByCod(codice);
                        indisponibili.add(c);
                    } //Caricamento Calciatori Indisponibili;
                }

                int ultima= (int) session.getAttribute("ultimaGiornata");
                ss.caricaSquadra();
                if (ultima>=4) {
                    votiSquadra1 = ss.caricaVotiSquadra(ultima - 3);
                    votiSquadra2 = ss.caricaVotiSquadra(ultima - 2);
                    votiSquadra3 = ss.caricaVotiSquadra(ultima - 1);
                    votiSquadra4 = ss.caricaVotiSquadra(ultima);
                    //Caricamento Voti ultime 4 Giornate;
                }else{
                    session.setAttribute("impossibileCalcolare",true);
                    request.getRequestDispatcher("/WEB-INF/interface/indisponibili.jsp").forward(request, response);
                }

                //Settaggio Lista Calciatori e Voti
                reg=new Regola72(ss.getPortieri(),ss.getDifensori(),ss.getCentrocampisti(),ss.getAttaccanti());
                reg.setGiornata1(votiSquadra1); reg.setGiornata2(votiSquadra2);
                reg.setGiornata3(votiSquadra3); reg.setGiornata4(votiSquadra4);
                reg.aggiornaIndisponibili(indisponibili);
                reg.aggiornaVoti();
                ArrayList<Calciatore> consigliati=reg.search();

                //Carico Lista Consigliati e Totale predetto
                Double somma=0.0;
                for (Calciatore x:consigliati)
                    somma+=x.getMedia();
                session.setAttribute("sommaConsigliati",somma);
                session.setAttribute("consigliati",consigliati);
                request.getRequestDispatcher("/WEB-INF/interface/visualizzaRegola.jsp").forward(request, response);
                break;
            case "/salva":
                ArrayList<Calciatore> lista= (ArrayList<Calciatore>) session.getAttribute("consigliati");
                int giornata= (int) session.getAttribute("ultimaGiornata");
                Double tot= (Double) session.getAttribute("sommaConsigliati"); //Recupero 11,totale e giornata.
                Storico salva=new Storico();
                giornata++;
                salva.setnGiornata(giornata);
                salva.setTotalePredetto(tot);
                salva.setTotaleVero(0.0);
                stoDAO=new StoricoDAO();
                try { stoDAO.addStorico(salva); } //Salvo la predizione nel DB
                catch (SQLException throwables) { throwables.printStackTrace(); }
                for (Calciatore x:lista){ csDAO.addCalSto(x,salva); } //Salvo i calciatori che formano quell'11
                ArrayList<Storico> statistiche= (ArrayList<Storico>)session.getAttribute("storici");
                if (statistiche==null)
                    statistiche = new ArrayList<>();
                statistiche.add(salva); //Salvo in Sessione gli storici per visualizzarli
                session.setAttribute("storici",statistiche);
                request.getRequestDispatcher("/WEB-INF/interface/visualizzaRegola.jsp").forward(request, response);
                break;
            case "/storico":
                statistiche= (ArrayList<Storico>) session.getAttribute("storici");
                if (statistiche==null)
                    session.setAttribute("storicoNull",true); //se NON CI SONO Storici salvati
                else{
                    int prossima=(int) session.getAttribute("prossimaGiornata");
                    for(int i=0; i<statistiche.size(); i++){
                        Storico x=statistiche.get(i);
                        if (x.getTotaleVero()==0.0 && x.getnGiornata()<prossima){
                            ArrayList<CalSto> oldSquadra=csDAO.doRetrieveCalciatoriWithStorico(x.getnGiornata());
                            ArrayList<Voto> oldVoto=votoDAO.doRetrieveByGiornata(x.getnGiornata());
                            Double trueTot=reg.calcolaTot(oldSquadra,oldVoto);
                            x.setTotaleVero(trueTot);
                            stoDAO.doChanges(x);
                            statistiche.remove(i);
                            statistiche.add(i,x);
                            session.setAttribute("storici",statistiche);
                        }
                        // se CI SONO Storici salvati e non calcolati li Calcolo.
                    }
                }
                //ChartUtils.saveChartAsPNG();
                request.getRequestDispatcher("/WEB-INF/interface/visualizzaStorici.jsp").forward(request, response);
                break;
        }
    }
}
