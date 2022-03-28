package application;

import model.calciatore.Calciatore;
import model.calendario.Calendario;
import model.calendario.CalendarioDAO;
import model.utils.FillDatabase;
import model.utils.SquadraService;
import model.voto.Voto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "VotiServlet", value = "/vs/*")
public class VotiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String address = request.getServletContext().getContextPath();
        ArrayList<Calciatore> result=new ArrayList<>();
        CalendarioDAO cDAO=new CalendarioDAO();
        SquadraService ss=new SquadraService();
        FillDatabase fill=new FillDatabase();
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path) {
            case "/carica":
                session.setAttribute("impossibileCaricare",0);
                request.getRequestDispatcher("/WEB-INF/interface/carica.jsp").forward(request, response);
                break;
            case "/caricaVoti":
                String x=request.getParameter("numGiornata");
                int giornata=Integer.parseInt(x);
                int prossima= (int) session.getAttribute("prossimaGiornata");
                if (giornata!=prossima){
                    session.setAttribute("impossibileCaricare",1);
                    request.getRequestDispatcher("/WEB-INF/interface/carica.jsp").forward(request, response);
                    break;
                }
                /*if (giornata>=5){
                    giornata-=4;
                    for (int i=giornata; i>0; i--)
                        votoDAO.deleteByGiornata(i);
                }*/ // per cancellare i voti che non servono piu [Non Funziona!]
                prossima++;
                session.setAttribute("prossimaGiornata",prossima);
                session.setAttribute("ultimaGiornata",giornata);
                try {
                    fill.generateVoti(giornata);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ArrayList<Calendario> partite=cDAO.doRetrieveByGiornata(prossima);
                session.setAttribute("partite",partite);
                request.getRequestDispatcher("/WEB-INF/interface/carica.jsp").forward(request, response);
                break;
            case "/visualizza":
                ss.caricaSquadra();
                session.setAttribute("portieri",ss.getPortieri());
                session.setAttribute("difensori",ss.getDifensori());
                session.setAttribute("centrocampisti",ss.getCentrocampisti());
                session.setAttribute("attaccanti",ss.getAttaccanti());
                int ultima= (int) session.getAttribute("ultimaGiornata");
                if (ultima>=4) {
                    ArrayList<Voto> votiSquadra1 = ss.caricaVotiSquadra(ultima-3);
                    ArrayList<Voto> votiSquadra2 = ss.caricaVotiSquadra(ultima-2);
                    ArrayList<Voto> votiSquadra3 = ss.caricaVotiSquadra(ultima-1);
                    ArrayList<Voto> votiSquadra4 = ss.caricaVotiSquadra(ultima);
                    session.setAttribute("votiSquadra4", votiSquadra4);
                    session.setAttribute("votiSquadra3", votiSquadra3);
                    session.setAttribute("votiSquadra2", votiSquadra2);
                    session.setAttribute("votiSquadra1", votiSquadra1);
                }
                request.getRequestDispatcher("/WEB-INF/interface/visualizzaVoti.jsp").forward(request, response);
                break;
        }

    }
}
