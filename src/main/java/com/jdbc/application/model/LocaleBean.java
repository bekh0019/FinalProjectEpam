package com.jdbc.application.model;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleBean {
    private String currentLocale;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String log_In;
    private String logOut;
    private String back;
    private String to1;
    private String cabinet;
    private String exit;
    private String to;
    private String common;
    private String create;

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    private String account;

    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    private String page;

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }



    public String getTo1() {
        return to1;
    }

    public void setTo1(String to1) {
        this.to1 = to1;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public   LocaleBean(){

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

    public String getLog_In() {
        return log_In;
    }

    public void setLog_In(String log_In) {

        this.log_In = log_In;
    }

    public String getLogOut() {
        return logOut;
    }

    public void setLogOut(String logOut) {

      this.logOut = logOut;
    }
    public String getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(String currentLocale) {
        this.currentLocale = currentLocale;
    }
    public static LocaleBean localBeanBuilder(ResourceBundle rb, Locale locale){

        LocaleBean localeBean = new LocaleBean();
        localeBean.setLogin(rb.getString("login"));
        localeBean.setPassword(rb.getString("password"));
        localeBean.setLog_In(rb.getString("log_In"));
        localeBean.setLogOut(rb.getString("logout"));
        localeBean.setBack(rb.getString("back"));
        localeBean.setTo1(rb.getString("to1"));
        localeBean.setCabinet(rb.getString("cabinet"));
        localeBean.setExit(rb.getString("exit"));
        localeBean.setTo(rb.getString("to"));
        localeBean.setCommon(rb.getString("common"));
        localeBean.setPage(rb.getString("page"));
        localeBean.setAccount(rb.getString("account"));
        localeBean.setCreate(rb.getString("create"));

        return localeBean;
    }
}
