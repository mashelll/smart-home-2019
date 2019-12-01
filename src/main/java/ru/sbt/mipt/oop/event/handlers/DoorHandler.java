package ru.sbt.mipt.oop.event.handlers;

import ru.sbt.mipt.oop.sensor.event.types.DoorActionType;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.types.SensorEventType;
import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.SmartHome;

public class DoorHandler implements SensorEventHandler {
    private final SmartHome smartHome;

    public DoorHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (!(event.getType() != SensorEventType.DOOR_EVENT)) return;

        smartHome.execute( (Actionable actionable) -> {
            if (!(actionable instanceof Door)) return;
            Door door = (Door) actionable;
            if (!(door.getId().equals(event.getObjectId()))) return;

            if (event.getActionType() == DoorActionType.OPEN) {
                setDoorOpened(door);
            }
            if (event.getActionType() == DoorActionType.CLOSE) {
                setDoorClosed(door);
            }

        });
    }

    private void setDoorClosed(Door door) {
        door.setOpen(false);
        System.out.println("Door " + door.getId() + " was closed.");
    }

    private void setDoorOpened(Door door) {
        door.setOpen(true);
        System.out.println("Door " + door.getId() + " was opened.");
    }
}