package com.big0soft.resource.encryption.md5;


import java.io.IOException;
import java.util.Properties;

public class InitConfigFileUtils {


    private static InitConfigFileUtils instance;
    private Properties config;

    public InitConfigFileUtils() {
        initConfig("config.properties");

    }

    public static InitConfigFileUtils getInstance() {
        if (instance == null) {
            instance = new InitConfigFileUtils();
        }
        return instance;
    }

    public Properties getConfig() {
        return config;
    }


    public void initConfig(String path) {
        Properties pro = new Properties();
        try {
            pro.load(InitConfigFileUtils.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.config = pro;

    }
}