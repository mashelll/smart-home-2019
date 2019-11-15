package ru.sbt.mipt.oop.sensor.event.types;

import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.action.types.ActionType;

public class AlarmEvent extends SensorEvent {
    private final String code;

    public AlarmEvent(ActionType actionType, String objectId, String code) {
        super(actionType, objectId);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
