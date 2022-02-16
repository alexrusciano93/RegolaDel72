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
        ArrayList<Voto> votiSquadra1 = new ArrayList<>(), votiSquadra2 = new ArrayList<>();
        ArrayList<Voto> votiSquadra3 = new ArrayList<>(), votiSquadra4 = new ArrayList<>();
        ArrayList<Calciatore> indisponibili=new ArrayList<>();
        SquadraService ss=new SquadraService();
        StoricoDAO stoDAO=new StoricoDAO(); CalStoDAO calStoDAO=new CalStoDAO();
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
                for(int i=0; i<param.length; i++) {
                    String x = param[i];
                    int codice = Integer.parseInt(x);
                    Calciatore c = calDAO.doRetrieveByCod(codice);
                    indisponibili.add(c);
                } //Caricamento Calciatori Indisponibili;

                int ultima= (int) session.getAttribute("ultimaGiornata");
                ss.caricaSquadra();
                if (ultima>=4) {
                    votiSquadra1 = ss.caricaVotiSquadra(ultima - 3);
                    votiSquadra2 = ss.caricaVotiSquadra(ultima - 2);
                    votiSquadra3 = ss.caricaVotiSquadra(ultima - 1);
                    votiSquadra4 = ss.caricaVotiSquadra(ultima);
                }  //Caricamento Voti ultime 4 Giornate;

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
                Double tot= (Double) session.getAttribute("sommaConsigliati");
                Storico salva=new Storico();
                giornata++;
                salva.setnGiornata(giornata);
                salva.setTotalePredetto(tot);
                salva.setTotaleVero(0.0);
                stoDAO=new StoricoDAO();
                try { stoDAO.addStorico(salva); } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                CalStoDAO csDAO=new CalStoDAO();
                for (Calciatore x:lista){
                    csDAO.addCalSto(x,salva);
                }
                if (giornata>5){
                    giornata--;
                    Storico old=stoDAO.doRetrieveByGiornata(giornata);
                    ArrayList<CalSto> oldSquadra=calStoDAO.doRetrieveCalciatoriWithStorico(giornata);
                    ArrayList<Voto> oldVoto=votoDAO.doRetrieveByGiornata(giornata);
                    Double trueTot=reg.calcolaTot(oldSquadra,oldVoto);
                    old.setTotaleVero(trueTot);
                    stoDAO.doChanges(old);
                    ArrayList<Storico> statistiche= (ArrayList<Storico>) session.getAttribute("storici");
                    if (statistiche==null)
                        statistiche=new ArrayList<>();
                    statistiche.add(old);
                    session.setAttribute("storici",statistiche);
                }
                request.getRequestDispatcher("/WEB-INF/interface/visualizzaRegola.jsp").forward(request, response);
                break;
            case "/storico":
                request.getRequestDispatcher("/WEB-INF/interface/visualizzaStorici.jsp").forward(request, response);
                break;
        }
    }
}
