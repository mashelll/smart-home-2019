package ru.sbt.mipt.oop.event.handlers.decorators;

import ru.sbt.mipt.oop.event.handlers.SensorEventHandler;
import ru.sbt.mipt.oop.notifiers.Notifier;
import ru.sbt.mipt.oop.notifiers.SMSNotification;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.types.SensorEventType;
import ru.sbt.mipt.oop.smart.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smart.devices.alarm.Activated;
import ru.sbt.mipt.oop.smart.devices.alarm.AlarmState;
import ru.sbt.mipt.oop.smart.devices.alarm.Alert;

public class AlarmStateDecorator extends HandlerDecorator {
    private final Alarm alarm;

    public AlarmStateDecorator(SensorEventHandler wrapped, Alarm alarm) {
        super(wrapped);
        this.alarm = alarm;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        AlarmState state = alarm.getState();
        Notifier notifier = new SMSNotification();
        if (state instanceof Alert) {
            notifier.sendNotification(event);
            return;
        }
        if (state instanceof Activated) {
            if (event.getType() == SensorEventType.ALARM_EVENT) {
                super.handleEvent(event);
                return;
            }
            alarm.triggerAlert();
            notifier.sendNotification(event);
            return;
        }
        super.handleEvent(event);
    }
}
