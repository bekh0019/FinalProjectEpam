package com.jdbc.application.dao;

import com.jdbc.application.model.Journal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/** @author Artem Bekh
 * This class contains Template Method. Method extract
 * is implemented in child class JournalExtractor.
 */
abstract class AbstractJournalExtractor {
    protected abstract Journal extract(ResultSet rs) throws SQLException;

    public List<Journal> extractAll(ResultSet rs) throws SQLException {
        List<Journal> result = new ArrayList<>();
        while (rs.next()){
            result.add(extract(rs));
        }
        return result;
    }
}
