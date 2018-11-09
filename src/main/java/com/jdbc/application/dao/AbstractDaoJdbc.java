package com.jdbc.application.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Bekh Artem
 * class gets object of ConnectionPool and obtain DB connection
 */
abstract class AbstractDaoJdbc {
    private static final ConnectionFactory factory = new ConnectionPool_Tomcat_Jdbc();

    Connection getSerializableConnection() throws SQLException {
        try{
            /*   result.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            result.setAutoCommit(false);<-- these are settings for connection pool c3p0*/
            return factory.newConnection();
        }
        catch(SQLException e){
            throw new SQLException("Exception while acquiring connection from Connection Pool");
        }
    }
}
