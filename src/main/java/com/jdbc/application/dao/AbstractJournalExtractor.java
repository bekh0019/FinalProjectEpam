package com.jdbc.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract class AbstractJournalExtractor {
    public abstract Journal extract(ResultSet rs) throws SQLException;

    public List<Journal> extractAll(ResultSet rs) throws SQLException {
        List<Journal> result = new ArrayList<>();
        while (rs.next()){
            result.add(extract(rs));
        }
        return result;
    }
}
