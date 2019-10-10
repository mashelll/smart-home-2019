package main.java.ru.sbt.mipt.oop;


import java.io.IOException;

import main.java.ru.sbt.mipt.oop.HomeCreator.SmartHome;
import main.java.ru.sbt.mipt.oop.ReadingUtils.SmartHomeReader;
import main.java.ru.sbt.mipt.oop.ReadingUtils.SmartHomeReaderJSON;
import main.java.ru.sbt.mipt.oop.SensorEvent.SensorEvent;
import main.java.ru.sbt.mipt.oop.SensorEvent.SensorEventGenerator;


public class Application {


    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHomeReader smartHomeReader = new SmartHomeReaderJSON();
        SmartHome smartHome = smartHomeReader.read();

        // начинаем цикл обработки событий
        SensorEvent event = SensorEventGenerator.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            event.performEvent(smartHome);
            event = SensorEventGenerator.getNextSensorEvent();
        }
    }

}
