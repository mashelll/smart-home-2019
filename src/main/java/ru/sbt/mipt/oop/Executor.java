package ru.sbt.mipt.oop;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sbt.mipt.oop.event_handlers.EventHandler;
import ru.sbt.mipt.oop.event_handlers.HandlersConstructor;
import ru.sbt.mipt.oop.reading_utils.SmartHomeReader;
import ru.sbt.mipt.oop.reading_utils.SmartHomeReaderJSON;
import ru.sbt.mipt.oop.smart_home.SmartHome;
import ru.sbt.mipt.oop.sensor_event.SensorEvent;
import ru.sbt.mipt.oop.sensor_event.SensorEventGenerator;
import ru.sbt.mipt.oop.sensor_event.SensorEventGetter;

import java.util.List;

public class Executor {
    @Autowired
    private static SmartHome smartHome;
    private static List<EventHandler> handlers = HandlersConstructor.constructHandlers(smartHome);

    public void run() {

        SensorEventGetter sensorEventGetter = new SensorEventGenerator();
        SensorEvent event = sensorEventGetter.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventHandler handler : handlers) {
                handler.handleEvent(event);
            }
        }
    }
}
