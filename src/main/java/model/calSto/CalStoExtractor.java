package model.calSto;

import model.calciatore.Calciatore;
import model.calciatore.CalciatoreDAO;
import model.storico.Storico;
import model.storico.StoricoDAO;
import model.utils.ResultSetExtractor;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CalStoExtractor implements ResultSetExtractor {
    private CalciatoreDAO cDAO=new CalciatoreDAO();
    private StoricoDAO sDAO=new StoricoDAO();

    @Override
    public CalSto extract(ResultSet rs) throws SQLException, IOException {
        CalSto cs=new CalSto();
        int codCalciatore=rs.getInt("cs.cal_fk");
        int storico=rs.getInt("cs.sto_fk");
        Calciatore c=cDAO.doRetrieveByCod(codCalciatore);
        cs.setCalciatore(c);
        Storico s=sDAO.doRetrieveByGiornata(storico);
        cs.setStorico(s);
        return cs;
    }
}
