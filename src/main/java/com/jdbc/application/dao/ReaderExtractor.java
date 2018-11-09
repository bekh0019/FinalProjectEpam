package com.jdbc.application.dao;

import com.jdbc.application.model.Reader;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author Bekh Artem
 * Here is an implementation of method extract
 * @see com.jdbc.application.dao.AbstractReaderExtractor
 */
public class ReaderExtractor extends AbstractReaderExtractor {
    @Override
    public Reader extract(ResultSet rs) throws SQLException {
        return new Reader(rs.getInt("id"),rs.getString("name"),rs.getString("surname"),
                rs.getString("login"),rs.getString("password"),rs.getString("email"),rs.getInt("unverifiedBalance"),
                rs.getBoolean("access"),rs.getInt("verifiedBalance"));
    }
}
