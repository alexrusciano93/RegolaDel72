package model.squadra;

import model.calciatore.Calciatore;
import model.calciatore.CalciatoreDAO;
import model.utils.ResultSetExtractor;
import model.voto.Voto;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SquadraExtractor implements ResultSetExtractor {
    @Override
    public Squadra extract(ResultSet rs) throws SQLException, IOException {
        Squadra s = new Squadra();
        s.setNome(rs.getString("squ.nome"));
        s.setAttacco(rs.getDouble("squ.att"));
        s.setDifesa(rs.getDouble("squ.dif"));
        return s;
    }
}
