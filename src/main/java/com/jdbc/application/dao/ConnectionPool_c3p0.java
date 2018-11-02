package com.jdbc.application.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.jdbc.application.service.Settings;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Standard Connection Pool c3p0 for dao layer and the case using MySQL database.
 * The pattern Singleton is used.
 * @author Bekh Artem
 */
public class ConnectionPool_c3p0 implements ConnectionFactory {

    private final DataSource dataSource;
    private final Settings settings = Settings.getInstance();

    public Connection newConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * @Constructor for Connection Pool with its tuning
     * @author Bekh Artem
     */
    public ConnectionPool_c3p0() {
        try{
            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setDriverClass(settings.value("jdbc.driver_class"));
            cpds.setJdbcUrl(settings.value("jdbc.url"));
            cpds.setUser(settings.value("jdbc.username"));
            cpds.setPassword(settings.value("jdbc.password"));

            cpds.setMaxStatements(1000);
            cpds.setMaxStatementsPerConnection(50);
            cpds.setMinPoolSize(3);
            cpds.setAcquireIncrement(10);
            cpds.setMaxPoolSize(200); //

            this.dataSource = cpds;
        } catch (PropertyVetoException e) {
            throw new RuntimeException("Exception during configuring cp30", e);
        }
    }
}
