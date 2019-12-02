package ru.sbt.mipt.oop.event.handlers;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.factories.SensorEventFactory;

import java.util.HashMap;
import java.util.Map;

import static ru.sbt.mipt.oop.sensor.event.types.DoorActionType.CLOSE;
import static ru.sbt.mipt.oop.sensor.event.types.DoorActionType.OPEN;
import static ru.sbt.mipt.oop.sensor.event.types.LightActionType.OFF;
import static ru.sbt.mipt.oop.sensor.event.types.LightActionType.ON;
import static ru.sbt.mipt.oop.sensor.event.types.SensorEventType.DOOR_EVENT;
import static ru.sbt.mipt.oop.sensor.event.types.SensorEventType.LIGHT_EVENT;


public class SensorEventAdapter implements EventHandler {
    private final SensorEventHandler sensorEventHandler;
    private final Map<String, SensorEventFactory> sensorEventFactories;

    public SensorEventAdapter(SensorEventHandler sensorEventHandler, Map<String, SensorEventFactory> sensorEventFactories) {
        this.sensorEventHandler = sensorEventHandler;
        this.sensorEventFactories = sensorEventFactories;
    }


    @Override
    public void handleEvent(CCSensorEvent ccSensorevent) {
        SensorEvent sensorEvent = adaptCCSensorEvent(ccSensorevent);
        if (sensorEvent != null) {
            sensorEventHandler.handleEvent(sensorEvent);
        }
    }

    private SensorEvent adaptCCSensorEvent(CCSensorEvent event) {
        return sensorEventFactories.get(event.getEventType()).generateSensorEvent(event.getObjectId());
    }
}
