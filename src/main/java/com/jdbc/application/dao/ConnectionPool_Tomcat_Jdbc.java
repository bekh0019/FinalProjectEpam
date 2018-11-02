package com.jdbc.application.dao;

import com.jdbc.application.dao.ConnectionFactory;
import com.jdbc.application.service.Settings;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool_Tomcat_Jdbc implements ConnectionFactory {

    private final DataSource dataSource;
    private final Settings settings = Settings.getInstance();

    public Connection newConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * @Constructor for Tomcat JDBC Connection Pool with its tuning
     * @author Bekh Artem
     */
    public ConnectionPool_Tomcat_Jdbc() {

        PoolProperties p = new PoolProperties();
        p.setUrl(settings.value("jdbc.url"));
        p.setDriverClassName(settings.value("jdbc.driver_class"));
        p.setUsername(settings.value("jdbc.username"));
        p.setPassword(settings.value("jdbc.password"));
        p.setDefaultAutoCommit(false);
        p.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(10);
        p.setInitialSize(3);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(600);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(3);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        dataSource = new DataSource();
        dataSource.setPoolProperties(p);
    }
}
