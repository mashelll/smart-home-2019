package ru.sbt.mipt.oop.sensor.event;


import ru.sbt.mipt.oop.sensor.event.action.types.AlarmActionType;
import ru.sbt.mipt.oop.sensor.event.action.types.DoorActionType;
import ru.sbt.mipt.oop.sensor.event.action.types.LightActionType;
import ru.sbt.mipt.oop.sensor.event.types.AlarmEvent;
import ru.sbt.mipt.oop.sensor.event.types.DoorEvent;
import ru.sbt.mipt.oop.sensor.event.types.LightEvent;

import static java.lang.StrictMath.abs;

public class SensorEventGenerator implements SensorEventGetter {
    @Override
    public SensorEvent getNextSensorEvent() {
        String objectId = "" + (((int) (Math.random() * 100)) % 10);
        int actionType = (int) (Math.random() * 2);

        switch ((int) (Math.random() * 3)) {
            case (0):
                return new DoorEvent(DoorActionType.values()[actionType], objectId);
            case (1):
                return new LightEvent(LightActionType.values()[actionType], objectId);
            case (2):
                return new AlarmEvent(AlarmActionType.values()[actionType], "0", "12345");
            default:
                return null;
        }

    }
}