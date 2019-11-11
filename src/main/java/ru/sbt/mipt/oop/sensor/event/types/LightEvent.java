package ru.sbt.mipt.oop.sensor.event.types;

import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.action_types.ActionType;

public class LightEvent extends SensorEvent {
    public LightEvent(ActionType actionType, String objectId) {
        super(actionType, objectId);
    }
}