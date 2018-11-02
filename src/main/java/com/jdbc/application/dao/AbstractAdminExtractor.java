package com.jdbc.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

public abstract class AbstractAdminExtractor {

    public abstract Admin extract(ResultSet rs) throws SQLException;

    public Set<Admin> extractAll(ResultSet rs) throws SQLException {
        Set<Admin> result = new TreeSet<Admin>();
        while (rs.next()){
            result.add(extract(rs));
        }
        return result;
    }
}
