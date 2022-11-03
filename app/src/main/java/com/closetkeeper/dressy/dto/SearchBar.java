package com.closetkeeper.dressy.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows users to quickly find Items and Outfits associated with their account by name or tag. Searches through either the
 * local or live database using the appropriate function(s).
 *
 * <br>
 * <br>Created by Tim on 10/18/2022.
 * <br>Last Modified on 10/29/2022.
 */

public class SearchBar {

    /**
     * Looks through live database to find specific Item, if exists.
     * @param name The Item name entered by the user.
     * @param accountNum The users account number to allow for correct fetching of data.
     */
    public void findItem(String name, int accountNum){

        //ToDo: Look for Item name on server that is associated to the specific account number
        //ToDo: Make return type "Item" when class is implemented
    }

    /**
     * Looks through live database to find specific Outfit, if exists.
     * @param name The Outfit name entered by the user.
     * @param accountNum The users account number to allow for correct fetching of data.
     */
    public void findOutfit(String name, int accountNum){

        //ToDo: Look for Outfit name on server that is associated to the specific account number
        //ToDo: Make return type "Outfit" when class is implemented
    }

    /**
     * Looks through live database to find specific tag(s), if exists.
     * @param name The tag name entered by the user.
     * @param accountNum The users account number to allow for correct fetching of data.
     * @return List(String) of tag names.
     */
    public List<String> findTag(String name, int accountNum){

        List<String> ls_tags = new ArrayList<String>();

        //ToDo: Look for tag(s) name on server that is associated to the specific account number

        return ls_tags;
    }
}