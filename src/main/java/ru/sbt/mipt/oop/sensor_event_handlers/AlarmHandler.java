package ru.sbt.mipt.oop.sensor_event_handlers;

import ru.sbt.mipt.oop.sensor_event.SensorEvent;
import ru.sbt.mipt.oop.sensor_event.types.AlarmActionType;
import ru.sbt.mipt.oop.sensor_event.types.SensorEventType;
import ru.sbt.mipt.oop.smart_devices.alarm.Alarm;
import ru.sbt.mipt.oop.smart_devices.alarm.CodeGetter;
import ru.sbt.mipt.oop.smart_home.Actionable;
import ru.sbt.mipt.oop.smart_home.SmartHome;

public class AlarmHandler implements SensorEventHandler {
    private final SmartHome smartHome;

    public AlarmHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event.getType() != SensorEventType.ALARM_EVENT) return;

        smartHome.execute( (Actionable actionable) -> {
            if (!(actionable instanceof Alarm)) return;
            Alarm alarm = (Alarm) actionable;
            if (!(alarm.getId().equals(event.getObjectId()))) return;

            if (event.getActionType() == AlarmActionType.ACTIVE) {
                activateAlarm(alarm);
            }
            if (event.getActionType() == AlarmActionType.DEACTIVE) {
                deactivateAlarm(alarm);
            }

        });
    }

    private void deactivateAlarm(Alarm alarm) {
        int code = CodeGetter.getCode();
        alarm.deactivate(code);
        System.out.println("Alarm " + alarm.getId() + " was deactivated.");
    }

    private void activateAlarm(Alarm alarm) {
        int code = CodeGetter.getCode();
        alarm.activate(code);
        System.out.println("Alarm " + alarm.getId() + " was activated.");
    }
}
