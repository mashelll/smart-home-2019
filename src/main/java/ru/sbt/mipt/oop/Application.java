package ru.sbt.mipt.oop;


import ru.sbt.mipt.oop.reading_utils.SmartHomeReader;
import ru.sbt.mipt.oop.reading_utils.SmartHomeReaderJSON;
import ru.sbt.mipt.oop.smart_home.SmartHome;

public class Application {


    public static void main(String... args) {
        SmartHomeReader smartHomeReader = new SmartHomeReaderJSON();
        SmartHome smartHome = smartHomeReader.read();
        Executor.run(smartHome);
    }


}
