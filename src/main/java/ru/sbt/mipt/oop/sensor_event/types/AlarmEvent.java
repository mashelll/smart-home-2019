package ru.sbt.mipt.oop.sensor_event.types;

import ru.sbt.mipt.oop.sensor_event.SensorEvent;
import ru.sbt.mipt.oop.sensor_event.action_types.ActionType;

public class AlarmEvent extends SensorEvent {
    private final int code;

    public AlarmEvent(ActionType actionType, String objectId, int code) {
        super(actionType, objectId);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
