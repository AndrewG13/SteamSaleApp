package com.example.slidedeck;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AppSettings {

    // todo: Lowercase these variables (these are not constants and expected to change
    String USER_ID;
    String USER_NAME;
    int REFRESH_RATE;
    private JSONObject settings;


    public AppSettings(String id, String name, int refreshRate) {
        this.settings = readAll();
        writeAll(id, name, refreshRate);
        this.USER_ID = id;
        this.USER_NAME = name;
        this.REFRESH_RATE = refreshRate;
    }

    public AppSettings() {
        this.settings = readAll();
        this.USER_ID = read(GlobalConsts.SETTINGS_USER_ID_STRING);
        this.USER_NAME = read(GlobalConsts.SETTINGS_USER_NAME_STRING);
        this.REFRESH_RATE = Integer.parseInt(read(GlobalConsts.SETTINGS_REFRESH_RATE_STRING));
    }

    public String getUser_ID() {
        return this.USER_ID;
    }

    public void setUSER_ID(String id) {
        this.USER_ID = id;
        write(GlobalConsts.SETTINGS_USER_ID_STRING, id);
    }

    public String getUser_NAME() {
        return this.USER_NAME;
    }

    public void setUSER_NAME(String name) {
        this.USER_NAME = name;
        write(GlobalConsts.SETTINGS_USER_NAME_STRING, name);
    }

    public int getRefreshRate() {
        return this.REFRESH_RATE;
    }

    public void setRefresh_Rate(int rate) {
        this.REFRESH_RATE = rate;
        write(GlobalConsts.SETTINGS_REFRESH_RATE_STRING, String.valueOf(rate));
    }

    private JSONObject readAll() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(GlobalConsts.SETTINGS_PATH));
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
        //JSONObject jsonSettings = readAll();
        String value = this.settings.get(field).toString();
        return value;
    }

    private void writeAll(String id, String name, int refreshRate) {
        write(GlobalConsts.SETTINGS_USER_ID_STRING, id);
        write(GlobalConsts.SETTINGS_USER_NAME_STRING, name);
        write(GlobalConsts.SETTINGS_REFRESH_RATE_STRING, String.valueOf(refreshRate));
    }

    private void write(String field, String value) {
        //JSONObject jsonSettings = readAll();
        this.settings.put(field, value);

        try (FileWriter file = new FileWriter(GlobalConsts.SETTINGS_PATH)) {
            // We can write any JSONArray or JSONObject instance to the file
            file.write(this.settings.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return GlobalConsts.SETTINGS_USER_ID_STRING + GlobalConsts.COLON_STRING + this.USER_ID + GlobalConsts.NEWLINE_STRING +
               GlobalConsts.SETTINGS_USER_NAME_STRING + GlobalConsts.COLON_STRING + this.USER_NAME + GlobalConsts.NEWLINE_STRING +
               GlobalConsts.SETTINGS_REFRESH_RATE_STRING + GlobalConsts.COLON_STRING + this.REFRESH_RATE + GlobalConsts.DOUBLE_NEWLINE_STRING;
    }
}
