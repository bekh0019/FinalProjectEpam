package com.jdbc.application.service;

import org.apache.log4j.jdbc.JDBCAppender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * @author Bekh Artem
 *This class overrides standard settings for JDBC Driver to prevent exceptions in log.
 * By default this creates a single connection which is held open
 * until the object is garbage collected.
 */
public class MyJdbcAppender extends JDBCAppender {
    @Override
    protected Connection getConnection() throws SQLException {
        if (!DriverManager.getDrivers().hasMoreElements())
            setDriver("com.mysql.cj.jdbc.Driver");

        if (connection == null) {
            connection = DriverManager.getConnection(databaseURL, databaseUser,
                    databasePassword);
        }

        return connection;
    }
}
