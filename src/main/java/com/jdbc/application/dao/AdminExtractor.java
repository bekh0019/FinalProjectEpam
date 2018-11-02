package com.jdbc.application.dao;


import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminExtractor extends AbstractAdminExtractor {

    public Admin extract(ResultSet rs) throws SQLException {
        return new Admin(rs.getInt("id"), rs.getString("login"), rs.getString("password"));
    }
}
