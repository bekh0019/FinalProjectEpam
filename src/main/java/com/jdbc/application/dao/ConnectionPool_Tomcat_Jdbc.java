package com.jdbc.application.dao;

import com.jdbc.application.dao.ConnectionFactory;
import com.jdbc.application.service.Settings;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Bekh Artem
 * class tunes Tomcat ConnectionPool and implements method
 * from ConnectionFactory
 */
public class ConnectionPool_Tomcat_Jdbc implements ConnectionFactory {

    private final DataSource dataSource;

    public Connection newConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * @Constructor for Tomcat JDBC Connection Pool with its tuning
     *
     */
    public ConnectionPool_Tomcat_Jdbc() {

        PoolProperties p = new PoolProperties();
        Settings settings = Settings.getInstance();
        p.setUrl(settings.value("jdbc.url"));//<--return DB URL from mysql.properties
        p.setDriverClassName(settings.value("jdbc.driver_class"));//<--return DB driver class name from mysql.properties
        p.setUsername(settings.value("jdbc.username"));//<--return users credentials from mysql.properties
        p.setPassword(settings.value("jdbc.password"));//<--return users credentials from mysql.properties
        p.setDefaultAutoCommit(false);//<-- to avoid unexpected commits in DB
        p.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);//<--protects from "dirty" reading and lost update,but not from phantom and unrepeatable reading
        p.setJmxEnabled(true);//<-- to monitor the state of container via VisualVM
        p.setTestWhileIdle(false);//<--test connections,here not used
        p.setTestOnBorrow(true);//<-- also test connections,but in more efficient way
        p.setValidationQuery("SELECT 1");//<--validate connections from this pool before returning them to the caller
        p.setTestOnReturn(false);//<--indication of whether objects will be validated before being returned to the pool
        p.setValidationInterval(30000);//<--in addition to running validations on connections, avoid running them too frequently
        p.setTimeBetweenEvictionRunsMillis(5000);//<-- The number of milliseconds to sleep between runs of the idle connection validation/cleaner thread
        p.setMaxActive(100);//<--The maximum number of active connections that can be allocated from this pool at the same time
        p.setInitialSize(10);//<--The initial number of connections that are created when the pool is started
        p.setMaxWait(30000);//<--The maximum number of milliseconds that the pool will wait before throwing an exception
        p.setRemoveAbandonedTimeout(60);//<--Timeout in seconds before an abandoned(in use) connection can be removed.
        p.setMinEvictableIdleTimeMillis(30000);//<--The minimum amount of time an object may sit idle in the pool before it is eligible for eviction.
        p.setMinIdle(10);//<--The minimum number of established connections that should be kept in the pool at all times.
        p.setLogAbandoned(true);//<--Flag to log stack traces for application code which abandoned a Connection.
        p.setRemoveAbandoned(true);//<--Flag to remove abandoned connections if they exceed the removeAbandonedTimeout.
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");//<--flexible and pluggable interceptors to create any customizations around the pool, the query execution and the result set handling.
        dataSource = new DataSource();
        dataSource.setPoolProperties(p);
    }
}
