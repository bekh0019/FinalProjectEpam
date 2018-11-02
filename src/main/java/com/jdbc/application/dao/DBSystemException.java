package com.jdbc.application.dao;

/**
 * @author Bekh Atrem
 * Exception for DB methods
 */
public class DBSystemException extends Exception {
    private static final long serialVersionUID = 3038970175081657383L;

    public DBSystemException(String message, Throwable cause) {

        super(message, cause);
    }
}
