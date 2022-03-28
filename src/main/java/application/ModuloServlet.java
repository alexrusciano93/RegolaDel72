package application;

import model.calSto.CalStoDAO;
import model.calciatore.Calciatore;
import model.calciatore.CalciatoreDAO;
import model.calendario.CalendarioDAO;
import model.consigli.Modulo;
import model.consigli.Regola72;
import model.storico.StoricoDAO;
import model.utils.SquadraService;
import model.voto.Voto;
import model.voto.VotoDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ModuloServlet", value = "/Modulo/*")
public class ModuloServlet extends HttpServlet {
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
        CalendarioDAO cDAO=new CalendarioDAO();
        Regola72 reg=new Regola72();
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path) {
            case "/indisponibili":
                session.setAttribute("indisponibili", indisponibili);
                request.getRequestDispatcher("/WEB-INF/interface/indisponibiliModulo.jsp").forward(request, response);
                break;
            case "/best":
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
                    request.getRequestDispatcher("/WEB-INF/interface/indisponibiliModulo.jsp").forward(request, response);
                }

                //Settaggio Lista Calciatori e Voti
                reg=new Regola72(ss.getPortieri(),ss.getDifensori(),ss.getCentrocampisti(),ss.getAttaccanti());
                reg.setGiornata1(votiSquadra1); reg.setGiornata2(votiSquadra2);
                reg.setGiornata3(votiSquadra3); reg.setGiornata4(votiSquadra4);
                reg.aggiornaIndisponibili(indisponibili);
                reg.aggiornaVoti();
                Modulo mod=new Modulo(reg);
                mod.controlloModuli();
                int g= (int) session.getAttribute("prossimaGiornata");
                mod.controlloCalendario(cDAO.doRetrieveByGiornata(g));
                ArrayList<Calciatore> best=mod.calcoloModuli();
                String modulo=mod.getModuloConsigliato();
                System.out.println("Calciatori:\n"+best);
                System.out.println("Modulo Consigliato:"+modulo);

                //Carico Lista Best e Totale predetto
                Double somma=0.0;
                for (Calciatore x:best)
                    somma+=x.getMedia();
                session.setAttribute("sommaConsigliati",somma);
                session.setAttribute("consigliati",best);
                session.setAttribute("okSalvato",0);
                request.getRequestDispatcher("/WEB-INF/interface/visualizzaModulo.jsp").forward(request, response);
                break;
        }
    }
}
