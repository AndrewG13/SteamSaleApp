package com.example.slidedeck;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ListData {

    ArrayList<Game> list;
    //private JSONObject settings;
    //private JSONArray

    public ListData() {

    }


    private JSONObject readAll() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(GlobalConsts.LISTDATA_PATH));
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

        try (FileWriter file = new FileWriter(GlobalConsts.LISTDATA_PATH)) {
            // We can write any JSONArray or JSONObject instance to the file
            file.write(this.settings.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}