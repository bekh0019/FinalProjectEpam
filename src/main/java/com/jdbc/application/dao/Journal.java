package com.jdbc.application.dao;

import java.io.Serializable;
import java.util.Objects;

public class Journal implements Serializable {
    private int id;
    private String title;
    private String topic;
    private int price;

    public Journal(int id, String title, String topic, int price) {
        this.id = id;
        this.title = title;
        this.topic = topic;
        this.price = price;
    }

    public Journal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Journal)) return false;
        Journal journal = (Journal) o;
        return id == journal.id &&
                price == journal.price &&
                Objects.equals(title, journal.title) &&
                Objects.equals(topic, journal.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, topic, price);
    }
}
