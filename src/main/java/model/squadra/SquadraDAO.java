package model.squadra;


import model.utils.ConPool;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SquadraDAO {
    /**
     * Inserisce una Squadra all'interno della base di dati
     * @param s la Squadra
     */
    public void addSquadra(Squadra s) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "insert into regola_72.squadra (nome, attacco, difesa) " +
                            "values (?,?,?);");
            ps.setString(1, s.getNome());
            ps.setDouble(2, s.getAttacco());
            ps.setDouble(3, s.getDifesa());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Recupera la Squadra con input il nome
     * @param nome nome della Squadra da ricercare
     * @return Squadra
     */
    public Squadra doRetrieveByNome(String nome){
        Squadra s=new Squadra();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM squadra as squ WHERE squ.nome=?");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            SquadraExtractor sExt= new SquadraExtractor();
            if (rs.next())
                s=sExt.extract(rs);
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return s;
    }
}
