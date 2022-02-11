package model.voto;

import model.calciatore.Calciatore;
import model.utils.ConPool;
import java.sql.*;

/**
 * Questa classe modella le interazioni tra la classe Voto e la base di dati. Sono previsti i metodi
 * principali delle operazioni CRUD
 */
public class VotoDAO {

    /**
     * Inserisce un voto all'interno della base di dati
     *
     * @param c il calciatore fk
     * @param v il voto da inserire
     * @return
     */
    public void addVoto(Calciatore c, Voto v) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "insert into regola_72.voto (n_giornata, voto, cal_fk) " +
                            "values (?,?,?);");
            ps.setInt(1, v.getnGiornata());
            ps.setDouble(2, v.getVoto());
            ps.setInt(3, c.getCod());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    //do Retrieve voto By Calciatore e giornata
    //do Retrieve Lista Voti By giornata
}