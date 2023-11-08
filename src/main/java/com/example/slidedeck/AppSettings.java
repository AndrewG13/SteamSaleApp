package com.example.slidedeck;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AppSettings {

    // todo: Lowercase these variables (these are not constants and expected to change
    String user_id;
    String user_name;
    int refresh_rate;
    private JSONObject settings;


    public AppSettings(String id, String name, int refreshRate) {
        this.settings = readAll();
        writeAll(id, name, refreshRate);
        this.user_id = id;
        this.user_name = name;
        this.refresh_rate = refreshRate;
    }

    public AppSettings() {
        this.settings = readAll();
        this.user_id = read(GlobalConsts.SETTINGS_USER_ID_STRING);
        this.user_name = read(GlobalConsts.SETTINGS_USER_NAME_STRING);
        this.refresh_rate = Integer.parseInt(read(GlobalConsts.SETTINGS_REFRESH_RATE_STRING));
    }

    public String getUser_ID() {
        return this.user_id;
    }

    public void setUser_id(String id) {
        this.user_id = id;
        write(GlobalConsts.SETTINGS_USER_ID_STRING, id);
    }

    public String getUser_NAME() {
        return this.user_name;
    }

    public void setUser_name(String name) {
        this.user_name = name;
        write(GlobalConsts.SETTINGS_USER_NAME_STRING, name);
    }

    public int getRefreshRate() {
        return this.refresh_rate;
    }

    public void setRefresh_Rate(int rate) {
        this.refresh_rate = rate;
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
        return GlobalConsts.SETTINGS_USER_ID_STRING + GlobalConsts.COLON_STRING + this.user_id + GlobalConsts.NEWLINE_STRING +
               GlobalConsts.SETTINGS_USER_NAME_STRING + GlobalConsts.COLON_STRING + this.user_name + GlobalConsts.NEWLINE_STRING +
               GlobalConsts.SETTINGS_REFRESH_RATE_STRING + GlobalConsts.COLON_STRING + this.refresh_rate + GlobalConsts.DOUBLE_NEWLINE_STRING;
    }
}
