package com.closetkeeper.dressy.dao;

/**
 * Interface for the application's datasets for all real and mock user accounts. Forms a basic contract between any class
 * that implements this interface and provides functions to connect to both the server and local cache.
 *
 * <br>
 * <br>Created by Tim on 10/18/22.
 * <br>Last Modified on 10/31/2022.
 */
public interface IAccountData {

    //Possibly will need some static variables that can be accessed by outside classes implementing this interface

    default void connectToServer(){
        //ToDo: Link to the live database
    }

    default void connectToCache(){
        //ToDo: Link to local cache
    }
}