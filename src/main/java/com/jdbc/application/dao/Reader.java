package com.jdbc.application.dao;

import com.jdbc.application.service.JdbcUtils;

import java.sql.*;
import java.util.Objects;

public class Reader extends CommonDaoJdbc implements Comparable<Reader> {
    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private int unverifiedBalance;
    private boolean access;
    private int verifiedBalance;


    public Reader() {
    }

    public int getUnverifiedBalance() {
        return unverifiedBalance;
    }

    public void setUnverifiedBalance(int unverifiedBalance) {
        this.unverifiedBalance = unverifiedBalance;
    }

    public int getVerifiedBalance() {
        return verifiedBalance;
    }

    public void setVerifiedBalance(int verifiedBalance) {
        this.verifiedBalance = verifiedBalance;
    }

    public Reader(int id, String name, String surname, String login, String password, String email, int unverifiedBalance, boolean access, int verifiedBalance) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.unverifiedBalance = unverifiedBalance;
        this.access = access;
        this.verifiedBalance = verifiedBalance;
    }

    @Override
    public int compareTo(Reader o) {
return this.id-o.id;
    }
    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }





}
