package ru.sbt.mipt.oop.sensor.event.factories;

import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.types.LightActionType;


import static ru.sbt.mipt.oop.sensor.event.types.SensorEventType.LIGHT_EVENT;

public class LightEventFactory implements SensorEventFactory {
    LightActionType lightActionType;

    public LightEventFactory(LightActionType lightActionType) {
        this.lightActionType = lightActionType;
    }

    @Override
    public SensorEvent generateSensorEvent(String objectId) {
        return new SensorEvent(LIGHT_EVENT, lightActionType, objectId);
    }
}
