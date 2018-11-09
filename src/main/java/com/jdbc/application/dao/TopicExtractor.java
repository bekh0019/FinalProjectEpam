package com.jdbc.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author Bekh Artem
 * Here is an implementation of method extract
 * @see com.jdbc.application.dao.AbstractTopicExtractor
 */
public class TopicExtractor extends AbstractTopicExtractor {
    @Override
    public String extract(ResultSet rs) throws SQLException {
        return rs.getString("topic");
    }
}
