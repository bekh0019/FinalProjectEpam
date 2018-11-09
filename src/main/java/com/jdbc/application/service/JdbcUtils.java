package com.jdbc.application.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Util methods for DAO layer
 */
public final class JdbcUtils {

    public JdbcUtils() {
    }

    public static void closeQuietly(ResultSet rs){

        if(rs != null){
            try{
                rs.close();
            }
            catch (SQLException e){
                // NOP
            }
        }
    }

    public static void closeQuietly(Statement statement){

        if(statement != null){
            try{
                statement.close();
            }
            catch (SQLException e){
                // NOP
            }
        }
    }

    public static void closeQuietly(Connection connection){

        if(connection != null){
            try{
                connection.close();
            }
            catch (SQLException e){
                // NOP
            }
        }
    }

    public static void rollbackQuietly(Connection conn){

        if(conn != null){
            try{
                conn.rollback();
            }
            catch (SQLException e){
                // NOP
            }
        }
    }    
}
