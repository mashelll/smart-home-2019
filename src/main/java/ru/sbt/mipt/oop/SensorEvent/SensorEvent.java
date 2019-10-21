package ru.sbt.mipt.oop.SensorEvent;

public class SensorEvent {
    private final SensorEventType type;
    private final ActionType actionType;
    private final String objectId;

    public SensorEvent(SensorEventType type, ActionType actionType, String objectId) {
        this.type = type;
        this.actionType = actionType;
        this.objectId = objectId;
    }

    public SensorEventType getType() {
        return type;
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
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
