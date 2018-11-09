package com.jdbc.application.model;

import java.io.Serializable;

/**
 * @author Bekh Artem
 * Bean class,which implements Serializable to work safety
 * with Session
 */
public class Admin implements Serializable {
    private int id;
    private String login;
    private String password;

    public Admin() {
    }

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





