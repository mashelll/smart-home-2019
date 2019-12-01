package ru.sbt.mipt.oop.event.handlers;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;

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

    public SensorEventAdapter(SensorEventHandler sensorEventHandler) {
        this.sensorEventHandler = sensorEventHandler;
    }


    @Override
    public void handleEvent(CCSensorEvent ccSensorevent) {
        SensorEvent sensorEvent = adaptCCSensorEvent(ccSensorevent);
        if (sensorEvent != null) {
            sensorEventHandler.handleEvent(sensorEvent);
        }
    }

    private SensorEvent adaptCCSensorEvent(CCSensorEvent event) {
        Map<String, SensorEvent> sensorEvents = new HashMap<>();
        sensorEvents.put("LightIsOn", new SensorEvent(LIGHT_EVENT, ON, event.getEventType()));
        sensorEvents.put("LightIsOff", new SensorEvent(LIGHT_EVENT, OFF, event.getEventType()));
        sensorEvents.put("DoorIsOpen", new SensorEvent(DOOR_EVENT, OPEN, event.getEventType()));
        sensorEvents.put("DoorIsClosed", new SensorEvent(DOOR_EVENT, CLOSE, event.getEventType()));
        sensorEvents.put("DoorIsLocked", new SensorEvent(DOOR_EVENT, CLOSE, event.getEventType()));
        sensorEvents.put("DoorIsUnlocked", new SensorEvent(DOOR_EVENT, OPEN, event.getEventType()));

        return sensorEvents.get(event.getEventType());
    }
}
