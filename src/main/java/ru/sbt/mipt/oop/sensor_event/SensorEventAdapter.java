package ru.sbt.mipt.oop.sensor_event;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.sensor_event_handlers.SensorEventHandler;


import static ru.sbt.mipt.oop.sensor_event.types.DoorActionType.CLOSE;
import static ru.sbt.mipt.oop.sensor_event.types.DoorActionType.OPEN;
import static ru.sbt.mipt.oop.sensor_event.types.LightActionType.OFF;
import static ru.sbt.mipt.oop.sensor_event.types.LightActionType.ON;
import static ru.sbt.mipt.oop.sensor_event.types.SensorEventType.DOOR_EVENT;
import static ru.sbt.mipt.oop.sensor_event.types.SensorEventType.LIGHT_EVENT;

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
        SensorEvent sensorEvent = null;
        switch (event.getEventType()) {
            case "LightIsOn":
                sensorEvent = new SensorEvent(LIGHT_EVENT, ON, event.getObjectId());
                break;
            case "LightIsOff":
                sensorEvent = new SensorEvent(LIGHT_EVENT, OFF, event.getObjectId());
                break;
            case "DoorIsOpen":
                sensorEvent = new SensorEvent(DOOR_EVENT, OPEN, event.getObjectId());
                break;
            case "DoorIsClosed":
                sensorEvent = new SensorEvent(DOOR_EVENT, CLOSE, event.getObjectId());
                break;
            case "DoorIsLocked":
                sensorEvent = new SensorEvent(DOOR_EVENT, CLOSE, event.getObjectId());
                break;
            case "DoorIsUnlocked":
                sensorEvent = new SensorEvent(DOOR_EVENT, OPEN, event.getObjectId());
                break;
        }
        return sensorEvent;
    }
}
