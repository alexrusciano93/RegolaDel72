package model.utils;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetExtractor<B> {
    B extract(ResultSet rs) throws SQLException, IOException;
}
