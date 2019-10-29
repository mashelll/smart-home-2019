package ru.sbt.mipt.oop.notifiers;

import ru.sbt.mipt.oop.sensor_event.SensorEvent;

public interface Notifier {
    void sendNotification(SensorEvent event);
}
