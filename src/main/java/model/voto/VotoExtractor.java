package model.voto;

import model.calciatore.Calciatore;
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

public class VotoExtractor implements ResultSetExtractor {
    @Override
    public Voto extract(ResultSet rs) throws SQLException, IOException {
        Voto v = new Voto();
        v.setVoto(rs.getDouble("vot.voto"));
        v.setnGiornata(rs.getInt("vot.n_giornata"));
        int codCalciatore=rs.getInt("vot.cal_fk");
        Calciatore c=new Calciatore();
        c.setCod(codCalciatore);
        v.setCalciatore(c);
        return v;
    }
}
