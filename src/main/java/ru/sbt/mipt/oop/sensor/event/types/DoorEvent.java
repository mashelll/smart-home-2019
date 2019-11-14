package ru.sbt.mipt.oop.sensor.event.types;

import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.action.types.ActionType;

public class DoorEvent extends SensorEvent {
    public DoorEvent(ActionType actionType, String objectId) {
        super(actionType, objectId);
    }
}
