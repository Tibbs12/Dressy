package com.closetkeeper.dressy.dto;

import java.util.*;

/**
 * The Wardrobe is the way a user can view an inventory of all stored Items and Outfits they have
 * associated with their Account.
 */

public class Wardrobe {

    private int account_id;
    private List<String> clothes;   //TODO: Rename type String to Items
    private List<String> outfits;   //TODO: Rename type String to Outfit


    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public List<String> getClothes() {
        return clothes;
    }

    public void setClothes(List<String> clothes) {
        this.clothes = clothes;
    }

    public List<String> getOutfits() {
        return outfits;
    }

    public void setOutfits(List<String> outfits) {
        this.outfits = outfits;
    }
}
