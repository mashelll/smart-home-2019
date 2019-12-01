package ru.sbt.mipt.oop.event.handlers.decorators;

import ru.sbt.mipt.oop.event.handlers.SensorEventHandler;
import ru.sbt.mipt.oop.notifiers.Notifier;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.types.SensorEventType;
import ru.sbt.mipt.oop.smart.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smart.devices.alarm.Activated;
import ru.sbt.mipt.oop.smart.devices.alarm.AlarmState;
import ru.sbt.mipt.oop.smart.devices.alarm.Alert;

public class AlarmStateDecorator extends HandlerDecorator {
    private final Alarm alarm;
    Notifier notifier;

    public AlarmStateDecorator(SensorEventHandler wrapped, Alarm alarm, Notifier notifier) {
        super(wrapped);
        this.alarm = alarm;
        this.notifier = notifier;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        AlarmState state = alarm.getState();
        if (state instanceof Alert) {
            notifier.sendNotification(event);
            return;
        }
        if (state instanceof Activated) {
            if (event.getType() != SensorEventType.ALARM_EVENT) {
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