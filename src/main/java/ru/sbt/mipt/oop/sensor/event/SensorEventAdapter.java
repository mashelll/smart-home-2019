package ru.sbt.mipt.oop.sensor.event;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.Executor;
import ru.sbt.mipt.oop.sensor.event.action.types.ActionType;
import ru.sbt.mipt.oop.sensor.event.action.types.DoorActionType;
import ru.sbt.mipt.oop.sensor.event.action.types.LightActionType;
import ru.sbt.mipt.oop.sensor.event.types.DoorEvent;
import ru.sbt.mipt.oop.sensor.event.types.LightEvent;


public class SensorEventAdapter extends Executor {
    SensorEventsManager sensorEventsManager = new SensorEventsManager();
    SensorEvent sensorEvent;

    public void run() {
        sensorEventsManager.registerEventHandler(event -> {
            System.out.println(adaptEvent(event));
        });
        sensorEventsManager.start();
    }

    private SensorEvent adaptEvent(CCSensorEvent event) {
        SensorEvent adaptedEvent;
        ActionType actionType;
        String objectId = event.getObjectId();

        switch (event.getEventType()) {
            case "LightIsOn":
                actionType = LightActionType.ON;
                adaptedEvent = new LightEvent(actionType, objectId);
                break;
            case "LightIsOff":
                actionType = LightActionType.OFF;
                adaptedEvent = new LightEvent(actionType, objectId);
                break;

            case "DoorIsOpen":
                actionType = DoorActionType.OPEN;
                adaptedEvent = new DoorEvent(actionType, objectId);
                break;
            case "DoorIsClosed":
                actionType = DoorActionType.CLOSE;
                adaptedEvent = new DoorEvent(actionType, objectId);
                break;

            default:
                adaptedEvent = null;
        }
        return sensorEvent;
    }
}
