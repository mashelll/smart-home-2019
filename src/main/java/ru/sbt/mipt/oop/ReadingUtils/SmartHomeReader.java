package main.java.ru.sbt.mipt.oop.ReadingUtils;

import main.java.ru.sbt.mipt.oop.HomeCreator.SmartHome;

import java.io.IOException;

public interface SmartHomeReader {
    public SmartHome read() throws IOException;
}
