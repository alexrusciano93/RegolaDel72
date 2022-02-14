package model.storico;


import model.utils.ResultSetExtractor;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoricoExtractor implements ResultSetExtractor {
    @Override
    public Storico extract(ResultSet rs) throws SQLException, IOException {
        Storico s = new Storico();
        s.setnGiornata(rs.getInt("vot.n_giornata"));
        s.setTotale(rs.getDouble("vot.totale"));
        return s;
    }
}
