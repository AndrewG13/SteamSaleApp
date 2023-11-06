package com.example.slidedeck;

import java.util.ArrayList;

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
        String details = "Name: " + name + "\nStoreID: " + storeID + "\nReviews: " + reviews_percent + "%, " + review_desc + " | Release Date: " + release_string + " | Priority: " + priority + "\n";
        return details + editions + " # of Editions: " + editions.size();
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