package model.voto;

import model.calciatore.Calciatore;
import model.utils.ConPool;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Questa classe modella le interazioni tra la classe Voto e la base di dati. Sono previsti i metodi
 * principali delle operazioni CRUD
 */
public class VotoDAO {

    /**
     * Inserisce un voto all'interno della base di dati
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

    /**
     * Recupera un voto a partire dal calciatore e num. giornata
     * @param cod l'identificativo da considerare
     * @return calciatore
     */
    public Voto doRetrieveByCalciatoreAndGiornata(int nGiornata,int cod){
        Voto v = new Voto();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM voto as vot WHERE vot.cal_fk=? and vot.n_giornata=?");
            ps.setInt(1, cod);
            ps.setInt(2,nGiornata);
            ResultSet rs = ps.executeQuery();
            VotoExtractor votoExt= new VotoExtractor();
            if (rs.next())
                v=votoExt.extract(rs);
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return v;
    }

    /**
     * Recupera i voti facenti parte di una giornata
     * @param nGiornata il numero della giornata
     * @return lista di voti
     */
    public ArrayList<Voto> doRetrieveByGiornata(int nGiornata){
        ArrayList<Voto> result=new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM voto as vot WHERE vot.n_giornata=?");
            ps.setInt(1, nGiornata);
            ResultSet rs = ps.executeQuery();
            VotoExtractor votoExt= new VotoExtractor();
            while(rs.next()) {
                result.add(votoExt.extract(rs));
            }
            return result;
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    /**
     * Elimina una giornata di voti dalla base di dati
     * @param giornata l'identificativo della giornata da eliminare
     */
    public void deleteByGiornata(int giornata){
        try (Connection con = ConPool.getConnection()) {
            String query ="DELETE FROM voto as vot WHERE vot.n_giornata = (?);";
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