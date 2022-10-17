package com.closetkeeper.dressy.dto;

import java.util.*;

/**
 * The Wardrobe is the way a user can view an inventory of all stored Items and Outfits they have
 * associated with their Account.
 *
 * Created by Curran
 */

public class Wardrobe {

    private int account_id;
    private List<String> clothes;   //TODO: Rename type String to Items
    private List<String> outfits;   //TODO: Rename type String to Outfit

    //Returns the user's Account ID
    public int getAccount_id() {
        return account_id;
    }

    //Sets the Account ID as the int that is passed
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    //Returns the list of Items[] from clothes
    public List<String> getClothes() {
        return clothes;
    }

    //Sets the List of clothes into List<Clothes> clothes
    public void setClothes(List<String> clothes) {
        this.clothes = clothes;
    }

    //Returns the list of Outfits[] from outfits
    public List<String> getOutfits() {
        return outfits;
    }

    //Sets the List of outfits into List<Outfits> outfits
    public void setOutfits(List<String> outfits) {
        this.outfits = outfits;
    }
}
