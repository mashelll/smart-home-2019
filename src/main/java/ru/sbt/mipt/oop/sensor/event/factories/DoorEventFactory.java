package ru.sbt.mipt.oop.sensor.event.factories;

import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.types.DoorActionType;

import static ru.sbt.mipt.oop.sensor.event.types.SensorEventType.DOOR_EVENT;


public class DoorEventFactory implements SensorEventFactory {
    DoorActionType doorActionType;

    public DoorEventFactory(DoorActionType doorActionType) {
        this.doorActionType = doorActionType;
    }

    @Override
    public SensorEvent generateSensorEvent(String objectId) {
        return new SensorEvent(DOOR_EVENT, doorActionType, objectId);
    }
}

