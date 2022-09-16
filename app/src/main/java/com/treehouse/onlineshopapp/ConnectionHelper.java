package com.treehouse.onlineshopapp;

import android.os.StrictMode;

import java.sql.Connection;

public class ConnectionHelper {
    Connection con;
    String uname, pass, ip, port, database;

    public Connection connectionclass() {
        ip = "172.1.1.0";
        database = "UserAccounts";
        uname="sa";
        pass="reallyStrongPwd123";
        port="1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy()
    }
}
