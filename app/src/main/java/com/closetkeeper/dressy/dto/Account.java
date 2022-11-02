package com.closetkeeper.dressy.dto;

import com.closetkeeper.dressy.dao.DatabaseMock;

/**
 * Holds important data like user account number (or account ID), email, and password. When the user registers
 * for the first time, the basic class constructor will automatically create an account ID for the user. If the user already has
 * an account, then the secondary constructor needs to be executed allowing this class to receive the existing user's data.
 *
 * <br>
 * <br>Created by Tim on 10/11/22.
 * <br>Last Modified on 11/1/2022.
 */
public class Account {

    private int accountNum;
    private String email;
    private String password;

    public Account(){

        //Generates the correct account number value when creating a new account
        this.generateAccountNum();
    }


    /**
     * This constructor is used when account already exist and the user is signing back in.
     * @param accountNum The pre-existing account number from live database.
     * @param email The pre-existing account email from live database.
     * @param password The pre-existing account password from live database.
     */
    public Account(int accountNum, String email, String password){

        this.accountNum = accountNum;
        this.email = email;
        this.password = password;
    }


    /**
     * Returns Account's email.
     * @return String the account's email.
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
     * @return String (non-encrypted) the account's password.
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
     * Generates the user's account ID by retrieving the last account ID value from the database, then increments the value by
     * one and returning it.
     */
    private void generateAccountNum(){
        DatabaseMock myMockData = new DatabaseMock();
        int lastAccountNum_DB = Integer.parseInt(myMockData.fetchLatestID()); //ToDo: Get lastAccountNum_DB value from live database

        accountNum = lastAccountNum_DB + 1;

        //Convert latest account ID to String and push back to database
        String currentID = String.format("%05d", accountNum);
        myMockData.updateLatestID(currentID);
    }


    /**
     * Returns the Account's ID.
     * @return int the account number (account ID).
     */
    public int getID(){
        return accountNum;
    }
}
