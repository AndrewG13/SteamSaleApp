package com.example.slidedeck;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AppSettings {

    String USER_ID;

    public AppSettings() {
        //readAll();

    }

    private JSONObject read() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/main/resources/com/example/slidedeck/settings.json"));
            JSONObject jsonSettings = (JSONObject) obj;
            return jsonSettings;
        } catch (IOException e) {
            System.out.println("IOException reading Json in AppSettings");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("ParseException reading Json in AppSettings");
        }
        return new JSONObject();
    }

    private String read(String field) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/main/resources/com/example/slidedeck/settings.json"));
            JSONObject jsonSettings = (JSONObject) obj;

            String value = jsonSettings.get(field).toString();

            return value;

        } catch (IOException e) {
            System.out.println("IOException reading Json in AppSettings");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("ParseException reading Json in AppSettings");
        }

        return null;
    }

    private void write(String field, String value) {
        JSONObject jsonSettings = read();
        jsonSettings.put(field, value);

        try (FileWriter file = new FileWriter("src/main/resources/com/example/slidedeck/settings.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(jsonSettings.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUser_ID() {
        return this.USER_ID;
    }

    public void setUSER_ID(String id) {
        this.USER_ID = id;
    }

    @Override
    public String toString() {
        return "USER_ID: " + this.USER_ID;
    }
}
