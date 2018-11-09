package com.jdbc.application.dao;
import com.jdbc.application.model.Admin;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Bekh Artem
 * Here is an implementation of method extract
 * @see com.jdbc.application.dao.AbstractAdminExtractor
 */
public class AdminExtractor extends AbstractAdminExtractor {

    public Admin extract(ResultSet rs) throws SQLException {
        return new Admin(rs.getInt("id"), rs.getString("login"), rs.getString("password"));
    }
}
