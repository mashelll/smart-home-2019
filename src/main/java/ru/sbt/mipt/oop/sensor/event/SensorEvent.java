package ru.sbt.mipt.oop.sensor.event;

import ru.sbt.mipt.oop.sensor.event.action.types.ActionType;

public class SensorEvent {
    private final ActionType actionType;
    private final String objectId;

    public SensorEvent(ActionType actionType, String objectId) {
        this.actionType = actionType;
        this.objectId = objectId;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}