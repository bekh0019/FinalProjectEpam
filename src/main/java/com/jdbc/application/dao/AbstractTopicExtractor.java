package com.jdbc.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTopicExtractor {
    public abstract String extract(ResultSet rs) throws SQLException;

    public List<String> extractAll(ResultSet rs) throws SQLException {
        List<String> result = new ArrayList<>();
        while (rs.next()){
            result.add(extract(rs));
        }
        return result;
    }
}
