package com.jdbc.application;

import org.apache.log4j.jdbc.JDBCAppender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyJdbcAppender extends JDBCAppender {
    @Override
    protected Connection getConnection() throws SQLException {
        if (!DriverManager.getDrivers().hasMoreElements())
            setDriver("com.mysql.jdbc.Driver");

        if (connection == null) {
            connection = DriverManager.getConnection(databaseURL, databaseUser,
                    databasePassword);
        }

        return connection;
    }
}
