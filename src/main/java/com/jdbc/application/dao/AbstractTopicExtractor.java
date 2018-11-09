package com.jdbc.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/** @author Artem Bekh
 * This class contains Template Method. Method extract
 * is implemented in child class TopicExtractor.
 */
abstract class AbstractTopicExtractor {
    protected abstract String extract(ResultSet rs) throws SQLException;

    public List<String> extractAll(ResultSet rs) throws SQLException {
        List<String> result = new ArrayList<>();
        while (rs.next()){
            result.add(extract(rs));
        }
        return result;
    }
}
