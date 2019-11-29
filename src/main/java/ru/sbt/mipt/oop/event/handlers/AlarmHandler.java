package ru.sbt.mipt.oop.event.handlers;

import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.types.AlarmActionType;
import ru.sbt.mipt.oop.sensor.event.types.SensorEventType;
import ru.sbt.mipt.oop.smart.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smart.devices.alarm.Alert;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.SmartHome;

public class AlarmHandler implements SensorEventHandler {
    private final SmartHome smartHome;

    public AlarmHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (!(event.getType() != SensorEventType.ALARM_EVENT)) return;
        String code = smartHome.getAlarm().getCode();
        smartHome.execute( (Actionable actionable) -> {
            if (!(actionable instanceof Alarm)) return;
            Alarm alarm = (Alarm) actionable;
            if (!(alarm.getId().equals(event.getObjectId()))) return;

            if (event.getActionType() == AlarmActionType.ACTIVE) {
                activateAlarm(alarm, code);
            }
            if (event.getActionType() == AlarmActionType.DEACTIVE) {
                deactivateAlarm(alarm, code);
            }

        });
    }

    private void deactivateAlarm(Alarm alarm, String code) {
        alarm.deactivate(code);
        if (alarm.getState() instanceof Alert) {
            System.out.println("Wrong code. Alert was triggered");
        }
        else {
            System.out.println("Alarm was deactivated.");
        }
    }

    private void activateAlarm(Alarm alarm, String code) {
        alarm.activate(code);
        System.out.println("Alarm was activated.");
    }
}