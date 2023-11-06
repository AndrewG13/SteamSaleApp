package com.example.slidedeck;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Objects;

public class Controller {

    @FXML
    private Label userTime;

    @FXML
    private volatile boolean stopTime = false;

    @FXML
    private Button wishlistButton;

    @FXML
    private TextField userIDInput;

    @FXML
    private ImageView imageDisplay = new ImageView();

    public static boolean debugMode = true;
    private AppSettings appSettings;

    /*
    ** Contains all start up processes for the application
    */
    @FXML
    public void initialize() {
        // retrieve stored settings configuration
        appSettings = new AppSettings();

        // set up clock
        userTime.setText("00:00:00");
        systemTime();

        if (debugMode) {
            debug();
        }
    }

    /*
    * Data Fetch
    *
    * Refresh all local content with updated fetched data
     */
    @FXML
    public void fetch() {

    }

    /*
    ** Clock Functionality
    */
    @FXML
    public void systemTime() {
        Thread timeThread = new Thread(() -> {
            // create time format to display
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm:ss");
            while (!stopTime) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // time exception has occurred
                    System.out.println(e);
                }
                Platform.runLater(() -> {
                    userTime.setText(timeFormat.format(LocalTime.now()));
                });
            }
        });
        timeThread.start();
    }

    @FXML
    /*
    * Fetches wishlist from Steam API and stores it globally
     */
    public void getWishlist() {
        String ID = userIDInput.getText();
        SteamAPI.getWishlist(ID);
    }

    @FXML
    public void setImageDisplay(String url) {
        imageDisplay.setImage(new Image (url));
    }

    /*
    * Prints the stored wishlist (from last fetch)
     */
    @FXML
    public void printWishlist() {
        GlobalVars.printGameData();
    }

    @FXML
    public void saveUserID() {
        String id = userIDInput.getText();
        appSettings.setUSER_ID(id);
    }

    @FXML
    private void debug() {
        System.out.println("Controller running in Debug mode");

        setImageDisplay("https://cdn.akamai.steamstatic.com/steam/apps/1745510/header_292x136.jpg");
    }

    @FXML
    private void debugPrint() {
        System.out.println(appSettings.toString());
    }
}