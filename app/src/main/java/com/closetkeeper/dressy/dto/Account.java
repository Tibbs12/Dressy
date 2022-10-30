package com.closetkeeper.dressy.dto;

/**
 * Holds important data like user account number (or account ID), email, and password. When the user registers
 * for the first time, the basic class constructor will automatically create an account ID for the user. If the user already has
 * an account, then the secondary constructor needs to be executed allowing this class to receive the existing user's data.
 *
 * <br>
 * <br>Created by Tim on 10/11/22.
 * <br>Last Modified on 10/30/2022.
 */
public class Account {

    private int accountNum;
    private String email;
    private String password;


    public Account(){

        //When a new account is being created for the first time, this constructor will automatically
        //generate the correct value for "accountNum" variable.
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
     * @return String.
     */
    public String getEmail() {
        return email;
    }


    /**
     * Sets the Account's email with the provided String.
     * @param email String of this specific account's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Returns the Account's password.
     * @return String (non-encrypted).
     */
    public String getPassword() {
        return password;
    }


    /**
     * Sets the Account's password with the provided String.
     * @param password String of this specific account's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Generates the user's account ID by retrieving the last account ID value from the database, then increments the value by
     * one and returning it.
     */
    private void generateAccountNum(){
        int lastAccountNum_DB = 0; //ToDo: Get lastAccountNum_DB integer from database
        accountNum = lastAccountNum_DB + 1;
    }


    /**
     * Returns the Account's ID.
     * @return int.
     */
    public int getID(){
        return accountNum;
    }
}
