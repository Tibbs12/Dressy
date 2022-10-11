package com.closetkeeper.dressy.dto;

/**
 * The user's account holds important information like user account number (or account ID), email, and password. When the user registers and is verified, the function
 * "generateAccountNum" needs to be called for correct account number.
 *
 *Created by Tim on 10/11/22
 */

public class Account {

    private int accountNum;
    private String email;
    private String password;

    //Returns the user's email
    public String getEmail() {
        return email;
    }

    //Sets user's email with the provided String parameter
    public void setEmail(String email) {
        this.email = email;
    }

    //Returns the user's password
    public String getPassword() {
        return password;
    }

    //Sets the user's password with the provided String parameter
    public void setPassword(String password) {
        this.password = password;
    }

    //Generates the user's account ID by retrieving the last account ID value from the database, then increments the value by
    //one and returning it.
    public void generateAccountNum(){
        int lastAccountNum_DB = 0; //ToDo: Get lastAccountNum_DB integer from database
        accountNum = lastAccountNum_DB + 1;
    }

    //Returns the user's account ID
    public int getID(){
        return accountNum;
    }
}
