package model.calciatore;

import model.utils.ConPool;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
/**
 * Questa classe modella le interazioni tra la classe Calciatore e la base di dati. Sono previsti i metodi
 * principali delle operazioni CRUD
 */
public class CalciatoreDAO {
    /**
     * Inserisce un calciatore all'interno della base di dati
     * @param c il calciatore da inserire
     * @return
     */
    public void addCalciatore(Calciatore c) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "insert into regola_72.calciatore (nome, ruolo, squadra, scelto, quotazione, cod) " +
                            "values (?,?,?,?,?,?);");
            ps.setString(1, c.getNome());
            ps.setString(2, c.getRuolo());
            ps.setString(3, c.getSquadra());
            ps.setBoolean(4,c.getScelto());
            ps.setInt(5, c.getQuotazione());
            ps.setInt(6, c.getCod());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Apporta delle modifiche al calciatore selezionato
     * @param c il calciatore da modificare
     * @return l'esito della modifica
     */
    public boolean doChanges(Calciatore c){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE calciatore c SET c.nome = (?), c.ruolo = (?), c.squadra =(?), " +
                            "c.quotazione=(?), c.scelto=(?) WHERE c.cod = (?);");
            ps.setString(1, c.getNome());
            ps.setString(2, c.getRuolo());
            ps.setString(3, c.getSquadra());
            ps.setInt(4,c.getQuotazione());
            ps.setBoolean(5, c.getScelto());
            ps.setInt(6, c.getCod());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            return true;
        } catch(SQLException ex){
            return false;
        }
    }

    /**
     * Recupera tutti i calciatori dalla base di dati
     * @return la lista di calciatori
     */
    public ArrayList<Calciatore> doRetrieveAll(){
        ArrayList<Calciatore> result=new ArrayList<Calciatore>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM calciatore as cal");
            ResultSet rs = ps.executeQuery();
            CalciatoreExtractor calExt = new CalciatoreExtractor();
            while(rs.next()) {
                result.add(calExt.extract(rs));
            }
            return result;
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Recupera un calciatore a partire da un identificativo
     * @param cod l'identificativo da considerare
     * @return calciatore
     */
    public Calciatore doRetrieveByCod(int cod){
        Calciatore c = new Calciatore();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM calciatore as cal WHERE cod=?");
            ps.setInt(1, cod);
            ResultSet rs = ps.executeQuery();
            CalciatoreExtractor calExt= new CalciatoreExtractor();
            if (rs.next())
                c=calExt.extract(rs);
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return c;
    }

    /**
     * Recupera i calciatori facenti parte di un ruolo
     * @param ruolo il ruolo da considerare
     * @return lista di calciatori
     */
    public ArrayList<Calciatore> doRetrieveByRuolo(String ruolo){
        ArrayList<Calciatore> result=new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM calciatore as cal WHERE ruolo=?");
            ps.setString(1, ruolo);
            ResultSet rs = ps.executeQuery();
            CalciatoreExtractor calExt= new CalciatoreExtractor();
            while(rs.next()) {
                result.add(calExt.extract(rs));
            }
            return result;
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ArrayList<Calciatore> doRetrieveByScelto(){
        ArrayList<Calciatore> result=new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM calciatore as cal WHERE scelto=?");
            ps.setBoolean(1, true);
            ResultSet rs = ps.executeQuery();
            CalciatoreExtractor calExt= new CalciatoreExtractor();
            while(rs.next()) {
                result.add(calExt.extract(rs));
            }
            return result;
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ArrayList<Calciatore> doRetrieveBySceltoAndRuolo(String ruolo){
        ArrayList<Calciatore> result=new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM calciatore as cal WHERE scelto=? and ruolo=?");
            ps.setBoolean(1, true);
            ps.setString(2,ruolo);
            ResultSet rs = ps.executeQuery();
            CalciatoreExtractor calExt= new CalciatoreExtractor();
            while(rs.next()) {
                result.add(calExt.extract(rs));
            }
            return result;
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }


}









