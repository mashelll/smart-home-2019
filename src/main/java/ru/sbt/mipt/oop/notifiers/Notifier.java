package ru.sbt.mipt.oop.notifiers;

import ru.sbt.mipt.oop.sensor.event.SensorEvent;

public interface Notifier {
    void sendNotification(SensorEvent event);
}
