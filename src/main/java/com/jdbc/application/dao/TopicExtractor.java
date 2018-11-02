package com.jdbc.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicExtractor extends AbstractTopicExtractor {
    @Override
    public String extract(ResultSet rs) throws SQLException {
        return new String(rs.getString("topic"));
    }
}
