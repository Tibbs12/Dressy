package com.closetkeeper.dressy.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock database representing a server. Used only for partial testing of classes depended on accessing data from a live database. This
 * class implements the IAccountData interface and also provides appropriate functions that would be expected of a class that connects
 * to a real server holding data.
 *
 * <br>
 * <br>Created by Tim on 10/18/22.
 * <br>Last Modified on 11/1/2022.
 */
public class DatabaseMock implements IAccountData{

    //Account Data
    private String currentAccountNumber = "06416";
    private String accountEmail;
    private String accountPassword;

    //Outfit Data
    private String outfitName = "Work Casual";
    private List<String> outfitTags = new ArrayList<>();
    private List<String> outfitItems = new ArrayList<>();


    public DatabaseMock(){
        outfitTags.add("Work");
        outfitTags.add("Casual");
        outfitItems.add("White Collared Shirt");
        outfitItems.add("Blue Dress Pants");
        outfitItems.add("Black Dress Shoes");
        outfitItems.add("Black Tie");
    }


    /**
     * Given an account number, return the associated password.
     * @param accountNum An integer that represents a user's account number.
     * @return String of the user's password.
     */
    public String fetchPassword(int accountNum) {
        return accountPassword;
    }


    /**
     * Given an account number, return the associated email.
     * @param accountNum An integer that represents a user's account number.
     * @return String of the user's email.
     */
    public String fetchEmail(int accountNum) {
        return accountEmail;
    }


    /**
     * Returns the most recent assigned account ID.
     * @return String of lasted assigned account ID.
     */
    public String fetchLatestID(){
        return  currentAccountNumber;
    }


    /**
     * Returns the Outfit's name.
     * @param accountNum An integer that represents a user's account number.
     * @return String of outfit name.
     */
    public String fetchOutfitName(int accountNum){
        return outfitName;
    }


    /**
     * Returns the tags associated with the Outfit
     * @param accountNum An integer that represents a user's account number.
     * @return List<String> of tags.
     */
    public List<String> fetchOutfitTags(int accountNum){
        return outfitTags;
    }


    /**
     * Returns the Items associated with the Outfit.
     * @param accountNum An integer that represents a user's account number.
     * @return List<String> of items.
     */
    public List<String> fetchOutfitItems(int accountNum){
        return outfitItems;
    }


    /**
     * Updates the mock database's "accountPassword" variable with the provided parameter.
     * @param password String that is the account's password.
     */
    public void updatePassword(String password){
        accountPassword = password;
    }


    /**
     * Updates the mock database's "accountEmail" variable with the provided parameter.
     * @param email String that is the account's email
     */
    public void updateEmail(String email){
        accountEmail = email;
    }


    /**
     * Updates the mock database's "currentAccountNum" variable with the provided parameter.
     * @param latestID A String that represents the the last account number created.
     */
    public void updateLatestID(String latestID){
        currentAccountNumber = latestID;
    }


    /**
     * Updates the mock database's "outfitName" variable with the provided parameter.
     * @param name A String that represents the outfit's name.
     */
    public void updateOutfitName(String name){
        outfitName = name;
    }


    /**
     * Updates the mock database's "outfitTags" variable with the provided parameter.
     * @param tags A List<String> that represents the outfit's tags.
     */
    public void updateOutfitTags(List<String> tags){
        outfitTags = tags;
    }


    /**
     * Updates the mock database's "outfitItems" variable with the provided parameter.
     * @param items A List<String> that represents the outfit's items.
     */
    public void updateOutfitItems(List<String> items){
        outfitItems = items;
    }
}
