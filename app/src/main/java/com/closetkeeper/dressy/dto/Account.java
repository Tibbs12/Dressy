package com.closetkeeper.dressy.dto;

import com.closetkeeper.dressy.dao.Database;
import com.closetkeeper.dressy.dao.DatabaseMock;

/**
 * Holds important data like user account number (or account ID), email, and password. When the user registers
 * for the first time, the default constructor will automatically create an account ID for the user. If the user already has
 * an account, then the secondary constructor needs to be executed allowing this class to receive the existing user's data.
 *
 * <br>
 * <br>Created by Tim on 10/11/22.
 * <br>Last Modified on 11/13/2022.
 */
public class Account {

    private int accountNum;
    private String email;
    private String password;

    public Account(){
        accountNum = -1;
        email = "";
        password = "";
    }


    /**
     * This constructor is used when account already exist and the user is signing back in.
     * @param accountNum The pre-existing account number from database.
     * @param email The pre-existing account email from database.
     * @param password The pre-existing account password from database.
     */
    public Account(int accountNum, String email, String password){
        this.accountNum = accountNum;
        this.email = email;
        this.password = password;
    }


    /**
     * Returns Account's email.
     * @return String of account's email.
     */
    public String getEmail() {
        return email;
    }


    /**
     * Sets the Account's email with the provided String.
     * @param email String of this specific account's email.
     */
    public void setEmail(String email) {
        //Check to see if parameter is null or empty first
        if(email != null && !email.trim().isEmpty()){
            this.email = email;
        }
    }


    /**
     * Returns the Account's password.
     * @return String (non-encrypted) of account's password.
     */
    public String getPassword() {
        return password;
    }


    /**
     * Sets the Account's password with the provided String.
     * @param password String of this specific account's password.
     */
    public void setPassword(String password) {
        //Check to see if parameter is null or empty first
        if(password != null && !password.trim().isEmpty()){
            this.password = password;
        }
    }


    /**
     * Request an account number (ID) from the server. This function must be called immediately after instantiating a new object Account or the default
     * account number will be -1, which means it will not be verified and acknowledged by the server.
     */
    public void applyForAccountNumber(Database db){
        accountNum = db.generateAccountNumber();
    }


    /**
     * Returns the Account's ID.
     * @return int of account number (account ID).
     */
    public int getID(){
        return accountNum;
    }
}
