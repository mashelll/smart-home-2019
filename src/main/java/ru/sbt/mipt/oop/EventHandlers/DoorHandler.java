package ru.sbt.mipt.oop.EventHandlers;

import ru.sbt.mipt.oop.SensorEvent.DoorActionType;
import ru.sbt.mipt.oop.SensorEvent.SensorEvent;
import ru.sbt.mipt.oop.SensorEvent.SensorEventType;
import ru.sbt.mipt.oop.SmartDevices.Door;
import ru.sbt.mipt.oop.SmartHome.Actionable;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

public class DoorHandler implements EventHandler {
    private final SmartHome smartHome;

    public DoorHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event.getType() != SensorEventType.DOOR_EVENT) return;

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
