package model.calSto;

import model.calciatore.Calciatore;
import model.storico.Storico;
import model.utils.ConPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CalStoDAO {
    /**
     * Inserisce la partecipazione all'interno della base di dati
     * @param c calciatore che partecipa alla giornata dello storico
     * @param s storico a cui partecipa il calciatore
     */
    public void addCalSto(Calciatore c, Storico s){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO calsto (cal_fk,sto_fk) VALUES(?,?)");
            ps.setInt(1, c.getCod());
            ps.setInt(2, s.getnGiornata());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Recupera tutti i calciatori che partecipano ad una giornata di storico
     * @param giornata giornata dello storico
     * @return la lista di calciatori che partecipano allo storico
     */
    public ArrayList<CalSto> doRetrieveCalciatoriWithStorico(int giornata){
        ArrayList<CalSto> result = new ArrayList<CalSto>();
        try (Connection con = ConPool.getConnection()) {
            String query = "SELECT * FROM calsto as cs WHERE cs.sto_fk = (?);";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, giornata);
            ResultSet rs = ps.executeQuery();
            CalStoExtractor csExt = new CalStoExtractor();
            while(rs.next()) {
                CalSto cs;
                cs = csExt.extract(rs);
                result.add(cs);
            }
        } catch (SQLException | IOException throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    /**
     * Recupera tutti gli storici a cui ha partecipato un calciatore
     * @param cod codice del calciatore
     * @return la lista di storici a cui ha partecipato un calciatore
     */
    public ArrayList<CalSto> doRetrieveStoriciWithCodice(int cod){
        ArrayList<CalSto> res=new ArrayList<CalSto>();
        try (Connection con = ConPool.getConnection()) {
            String query = "SELECT * FROM calsto as cs WHERE cs.cal_fk = (?);";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, cod);
            ResultSet rs = ps.executeQuery();
            CalStoExtractor csExt = new CalStoExtractor();
            while( rs.next()) {
                CalSto cs;
                cs = csExt.extract(rs);
                res.add(cs);
            }
        } catch (SQLException | IOException throwable) {
            throwable.printStackTrace();
        }
        return res;
    }


}
