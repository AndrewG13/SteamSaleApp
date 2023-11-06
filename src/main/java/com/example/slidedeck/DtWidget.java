package com.example.slidedeck;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class DtWidget extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DtWidget.class.getResource("hello-view-test.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // set transparency to stage
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);

        stage.setTitle("Steam Sale App");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}