package model.storico;

import model.calciatore.Calciatore;
import model.utils.ConPool;
import model.voto.Voto;
import model.voto.VotoExtractor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StoricoDAO {

    /**
     * Inserisce uno storico all'interno della base di dati
     * @param s lo storico
     */
    public void addStorico(Storico s) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "insert into regola_72.storico (n_giornata, totale) " +
                            "values (?,?);");
            ps.setInt(1, s.getnGiornata());
            ps.setDouble(2, s.getTotale());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Recupera lo storico facente parte di una giornata
     * @param nGiornata il numero della giornata
     * @return storico
     */
    public Storico doRetrieveByGiornata(int nGiornata){
        Storico result=new Storico();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM storico as sto WHERE sto.n_giornata=?");
            ps.setInt(1, nGiornata);
            ResultSet rs = ps.executeQuery();
            StoricoExtractor stoExt= new StoricoExtractor();
            if (rs.next()){
                result=stoExt.extract(rs);
            }
            return result;
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Elimina uno storico dalla base di dati data una giornata in input
     * @param giornata l'identificativo della giornata dello storico da eliminare
     */
    public void deleteByGiornata(int giornata){
        try (Connection con = ConPool.getConnection()) {
            String query ="DELETE FROM storico as sto WHERE sto.n_giornata = (?);";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, giornata);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}
