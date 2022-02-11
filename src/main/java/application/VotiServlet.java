package application;

import model.calciatore.Calciatore;
import model.calciatore.CalciatoreDAO;
import model.utils.FillDatabase;

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
        FillDatabase fill=new FillDatabase();
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path) {
            case "/carica":
                request.getRequestDispatcher("/WEB-INF/interface/carica.jsp").forward(request, response);
                break;
            case "/caricaVoti":
                String x=request.getParameter("numGiornata");
                System.out.println(x);
                int giornata=Integer.parseInt(x);
                try {
                    fill.generateVoti(giornata);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                request.getRequestDispatcher("/WEB-INF/interface/carica.jsp").forward(request, response);
                break;
        }

    }
}
