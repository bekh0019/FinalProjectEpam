package com.jdbc.application.dao;

import com.jdbc.application.model.Reader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;
/** @author Artem Bekh
 * This class contains Template Method. Method extract
 * is implemented in child class ReaderExtractor.
 */
abstract class AbstractReaderExtractor {
    protected abstract Reader extract(ResultSet rs) throws SQLException;

    public Set<Reader> extractAll(ResultSet rs) throws SQLException {
        Set<Reader> result = new TreeSet<>();
        while (rs.next()){
            result.add(extract(rs));
        }
        return result;
    }
}
