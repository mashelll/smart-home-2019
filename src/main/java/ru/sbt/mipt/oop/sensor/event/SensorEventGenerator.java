package ru.sbt.mipt.oop.sensor.event;

import ru.sbt.mipt.oop.sensor.event.types.AlarmActionType;
import ru.sbt.mipt.oop.sensor.event.types.DoorActionType;
import ru.sbt.mipt.oop.sensor.event.types.LightActionType;


import static ru.sbt.mipt.oop.sensor.event.types.SensorEventType.*;

public class SensorEventGenerator implements SensorEventGetter {
    @Override
    public SensorEvent getNextSensorEvent() {
        String objectId = "" + (((int) (Math.random() * 100)) % 10);
        int actionType = (int) (Math.random() * 2);

        switch ((int) (Math.random() * 3)) {
            case (0):
                return new SensorEvent(DOOR_EVENT, DoorActionType.values()[actionType], objectId);
            case (1):
                return new SensorEvent(LIGHT_EVENT, LightActionType.values()[actionType], objectId);
            case (2):
                return new SensorEvent(ALARM_EVENT, AlarmActionType.values()[actionType], "0");
            default:
                return null;
        }

    }
}