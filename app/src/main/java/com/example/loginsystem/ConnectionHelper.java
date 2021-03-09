package com.example.loginsystem;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {

    Connection con;
    String name, pass, ip, port, database;
    @SuppressLint("NewApi")
    public Connection connectionclass(){
        ip="192.168.3.23";
        database="Cooking_App";
        name="klein";
        pass="Project666";
        port="1433";

        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection= null;
        String ConnectionURL=null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL="jdbc:jtds:sqlserver://cookingapp.database.windows.net:1433;DatabaseName=Cooking_App;user=klein@cookingapp;password=Project666;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            connection= DriverManager.getConnection(ConnectionURL);
        }
        catch (Exception ex){
            Log.e("Error", ex.getMessage());
        }
        return connection;
    }
}
