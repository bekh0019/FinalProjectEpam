package com.jdbc.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

public abstract class AbstractReaderExtractor {
    public abstract Reader extract(ResultSet rs) throws SQLException;

    public Set<Reader> extractAll(ResultSet rs) throws SQLException {
        Set<Reader> result = new TreeSet<>();
        while (rs.next()){
            result.add(extract(rs));
        }
        return result;
    }
}
