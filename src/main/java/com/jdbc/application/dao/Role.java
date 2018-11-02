package com.jdbc.application.dao;

public enum  Role {
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
