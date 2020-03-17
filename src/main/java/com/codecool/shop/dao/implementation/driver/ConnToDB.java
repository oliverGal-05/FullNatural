package com.codecool.shop.dao.implementation.driver;

import java.sql.*;
import java.util.Properties;

public class ConnToDB {

    private static String user;
    private static String password;
    private static String url;
    private static Connection cursor;
    private static ConnToDB instance;

    private ConnToDB() {
        user ="ccshop";
        password = "1231";
        url = "jdbc:postgresql://localhost:5432/codecoolshop";
    }

    private void connectDb() {
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        try {
            cursor = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getDb() {
        if (instance == null){
            instance = new ConnToDB();
        }
        instance.connectDb();
        return cursor;
    }

}