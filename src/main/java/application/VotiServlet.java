package application;

import model.calciatore.Calciatore;
import model.calciatore.CalciatoreDAO;
import model.utils.FillDatabase;
import model.utils.SquadraService;
import model.voto.Voto;
import model.voto.VotoDAO;

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
        SquadraService ss=new SquadraService();
        FillDatabase fill=new FillDatabase();
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path) {
            case "/carica":

                request.getRequestDispatcher("/WEB-INF/interface/carica.jsp").forward(request, response);
                break;
            case "/caricaVoti":
                VotoDAO votoDAO=new VotoDAO();
                String x=request.getParameter("numGiornata");
                int giornata=Integer.parseInt(x);
                int prossima=Integer.parseInt(getServletContext().getInitParameter("prossimaGiornata"));
                if (giornata!=prossima){
                    //errore da gestire poi con un alert
                }
                /*if (giornata>=5){
                    giornata-=4;
                    for (int i=giornata; i>0; i--)
                        votoDAO.deleteByGiornata(i);
                }*/ // per cancellare i voti che non servono piu [Non Funziona!]
                prossima++;
                getServletContext().setInitParameter("prossimaGiornata", String.valueOf(prossima));
                session.setAttribute("ultimaGiornata",giornata);
                try {
                    fill.generateVoti(giornata);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
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
