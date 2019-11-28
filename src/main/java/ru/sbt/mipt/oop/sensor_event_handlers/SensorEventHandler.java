package ru.sbt.mipt.oop.sensor_event_handlers;

import ru.sbt.mipt.oop.sensor_event.SensorEvent;

public interface SensorEventHandler {
    public void handleEvent(SensorEvent event);

}
