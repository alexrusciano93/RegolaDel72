package model.calSto;

import model.utils.ResultSetExtractor;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CalStoExtractor implements ResultSetExtractor {
    @Override
    public CalSto extract(ResultSet rs) throws SQLException, IOException {
        CalSto cs=new CalSto();
        return cs;
    }
}
