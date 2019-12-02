package ru.sbt.mipt.oop.sensor.event.factories;

import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.types.ActionType;
import ru.sbt.mipt.oop.sensor.event.types.LightActionType;
import ru.sbt.mipt.oop.sensor.event.types.SensorEventType;

import static ru.sbt.mipt.oop.sensor.event.types.SensorEventType.LIGHT_EVENT;

public class LightEventFactory implements SensorEventFactory {
    ActionType actionType;

    public LightEventFactory(ActionType actionType) {
        this.actionType = actionType;
    }

    @Override
    public SensorEvent generateSensorEvent(String objectId) {
        return new SensorEvent(LIGHT_EVENT, (LightActionType) actionType, objectId);
    }
}
