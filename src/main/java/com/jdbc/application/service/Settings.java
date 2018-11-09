package com.jdbc.application.service;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Bekh Artem
 * Singleton(not lazy) which provides access to .properties
 * and get value by the key
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
