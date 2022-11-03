package com.closetkeeper.dressy.dao;


import java.util.ArrayList;
import java.util.List;

/**
 * Mock database representing a local cache. Used only for partial testing of classes depended on accessing data from a the local device's cache. This
 * class implements the IAccountData interface and also provides appropriate functions that would be expected of a class that connects
 * to a the device's cache that holds data.
 *
 * <br>
 * <br>Created by Tim on 10/18/22.
 * <br>Last Modified on 11/1/2022.
 */
public class CacheMock {

    //Account Data
    private String accountEmail;
    private String accountPassword;

    //Outfit Data
    private String outfitName = "Gym Clothes";
    private List<String> outfitTags = new ArrayList<>();
    private List<String> outfitItems = new ArrayList<>();


    public CacheMock(){
        outfitTags.add("Athletic");
        outfitTags.add("Gym");
        outfitItems.add("Grey Athletic Shirt");
        outfitItems.add("Black Athletic Shorts");
        outfitItems.add("Black Running Shoes");
    }


    /**
     * Return the associated password.
     * @return String of the user's password.
     */
    public String fetchPassword() {
        return accountPassword;
    }


    /**
     * Return the associated email.
     * @return String of the user's email.
     */
    public String fetchEmail() {
        return accountEmail;
    }

    /**
     * Returns the Outfit's name.
     * @return String of outfit name.
     */
    public String fetchOutfitName(){
        return outfitName;
    }


    /**
     * Returns the tags associated with the Outfit
     * @return List<String> of tags.
     */
    public List<String> fetchOutfitTags(){
        return outfitTags;
    }


    /**
     * Returns the Items associated with the Outfit.
     * @return List<String> of items.
     */
    public List<String> fetchOutfitItems(){
        return outfitItems;
    }


    /**
     * Updates the "accountPassword" variable in mock cache with the provided parameter.
     * @param password String that is the account's password.
     */
    public void updatePassword(String password){
        accountPassword = password;
    }


    /**
     * Updates the "accountEmail" variable in mock cache with the provided parameter.
     * @param email String that is the account's email
     */
    public void updateEmail(String email){
        accountEmail = email;
    }


    /**
     * Updates the "outfitName" variable in mock cache with the provided parameter.
     * @param name A String that represents the outfit's name.
     */
    public void updateOutfitName(String name){
        outfitName = name;
    }


    /**
     * Updates the "outfitTags" variable in mock cache with the provided parameter.
     * @param tags A List<String> that represents the outfit's tags.
     */
    public void updateOutfitTags(List<String> tags){
        outfitTags = tags;
    }


    /**
     * Updates the "outfitItems" variable in mock cache with the provided parameter.
     * @param items A List<String> that represents the outfit's items.
     */
    public void updateOutfitItems(List<String> items){
        outfitItems = items;
    }
}
