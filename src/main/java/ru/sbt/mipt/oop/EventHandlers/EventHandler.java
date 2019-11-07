package ru.sbt.mipt.oop.EventHandlers;

import ru.sbt.mipt.oop.SensorEvent.SensorEvent;

public interface EventHandler {
    public void handleEvent(SensorEvent event);

}
