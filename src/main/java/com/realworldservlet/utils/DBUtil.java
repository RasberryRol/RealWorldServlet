package com.realworldservlet.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/realworldservlet", "root", "D!lemma628");
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        getConnection();
        System.out.println("Connected");
    }
}
