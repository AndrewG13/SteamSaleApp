module com.example.slidedeck {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires json.simple;

    opens com.example.slidedeck to javafx.fxml;
    exports com.example.slidedeck;
}