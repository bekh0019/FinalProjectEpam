package com.jdbc.application.model;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * @author Bekh Artem
 * Bean class,which implements Serializable to work safety
 * with Session
 * Object of ResourceBoundle get desired string from "messages"
 * then this value is assigned to a variable with the same name
 * in this class. Then via getter this variable is available on jsp page.
 */
public class LocaleBean implements Serializable {
    private String currentLocale;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String log_In;
    private String logOut;
    private String back;
    private String to1;
    private String cabinet;
    private String exit;
    private String to;
    private String common;
    private String create;
    private String put;
    private String money;
    private String subscribe;
    private String on;
    private String journals;
    private String my;
    private String subscribes;
    private String personal;
    private String reader;
    private String common1;
    private String page1;
    private String log2in;
    private String search;
    private String journal;
    private String enter;
    private String as;
    private String hello;
    private String guest1;
    private String please;
    private String choose;
    private String display;
    private String journals1;
    private String choose1;
    private String topics;
    private String select;
    private String type1;
    private String without;
    private String alphabetic;
    private String reverse;
    private String ascending;
    private String descending;
    private String sort;
    private String list1;
    private String cabinet1;
    private String purchases;
    private String edit;
    private String delete;
    private String journal1;
    private String change;
    private String status;
    private String modify;
    private String readers;
    private String title;
    private String topic;
    private String price;
    private String reader1;
    private String access;
    private String block;
    private String balance;
    private String submit;
    private String active;
    private String blocked;
    private String block1;
    private String unblock;
    private String verify;
    private String confirm;
    private String welcome;
    private String current;

    public String getCurrent() {
        return current;
    }

