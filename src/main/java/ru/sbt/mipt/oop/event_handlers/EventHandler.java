package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.sensor_event.SensorEvent;

public interface EventHandler {
    public void handleEvent(SensorEvent event);

}
