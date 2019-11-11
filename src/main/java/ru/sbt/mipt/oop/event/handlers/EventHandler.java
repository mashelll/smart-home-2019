package ru.sbt.mipt.oop.event.handlers;

import ru.sbt.mipt.oop.sensor.event.SensorEvent;

public interface EventHandler {
    public void handleEvent(SensorEvent event);

}
