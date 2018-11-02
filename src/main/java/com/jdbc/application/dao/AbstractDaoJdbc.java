package com.jdbc.application.dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDaoJdbc {

    private final ConnectionFactory factory = new ConnectionPool_Tomcat_Jdbc();

    protected Connection getSerializableConnection() throws SQLException {
        try{
            /*   result.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            result.setAutoCommit(false);*/
            return factory.newConnection();
        }
        catch(SQLException e){
            throw new SQLException("Exception while acquiring connection from Connection Pool");
        }
    }
}
