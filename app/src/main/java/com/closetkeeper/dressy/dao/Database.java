package com.closetkeeper.dressy.dao;

import android.database.Cursor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Holds functions that allows the user to push/pull appropriate data to and from the server. Implements the IAccountData interface and
 * uses the default function 'connectToServer' when this class is instantiated - Meaning that there is no need to connect to the database
 * manually. Simply instantiate this class as an Object then call the functions needed.
 *
 * <br>
 * <br>Created by Tim on 11/04/22.
 * <br>Last Modified on 11/13/22.
 */
public class Database implements IAccountData
{
    private final Connection databaseConnection;
    private String sqlStatement = "";
    private PreparedStatement readiedStatement;
    private ResultSet currentResultSet;
    private final String userPasswordColumnName = "userpass";
    private final String userIDColumnName = "userID";
    private final String userEmailColumnName = "email";


    public Database(){
        databaseConnection = this.connectToServer();
    }


    /**
     * Given an account number, returns the associated password to that user. Returns null and logs error message
     * if the SQL query fails.
     * @param accountNum An integer that represents the user's account number.
     * @return String of the user's password.
     */
    public String fetchPassword(int accountNum) {
        //Try to execute the SQL query
        try{
            sqlStatement = "SELECT " + userPasswordColumnName + " FROM TESTING WHERE " + userIDColumnName + " = '" + accountNum + "'";      //ToDo: Change table name 'TESTING' to appropriate name
            readiedStatement = databaseConnection.prepareStatement(sqlStatement);
            currentResultSet = readiedStatement.executeQuery();

            if(currentResultSet.next()){
                return currentResultSet.getString(userPasswordColumnName);
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            System.out.println("\nERROR: Fetch password SQL query failed - " + e.getMessage());
            return null;
        }
    }


    /**
     * Returns the user's associated email from the server using their account number. Returns null and logs
     * error message if the sql query fails.
     * @param accountNum An integer that represents the user's account number.
     * @return String of the user's email.
     */
    public String fetchEmail(int accountNum){
        //Try to execute the SQL query
        try{
            sqlStatement = "SELECT " + userEmailColumnName + " FROM TESTING WHERE " + userIDColumnName + " = '" + accountNum + "'";      //ToDo: Change table name 'TESTING' to appropriate name
            readiedStatement = databaseConnection.prepareStatement(sqlStatement);
            currentResultSet = readiedStatement.executeQuery();

            if(currentResultSet.next()){
                return currentResultSet.getString(userEmailColumnName);
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            System.out.println("\nERROR: Fetch email SQL query failed - " + e.getMessage());
            return null;
        }
    }


    /**
     * Generates a new account number based on the latest ID on the server. If the server fails to update the newly 
     * created account number, then a null value of '00000' will be returned.
     * @return int of the user's newly generated account number.
     */
    public int generateAccountNumber(){
       //Try to get the latest account ID that the server is holding
        String latestID = "";
        int newAccountNum;
        String generatedAccountNum;
        final int NULL_ACCOUNT_NUMBER = 00000;

        try{
            sqlStatement = "SELECT " + userIDColumnName + " FROM TESTING ORDER BY " + userIDColumnName + " DESC LIMIT 1";       //ToDo: Change table name 'TESTING' to appropriate name
            readiedStatement = databaseConnection.prepareStatement((sqlStatement));
            currentResultSet = readiedStatement.executeQuery();

            if(currentResultSet.next()){
                latestID = currentResultSet.getString(userIDColumnName);
            }
            else{
                System.out.println("\nWARNING - No data found");
            }
        }
        catch (Exception e){
            System.out.println("\nERROR: Fetch latest account number SQL query failed - " + e.getMessage());
        }

        //Increment the account number by 1
        newAccountNum = Integer.parseInt(latestID);
        newAccountNum += 1;
        //generatedAccountNum = String.format("%05d", newAccountNum);           //ToDo: Change to this format when table for user's data has been created (NOT THE TESTING TABLE)
        generatedAccountNum = Integer.toString(newAccountNum);

        //Update the server with the newly created account number and return it
        try{
            sqlStatement = "INSERT into TESTING (email, userID, userpass)" + " VALUES (?, ?, ?)";             //ToDo: Change table name 'TESTING' to appropriate name

            readiedStatement = databaseConnection.prepareStatement(sqlStatement);
            readiedStatement.setString(1, "");
            readiedStatement.setString(2, generatedAccountNum);
            readiedStatement.setString(3, "");
            readiedStatement.execute();

            return newAccountNum;
        }
        catch (Exception e){
            System.out.println("\nERROR: Could not update server with latest account number. Returning value of '00000' - " + e.getMessage());
            return NULL_ACCOUNT_NUMBER;
        }
    }


    /**
     * Updates an existing user's email and password with the provided Strings. The account corresponding to the given account number will be updated.
     * @param accountNum int of the user's account number that you wish to be edited.
     * @param accountEmail String of user email that you wish to update.
     * @param accountPassword String of user password that you wish to update.
     */
    public void updateAccount(int accountNum, String accountEmail, String accountPassword){
        try{
            String sql = "UPDATE TESTING SET email = '" + accountEmail + "' WHERE userID = '" + accountNum + "'";
            PreparedStatement statement = databaseConnection.prepareStatement(sql);
            statement.execute();

            String sql2 = "UPDATE TESTING SET userpass = '" + accountPassword + "' WHERE userID = '" + accountNum + "'";
            PreparedStatement statement2 = databaseConnection.prepareStatement(sql2);
            statement2.execute();
        }
        catch (Exception e){
            System.out.println("ERROR: Account was not updated. " + e.getMessage());
        }
    }


    /**
     * Updates an existing user's email with the provided String. The account corresponding to the given account number will be updated.
     * @param accountNum int of the user's account number that you wish to be edited.
     * @param accountEmail String of user email that you wish to update.
     */
    public void updateEmail(int accountNum, String accountEmail){
        try{
            String sql = "UPDATE TESTING SET email = '" + accountEmail + "' WHERE userID = '" + accountNum + "'";
            PreparedStatement statement = databaseConnection.prepareStatement(sql);
            statement.execute();
        }
        catch (Exception e){
            System.out.println("ERROR: Account's email was not updated. " + e.getMessage());
        }
    }


    /**
     * Updates an existing user's password with the provided String. The account corresponding to the given account number will be updated.
     * @param accountNum int of the user's account number that you wish to be edited.
     * @param accountPassword String of user email that you wish to update.
     */
    public void updatePassword(int accountNum, String accountPassword){
        try{
            String sql = "UPDATE TESTING SET userpass = '" + accountPassword + "' WHERE userID = '" + accountNum + "'";
            PreparedStatement statement = databaseConnection.prepareStatement(sql);
            statement.execute();
        }
        catch (Exception e){
            System.out.println("ERROR: Account's password was not updated. " + e.getMessage());
        }
    }
}
