package com.jdbc.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderExtractor extends AbstractReaderExtractor {
    @Override
    public Reader extract(ResultSet rs) throws SQLException {
        return new Reader(rs.getInt("id"),rs.getString("name"),rs.getString("surname"),
                rs.getString("login"),rs.getString("password"),rs.getString("email"),rs.getInt("unverifiedBalance"),
                rs.getBoolean("access"),rs.getInt("verifiedBalance"));
    }
}
