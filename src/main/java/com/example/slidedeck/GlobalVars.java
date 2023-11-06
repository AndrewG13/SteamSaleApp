package com.example.slidedeck;

import java.util.ArrayList;

public class GlobalVars {

    public static ArrayList<Game> gameData;
    public static String userID;

    public static void printGameData() {
        gameData.forEach(game -> {
            System.out.println(game);
            // using first index, all indexes should have same id
            //SteamAPI.getGameImage(game.storeID);
        });
    }

}
