package ru.sbt.mipt.oop.event_handlers.decorators;

import ru.sbt.mipt.oop.event_handlers.EventHandler;
import ru.sbt.mipt.oop.notifiers.Notifier;
import ru.sbt.mipt.oop.notifiers.SMSNotification;
import ru.sbt.mipt.oop.sensor_event.SensorEvent;
import ru.sbt.mipt.oop.sensor_event.types.SensorEventType;
import ru.sbt.mipt.oop.smart_devices.alarm.Alarm;
import ru.sbt.mipt.oop.smart_devices.alarm.states.Activated;
import ru.sbt.mipt.oop.smart_devices.alarm.states.AlarmState;
import ru.sbt.mipt.oop.smart_devices.alarm.states.Alert;

public class AlarmStateDecorator extends HandlerDecorator {
    private final Alarm alarm;

    public AlarmStateDecorator(EventHandler wrapped, Alarm alarm) {
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
