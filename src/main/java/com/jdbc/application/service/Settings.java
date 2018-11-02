package com.jdbc.application.service;

import java.io.IOException;
import java.util.Properties;

/**
 * Класс, позволяющий считать файл .properties и в нём по ключу считать значение
 */
public class Settings {
    private static final Settings INSTANCE = new Settings();

    private final Properties properties = new Properties();

    private Settings(){
        try{
            properties.load(getClass().getClassLoader().getResourceAsStream("mysql.properties"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Settings getInstance(){
        return INSTANCE;
    }

    public String value(String key){
        return this.properties.getProperty(key);
    }
}
