package com.jdbc.application.dao;
import java.sql.Connection;
import java.sql.SQLException;

interface ConnectionFactory {
    /**
     * @author Bekh Artem
     * @return object of Connection
     * implemented in class @see com.jdbc.application.dao.ConnectionPool_Tomcat_Jdbc
     * @throws SQLException
     */
    Connection newConnection() throws SQLException;
}
