package model.calendario;

import model.squadra.Squadra;
import model.squadra.SquadraDAO;
import model.utils.ResultSetExtractor;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CalendarioExtractor implements ResultSetExtractor {
    SquadraDAO sDAO=new SquadraDAO();
    @Override
    public Calendario extract(ResultSet rs) throws SQLException, IOException {
        Calendario c=new Calendario();
        c.setnGiornata(rs.getInt("cal.nGiornata"));
        String sq1=rs.getString("cal.sq1_fk");
        String sq2=rs.getString("cal.sq2_fk");
        Squadra s1=sDAO.doRetrieveByNome(sq1);
        Squadra s2=sDAO.doRetrieveByNome(sq2);
        c.setCasa(s1);
        c.setTrasferta(s2);
        return c;
    }
}
