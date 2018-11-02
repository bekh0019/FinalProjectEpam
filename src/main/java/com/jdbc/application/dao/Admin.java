package com.jdbc.application.dao;

import com.jdbc.application.service.JdbcUtils;

import java.sql.*;
import java.util.Objects;
import java.util.Set;

public class Admin   {
    private int id;
    private String login;
    private String password;


    public Admin(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    }





