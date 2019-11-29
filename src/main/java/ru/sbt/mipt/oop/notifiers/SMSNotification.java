package ru.sbt.mipt.oop.notifiers;

import ru.sbt.mipt.oop.sensor.event.SensorEvent;

public class SMSNotification implements Notifier {
    @Override
    public void sendNotification(SensorEvent event) {
        System.out.println(event.getType() + "happened!");
    }

}
