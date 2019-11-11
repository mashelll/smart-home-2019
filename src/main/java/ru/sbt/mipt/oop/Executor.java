package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.event.handlers.EventHandler;
import ru.sbt.mipt.oop.event.handlers.HandlersConstructor;
import ru.sbt.mipt.oop.smart.home.SmartHome;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.SensorEventGenerator;
import ru.sbt.mipt.oop.sensor.event.SensorEventGetter;

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