    private void setCurrent(String current) {
        this.current = current;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getWelcome() {
        return welcome;
    }

    private void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public String getConfirm() {
        return confirm;
    }

    private void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getBlock1() {
        return block1;
    }

    private void setBlock1(String block1) {
        this.block1 = block1;
    }

    public String getUnblock() {
        return unblock;
    }

    private void setUnblock(String unblock) {
        this.unblock = unblock;
    }

    public String getVerify() {
        return verify;
    }

    private void setVerify(String verify) {
        this.verify = verify;
    }

    public String getActive() {
        return active;
    }

    private void setActive(String active) {
        this.active = active;
    }

    public String getBlocked() {
        return blocked;
    }

    private void setBlocked(String blocked) {
        this.blocked = blocked;
    }

    public String getSubmit() {
        return submit;
    }

    private void setSubmit(String submit) {
        this.submit = submit;
    }

    public String getReader1() {
        return reader1;
    }

    private void setReader1(String reader1) {
        this.reader1 = reader1;
    }

    public String getAccess() {
        return access;
    }

    private void setAccess(String access) {
        this.access = access;
    }

    public String getBlock() {
        return block;
    }

    private void setBlock(String block) {
        this.block = block;
    }

    public String getBalance() {
        return balance;
    }

    private void setBalance(String balance) {
        this.balance = balance;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    private void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPrice() {
        return price;
    }

    private void setPrice(String price) {
        this.price = price;
    }

    public String getReaders() {
        return readers;
    }

    private void setReaders(String readers) {
        this.readers = readers;
    }

    public String getModify() {
        return modify;
    }

    private void setModify(String modify) {
        this.modify = modify;
    }

    public String getChange() {
        return change;
    }

    private void setChange(String change) {
        this.change = change;
    }

    public String getStatus() {
        return status;
    }

    private void setStatus(String status) {
        this.status = status;
    }

    public String getJournal1() {
        return journal1;
    }

    private void setJournal1(String journal1) {
        this.journal1 = journal1;
    }

    public String getEdit() {
        return edit;
    }

    private void setEdit(String edit) {
        this.edit = edit;
    }

    public String getDelete() {
        return delete;
    }

    private void setDelete(String delete) {
        this.delete = delete;
    }

    public String getPurchases() {
        return purchases;
    }

    private void setPurchases(String purchases) {
        this.purchases = purchases;
    }

    public String getCabinet1() {
        return cabinet1;
    }

    private void setCabinet1(String cabinet1) {
        this.cabinet1 = cabinet1;
    }

    public String getList1() {
        return list1;
    }

    private void setList1(String list1) {
        this.list1 = list1;
    }

    public String getWithout() {
        return without;
    }

    private void setWithout(String without) {
        this.without = without;
    }

    public String getAlphabetic() {
        return alphabetic;
    }

    private void setAlphabetic(String alphabetic) {
        this.alphabetic = alphabetic;
    }

    public String getReverse() {
        return reverse;
    }

    private void setReverse(String reverse) {
        this.reverse = reverse;
    }

    public String getAscending() {
        return ascending;
    }

    private void setAscending(String ascending) {
        this.ascending = ascending;
    }

    public String getDescending() {
        return descending;
    }

    private void setDescending(String descending) {
        this.descending = descending;
    }

    public String getSort() {
        return sort;
    }

    private void setSort(String sort) {
        this.sort = sort;
    }

    public String getSelect() {
        return select;
    }

    private void setSelect(String select) {
        this.select = select;
    }

    public String getType1() {
        return type1;
    }

    private void setType1(String type1) {
        this.type1 = type1;
    }

    public String getTopics() {
        return topics;
    }

    private void setTopics(String topics) {
        this.topics = topics;
    }

    public String getChoose1() {
        return choose1;
    }

    private void setChoose1(String choose1) {
        this.choose1 = choose1;
    }

    public String getPlease() {
        return please;
    }

    private void setPlease(String please) {
        this.please = please;
    }

    public String getChoose() {
        return choose;
    }

    private void setChoose(String choose) {
        this.choose = choose;
    }

    public String getDisplay() {
        return display;
    }

    private void setDisplay(String display) {
        this.display = display;
    }

    public String getJournals1() {
        return journals1;
    }

    private void setJournals1(String journals1) {
        this.journals1 = journals1;
    }

    public String getHello() {
        return hello;
    }

    private void setHello(String hello) {
        this.hello = hello;
    }

    public String getGuest1() {
        return guest1;
    }

    private void setGuest1(String guest1) {
        this.guest1 = guest1;
    }

    public String getEnter() {
        return enter;
    }

    private void setEnter(String enter) {
        this.enter = enter;
    }

    public String getAs() {
        return as;
    }

    private void setAs(String as) {
        this.as = as;
    }

    public String getGuest() {
        return guest;
    }

    private void setGuest(String guest) {
        this.guest = guest;
    }

    private String guest;

    public String getSearch() {
        return search;
    }

    private void setSearch(String search) {
        this.search = search;
    }

    public String getJournal() {
        return journal;
    }

    private void setJournal(String journal) {
        this.journal = journal;
    }

    public String getLog2in() {
        return log2in;
    }

    private void setLog2in(String log2in) {
        this.log2in = log2in;
    }

    public String getCommon1() {
        return common1;
    }

    private void setCommon1(String common1) {
        this.common1 = common1;
    }

    public String getPage1() {
        return page1;
    }

    private void setPage1(String page1) {
        this.page1 = page1;
    }

    public String getPersonal() {
        return personal;
    }

    private void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getReader() {
        return reader;
    }

    private void setReader(String reader) {
        this.reader = reader;
    }

    public String getMy() {
        return my;
    }

    private void setMy(String my) {
        this.my = my;
    }

    public String getSubscribes() {
        return subscribes;
    }

    private void setSubscribes(String subscribes) {
        this.subscribes = subscribes;
    }

    public String getSubscribe() {
        return subscribe;
    }

    private void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getOn() {
        return on;
    }

    private void setOn(String on) {
        this.on = on;
    }

    public String getJournals() {
        return journals;
    }

    private void setJournals(String journals) {
        this.journals = journals;
    }

    public String getPut() {
        return put;
    }

    private void setPut(String put) {
        this.put = put;
    }

    public String getMoney() {
        return money;
    }

    private void setMoney(String money) {
        this.money = money;
    }

    public String getCreate() {
        return create;
    }

    private void setCreate(String create) {
        this.create = create;
    }

    public String getAccount() {
        return account;
    }

    private void setAccount(String account) {
        this.account = account;
    }

    private String account;

    public String getExit() {
        return exit;
    }

    private void setExit(String exit) {
        this.exit = exit;
    }

    public String getTo() {
        return to;
    }

    private void setTo(String to) {
        this.to = to;
    }

    public String getCommon() {
        return common;
    }

    private void setCommon(String common) {
        this.common = common;
    }

    public String getPage() {
        return page;
    }

    private void setPage(String page) {
        this.page = page;
    }

    private String page;

    public String getCabinet() {
        return cabinet;
    }

    private void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }



    public String getTo1() {
        return to1;
    }

    private void setTo1(String to1) {
        this.to1 = to1;
    }

    public String getBack() {
        return back;
    }

    private void setBack(String back) {
        this.back = back;
    }

    public LocaleBean(){

    }

    public String getLogin() {
        return login;
    }

    private void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    private void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLog_In() {
        return log_In;
    }

    private void setLog_In(String log_In) {

        this.log_In = log_In;
    }

    public String getLogOut() {
        return logOut;
    }

    private void setLogOut(String logOut) {

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
localeBean.setPut(rb.getString("put"));
localeBean.setMoney(rb.getString("money"));
localeBean.setSubscribe(rb.getString("subscribe"));
localeBean.setOn(rb.getString("on"));
localeBean.setJournals(rb.getString("journals"));
localeBean.setMy(rb.getString("my"));
localeBean.setSubscribes(rb.getString("subscribes"));
localeBean.setPersonal(rb.getString("personal"));
localeBean.setReader(rb.getString("reader"));
localeBean.setCommon1(rb.getString("common1"));
localeBean.setPage1(rb.getString("page1"));
localeBean.setLog2in(rb.getString("log2in"));
localeBean.setSearch(rb.getString("search"));
localeBean.setJournal(rb.getString("journal"));
localeBean.setEnter(rb.getString("enter"));
localeBean.setAs(rb.getString("as"));
localeBean.setGuest(rb.getString("guest"));
localeBean.setHello(rb.getString("hello"));
localeBean.setGuest1(rb.getString("guest1"));
localeBean.setPlease(rb.getString("please"));
localeBean.setChoose(rb.getString("choose"));
localeBean.setDisplay(rb.getString("display"));
localeBean.setJournals1(rb.getString("journals1"));
localeBean.setChoose1(rb.getString("choose1"));
localeBean.setTopics(rb.getString("topics"));
localeBean.setSelect(rb.getString("select"));
localeBean.setType1(rb.getString("type1"));
localeBean.setWithout(rb.getString("without"));
localeBean.setAlphabetic(rb.getString("alphabetic"));
localeBean.setReverse(rb.getString("reverse"));
localeBean.setAscending(rb.getString("ascending"));
localeBean.setDescending(rb.getString("descending"));
localeBean.setSort(rb.getString("sort"));
localeBean.setList1(rb.getString("list1"));
localeBean.setCabinet1(rb.getString("cabinet1"));
localeBean.setPurchases(rb.getString("purchases"));
localeBean.setEdit(rb.getString("edit"));
localeBean.setDelete(rb.getString("delete"));
localeBean.setJournal1(rb.getString("journal1"));
localeBean.setChange(rb.getString("change"));
localeBean.setStatus(rb.getString("status"));
localeBean.setModify(rb.getString("modify"));
localeBean.setReaders(rb.getString("readers"));
localeBean.setTitle(rb.getString("title"));
localeBean.setTopic(rb.getString("topic"));
localeBean.setPrice(rb.getString("price"));
localeBean.setReader1(rb.getString("reader1"));
localeBean.setAccess(rb.getString("access"));
localeBean.setBlock(rb.getString("block"));
localeBean.setBalance(rb.getString("balance"));
localeBean.setSubmit(rb.getString("submit"));
localeBean.setActive(rb.getString("active"));
localeBean.setBlocked(rb.getString("blocked"));
localeBean.setBlock1(rb.getString("block1"));
localeBean.setUnblock(rb.getString("unblock"));
localeBean.setVerify(rb.getString("verify"));
localeBean.setConfirm(rb.getString("confirm"));
localeBean.setWelcome(rb.getString("welcome"));
localeBean.setName(rb.getString("name"));
localeBean.setSurname(rb.getString("surname"));
localeBean.setEmail(rb.getString("email"));
localeBean.setCurrent(rb.getString("current"));

        return localeBean;
    }
}
