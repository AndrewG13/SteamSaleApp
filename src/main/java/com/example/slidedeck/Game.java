package com.example.slidedeck;

import java.util.ArrayList;
import org.json.simple.JSONObject;

public class Game {

    String storeID;
    String name;
    String capsule;
    String review_desc;
    String reviews_percent;
    String release_string;
    String priority;
    ArrayList<Edition> editions;

    public Game(String storeID, String name, String capsule, String review_desc, String reviews_percent, String release_string, String priority, ArrayList<Edition> editions) {
        this.storeID = storeID;
        this.name = name;
        this.capsule = capsule;
        this.review_desc = review_desc;
        this.reviews_percent = reviews_percent;
        this.release_string = release_string;
        this.priority = priority;
        this.editions = editions;
    }

    @Override
    public String toString() {
        String details = GlobalConsts.LISTDATA_STORE_ID_STRING + GlobalConsts.COLON_STRING + storeID + GlobalConsts.NEWLINE_STRING +
                         GlobalConsts.LISTDATA_NAME_STRING + GlobalConsts.COLON_STRING + name + GlobalConsts.NEWLINE_STRING +
                         GlobalConsts.LISTDATA_REVIEW_DESC_STRING + GlobalConsts.COLON_STRING + review_desc + GlobalConsts.NEWLINE_STRING +
                         GlobalConsts.LISTDATA_CAPSULE_STRING + GlobalConsts.COLON_STRING + capsule + GlobalConsts.NEWLINE_STRING +
                         GlobalConsts.LISTDATA_PRIORITY_STRING + GlobalConsts.COLON_STRING + priority + GlobalConsts.NEWLINE_STRING +
                         GlobalConsts.LISTDATA_RELEASE_STRING_STRING + GlobalConsts.COLON_STRING + release_string + GlobalConsts.NEWLINE_STRING +
                         GlobalConsts.LISTDATA_REVIEWS_PERCENT_STRING + GlobalConsts.COLON_STRING + reviews_percent + GlobalConsts.NEWLINE_STRING +
                         GlobalConsts.LISTDATA_EDITIONS_STRING + GlobalConsts.COLON_STRING + editions + GlobalConsts.DOUBLE_NEWLINE_STRING;

        return details;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put(GlobalConsts.LISTDATA_STORE_ID_STRING, this.storeID);
        json.put(GlobalConsts.LISTDATA_NAME_STRING, this.name);
        json.put(GlobalConsts.LISTDATA_REVIEW_DESC_STRING, this.review_desc);
        json.put(GlobalConsts.LISTDATA_CAPSULE_STRING, this.capsule);
        json.put(GlobalConsts.LISTDATA_PRIORITY_STRING, this.priority);
        json.put(GlobalConsts.LISTDATA_RELEASE_STRING_STRING, this.release_string);
        json.put(GlobalConsts.LISTDATA_REVIEWS_PERCENT_STRING, this.reviews_percent);
        json.put(GlobalConsts.LISTDATA_EDITIONS_STRING, this.editions);

        return json;
    }

}

class Edition {

    String id;
    double price;
    int discount_pct;

    public Edition (String id, double price, int discount_pct) {
        this.id = id;
        this.price = price;
        this.discount_pct = discount_pct;
    }

    public String getPriceFormatted() {
        String formattedPrice = "$" + String.format("%,.2f", this.price);
        return formattedPrice;
    }

    public String getDiscountFormatted() {
        String formattedDiscount = this.discount_pct + "%";
        return formattedDiscount;
    }

    @Override
    public String toString() {
        return "Edition - ID: " + id + ", " + getPriceFormatted() + ", " + getDiscountFormatted() + "\n";
    }

}