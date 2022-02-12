package application;

import model.calciatore.Calciatore;
import model.calciatore.CalciatoreDAO;
import model.utils.FillDatabase;
import model.utils.SquadraService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

@WebServlet(name = "RosaServlet", value = "/rs/*")
public class RosaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CalciatoreDAO calDAO=new CalciatoreDAO();
        ArrayList<Calciatore> result;
        String address = request.getServletContext().getContextPath();
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path) {
            case "/sommario":
                session.setAttribute("portieriNull", false);
                session.setAttribute("difensoriNull", false);
                session.setAttribute("centrocampistiNull", false);
                session.setAttribute("attaccantiNull", false);
                Boolean caricato = (Boolean) session.getAttribute("caricato");
                if (caricato == null) {
                    FillDatabase db = new FillDatabase();
                    try {
                        for (int i = 1; i < 5; i++)
                            db.generateCalciatori(i);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    session.setAttribute("caricato", true);
                }  //Carica i calciatori da File Quotazioni

                result=calDAO.doRetrieveByScelto();
                if (result.size()!=0) {
                    SquadraService ss=new SquadraService();
                    ss.caricaSquadra();
                    session.setAttribute("portieri",ss.getPortieri());
                    session.setAttribute("difensori",ss.getDifensori());
                    session.setAttribute("centrocampisti",ss.getCentrocampisti());
                    session.setAttribute("attaccanti",ss.getAttaccanti());
                } // se ci sono Selezionati
                else{
                    session.setAttribute("portieriNull", true);
                    session.setAttribute("difensoriNull", true);
                    session.setAttribute("centrocampistiNull", true);
                    session.setAttribute("attaccantiNull", true);
                } //se non ci sono Selezionati

                request.getRequestDispatcher("/WEB-INF/interface/sommario.jsp").forward(request, response);
                break;
            case "/estrai":
                int pagina = Integer.parseInt(request.getParameter("id"));
                String ruolo = "";
                int max=0;
                if (pagina == 1) { ruolo = "Portieri";  max=3; }
                if (pagina == 2) { ruolo = "Difensori"; max=8; }
                if (pagina == 3) { ruolo = "Centrocampisti"; max=8; }
                if (pagina == 4) { ruolo = "Attaccanti"; max=6; }
                session.setAttribute("ruolo", ruolo);
                session.setAttribute("maxGiocatori",max);
                result= calDAO.doRetrieveByRuolo(ruolo.substring(0, 1)); //Preparo da DB la lista by Ruolo
                session.setAttribute("giocatori", result);
                request.getRequestDispatcher("/WEB-INF/interface/scelta.jsp").forward(request, response);
                break;
            case "/salva":
                String[] param=request.getParameterValues("selezioneSquadra");
                for(int i=0; i<param.length; i++){
                    String x=param[i];
                    int codice=Integer.parseInt(x);
                    Calciatore c=calDAO.doRetrieveByCod(codice);
                    c.setScelto(true);
                    calDAO.doChanges(c);
                } //Prendo i selezionati e li setto a True into -> Squadra Sommario
                response.sendRedirect(address + "/rs/sommario");
                break;
        }
    }
}
