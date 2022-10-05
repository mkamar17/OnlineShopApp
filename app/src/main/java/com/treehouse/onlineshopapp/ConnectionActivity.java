package com.treehouse.onlineshopapp;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionActivity extends MainActivity {
    public static void main(String args[]) throws ClassNotFoundException
    {
        String name,pass,url;
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            url="jdbc:mysql://localhost:1433/master";
            name="sa";
            pass="reallyStrongPwd123";
            con = DriverManager.getConnection(url,name,pass);
            System.out.println("Connection created");
            con.close();
            System.out.println("Connection closed");
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

