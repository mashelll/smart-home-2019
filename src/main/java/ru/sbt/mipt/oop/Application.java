package ru.sbt.mipt.oop;


import ru.sbt.mipt.oop.ReadingUtils.SmartHomeReader;
import ru.sbt.mipt.oop.ReadingUtils.SmartHomeReaderJSON;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

public class Application {


    public static void main(String... args) {
        SmartHomeReader smartHomeReader = new SmartHomeReaderJSON();
        SmartHome smartHome = smartHomeReader.read();
        Executor.run(smartHome);
    }


}
