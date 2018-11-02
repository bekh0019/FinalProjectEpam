package com.jdbc.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JournalExtractor extends AbstractJournalExtractor {
    @Override
    public Journal extract(ResultSet rs) throws SQLException {
        return new Journal(rs.getInt("id"),rs.getString("title"),rs.getString("topic"),rs.getInt("price"));
    }
}
