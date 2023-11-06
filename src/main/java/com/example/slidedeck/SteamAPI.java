package com.example.slidedeck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SteamAPI {


    /*
    ** Get Wishlist Method
    *
    *  Sends a GET request to retrieve user wishlist information, then parses the response into Game objects
    */
    public static void getWishlist(String userID) {
        // create the URL and append user data
        URL url = constructWishlistURL(userID, 0);
        if (url == null) {
            // handle null url
            System.out.print("URL is null");
            return;
        }

        try {
            // send the GET request
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int status = con.getResponseCode();
            System.out.println("Status Code: " + status);

            // receive and store response
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // parse JSON response
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(response.toString());

            // create individual JSON objects from response
            Set<String> keyset = json.keySet();
            Iterator<String> keys = keyset.iterator();
            ArrayList<Game> games = new ArrayList<>();

            while (keys.hasNext()){
                String key = keys.next();
                Object value = json.get(key);
                //System.out.println( key +" : " + value); // print each wishlist game's JSON data
                JSONObject gameJson = (JSONObject) parser.parse(value.toString());
                JSONArray editionsJson = (JSONArray) parser.parse(gameJson.get("subs").toString());

                // test console prints
                //System.out.println(gameJson.get("name")); // print the value for the key "name" of the JSON obj
                //System.out.println(game.get("subs")); // print the value for the key "subs" of the JSON obj

                Game newGame = createGame(key, gameJson, editionsJson);
                games.add(newGame);
            }

            // store fetched wishlist to global data
            GlobalVars.gameData = games;

        } catch (IOException e) {
            System.out.println("IO Exception! Your Wishlist data might not be public.");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private static Game createGame(String gameStoreID, JSONObject gameJson, JSONArray editionsJson) {
        String name = gameJson.get("name").toString();
        String capsule = gameJson.get("capsule").toString();
        String review_desc = gameJson.get("review_desc").toString();
        String reviews_percent = gameJson.get("reviews_percent").toString();
        String release_string = gameJson.get("release_string").toString();
        String priority = gameJson.get("priority").toString();

        if (Controller.debugMode) {
            System.out.println("createGame debug: " + name);
        }

        // Handle subs/editions TODO
        ArrayList<Edition> editions = new ArrayList<>();
        Iterator<JSONObject> it = editionsJson.iterator();
        while (it.hasNext()) {
            JSONObject sub = it.next();

            String id = sub.get("packageid").toString();
            double price = Double.parseDouble(sub.get("price").toString()) / 100;
            int discount_pct = sub.get("discount_pct") != null ? Integer.parseInt(sub.get("discount_pct").toString()) : 0;

            Edition edition = new Edition(id, price, discount_pct);
            editions.add(edition);
        }

        Game game = new Game(gameStoreID, name, capsule, review_desc, reviews_percent, release_string, priority, editions);
        return game;
    }

    private static URL constructWishlistURL(String userID, int page) {
        String url = "https://store.steampowered.com/wishlist/profiles/" + userID + "/wishlistdata/?p=" + page;
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL Exception! User ID may be incorrect. \nURL: " + url);
            return null;
        }
    }

    private static URL constructImageURL(String gameStoreID) {
        String url = "https://cdn.akamai.steamstatic.com/steam/apps/" + gameStoreID + "/header_292x136.jpg";
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL Exception! Game ID may be incorrect");
            return null;
        }
    }
}
