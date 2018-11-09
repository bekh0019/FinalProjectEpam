package com.jdbc.application.model;

import java.io.Serializable;

/**
 * @author Bekh Artem
 * class enum for Roles
 */
public enum  Role implements Serializable {
    Admin,Reader;

    @Override
    public String toString() {
        String result="";
        switch (this){
            case Admin:
                result="admin";
                break;
            case Reader:
                result="reader";
                break;
        }
        return result;
    }
}
