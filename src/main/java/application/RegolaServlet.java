package application;

import model.consigli.Regola72;
import model.calciatore.Calciatore;
import model.utils.SquadraService;
import model.voto.Voto;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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
        SquadraService ss=new SquadraService();
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path) {
            case "/regola":
                ArrayList<Voto> votiSquadra1 = new ArrayList<>(), votiSquadra2 = new ArrayList<>(),
                        votiSquadra3 = new ArrayList<>(), votiSquadra4 = new ArrayList<>();
                int ultima= (int) session.getAttribute("ultimaGiornata");
                ss.caricaSquadra();
                if (ultima>=4) {
                    votiSquadra1 = ss.caricaVotiSquadra(ultima - 3);
                    votiSquadra2 = ss.caricaVotiSquadra(ultima - 2);
                    votiSquadra3 = ss.caricaVotiSquadra(ultima - 1);
                    votiSquadra4 = ss.caricaVotiSquadra(ultima);
                }
                Regola72 reg=new Regola72(ss.getPortieri(),ss.getDifensori(),ss.getCentrocampisti(),ss.getAttaccanti());
                reg.setGiornata1(votiSquadra1); reg.setGiornata2(votiSquadra2);
                reg.setGiornata3(votiSquadra3); reg.setGiornata4(votiSquadra4);

                reg.aggiornamento();
                ArrayList<Calciatore> consigliati=reg.search();
                Double somma=0.0;
                for (Calciatore x:consigliati)
                    somma+=x.getMedia();
                session.setAttribute("sommaConsigliati",somma);
                session.setAttribute("consigliati",consigliati);

                request.getRequestDispatcher("/WEB-INF/interface/visualizzaRegola.jsp").forward(request, response);
                break;
            case "/salva":

                break;

        }
    }
}
