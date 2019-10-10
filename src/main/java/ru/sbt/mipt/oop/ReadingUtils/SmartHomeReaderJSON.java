package main.java.ru.sbt.mipt.oop.ReadingUtils;

import com.google.gson.Gson;
import main.java.ru.sbt.mipt.oop.HomeCreator.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class SmartHomeReaderJSON implements SmartHomeReader {
    @Override
    public SmartHome read() throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        return smartHome;
    }
}
