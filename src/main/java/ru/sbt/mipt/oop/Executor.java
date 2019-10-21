package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.EventHandlers.EventHandler;
import ru.sbt.mipt.oop.EventHandlers.HandlersConstructor;
import ru.sbt.mipt.oop.SmartHome.SmartHome;
import ru.sbt.mipt.oop.SensorEvent.SensorEvent;
import ru.sbt.mipt.oop.SensorEvent.SensorEventGenerator;
import ru.sbt.mipt.oop.SensorEvent.SensorEventGetter;

import java.util.List;

public class Executor {
    public static void run(SmartHome smartHome) {
        List<EventHandler> handlers = HandlersConstructor.constructHandlers(smartHome);
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
