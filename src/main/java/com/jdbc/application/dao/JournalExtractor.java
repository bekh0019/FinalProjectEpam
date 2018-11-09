package com.jdbc.application.dao;

import com.jdbc.application.model.Journal;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author Bekh Artem
 * Here is an implementation of method extract
 * @see com.jdbc.application.dao.AbstractJournalExtractor
 */
public class JournalExtractor extends AbstractJournalExtractor {
    @Override
    public Journal extract(ResultSet rs) throws SQLException {
        return new Journal(rs.getInt("id"),rs.getString("title"),rs.getString("topic"),rs.getInt("price"));
    }
}
