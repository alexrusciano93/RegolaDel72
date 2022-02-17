package model.calendario;

import model.utils.ConPool;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CalendarioDAO {

    /**
     * Inserisce una partita del Calendario all'interno della base di dati
     * @param c la partita da aggiungere al Calendario
     */
    public void addCalendario(Calendario c) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "insert into regola_72.calendario (nGiornata, sq1_fk, sq2_fk) " +
                            "values (?,?,?);");
            ps.setInt(1, c.getnGiornata());
            ps.setString(2, c.getCasa().getNome());
            ps.setString(3, c.getTrasferta().getNome());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Recupera le partite facenti parte di una giornata di Calendario
     * @param nGiornata il numero della giornata
     * @return lista di partite
     */
    public ArrayList<Calendario> doRetrieveByGiornata(int nGiornata){
        ArrayList<Calendario> result=new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM calendario as cal WHERE cal.n_giornata=?");
            ps.setInt(1, nGiornata);
            ResultSet rs = ps.executeQuery();
            CalendarioExtractor cExt= new CalendarioExtractor();
            while(rs.next()) {
                result.add(cExt.extract(rs));
            }
            return result;
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
