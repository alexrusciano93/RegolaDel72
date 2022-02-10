package model.calciatore;

import model.utils.ResultSetExtractor;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Estrae i dati dei calciatori dalla base di dati e li inserisce in un oggetto Calciatore
 * @return calciatore della base di dati sotto forma di oggetto
 * @throws SQLException
 * @throws IOException
 */

public class CalciatoreExtractor implements ResultSetExtractor {
    @Override
    public Calciatore extract(ResultSet rs) throws SQLException, IOException {
        Calciatore c = new Calciatore();
        c.setNome(rs.getString("cal.nome"));
        c.setRuolo(rs.getString("cal.ruolo"));
        c.setSquadra(rs.getString("cal.squadra"));
        c.setQuotazione(rs.getInt("cal.quotazione"));
        c.setScelto(rs.getBoolean("cal.scelto"));
        c.setCod(rs.getInt("cal.cod"));
        return c;
    }
}
