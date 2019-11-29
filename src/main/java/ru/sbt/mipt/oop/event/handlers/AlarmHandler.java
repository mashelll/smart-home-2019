package ru.sbt.mipt.oop.event.handlers;

import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.types.AlarmActionType;
import ru.sbt.mipt.oop.sensor.event.types.SensorEventType;
import ru.sbt.mipt.oop.smart.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smart.devices.alarm.CodeGetter;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.SmartHome;

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
