package ru.sbt.mipt.oop.sensor_event.types;

import ru.sbt.mipt.oop.sensor_event.SensorEvent;
import ru.sbt.mipt.oop.sensor_event.action_types.ActionType;

public class DoorEvent extends SensorEvent {
    public DoorEvent(ActionType actionType, String objectId) {
        super(actionType, objectId);
    }
}
