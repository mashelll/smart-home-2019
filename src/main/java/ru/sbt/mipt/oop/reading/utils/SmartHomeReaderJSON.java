package ru.sbt.mipt.oop.reading.utils;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.smart.home.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class SmartHomeReaderJSON implements SmartHomeReader {
    @Override
    public SmartHome read() {
        Gson gson = new Gson();
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        return smartHome;
    }
}

