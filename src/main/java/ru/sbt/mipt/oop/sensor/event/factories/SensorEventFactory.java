package ru.sbt.mipt.oop.sensor.event.factories;

import ru.sbt.mipt.oop.sensor.event.SensorEvent;

public interface SensorEventFactory {
    public SensorEvent generateSensorEvent(String objectId);
}
