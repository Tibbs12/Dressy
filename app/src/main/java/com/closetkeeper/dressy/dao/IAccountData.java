package com.closetkeeper.dressy.dao;

import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

/**
 * Interface for the application's datasets for all real and mock user accounts. Forms a basic contract between any class
 * that implements this interface and provides functions to connect to both the server and local cache.
 *
 * <br>
 * <br>Created by Tim on 10/18/22.
 * <br>Last Modified on 11/06/22.
 */
public interface IAccountData {


    public final static  String ACCOUNT_DATA_FILENAME = "userData.txt";

    /**
     * Default function provided by IAccountData interface that sets up the connection between the application and live server.
     * Returns null and logs error if the connection fails.
     * @return Connection link from application to server.
     */
    default Connection connectToServer(){
        String databaseName = "senf22g9";
        String userName = "senf22g9";
        String password = "Sce4620092!!";
        String url = "jdbc:mysql://dcm.uhcl.edu/" + databaseName + "?verifyServerCertificate=false&useSSL=true";
        Connection dbConnect;

        //Try to connect to the server. Return null if connection fails.
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");

            dbConnect = DriverManager.getConnection(url, userName, password);
            return dbConnect;
        }
        catch (Exception e){
            System.out.println("ERROR: Connection to the server failed - " + e.getMessage());
            return null;
        }
    }
}