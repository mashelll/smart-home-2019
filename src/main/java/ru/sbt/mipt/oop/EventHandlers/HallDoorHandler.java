package ru.sbt.mipt.oop.EventHandlers;

import ru.sbt.mipt.oop.SensorEvent.DoorActionType;
import ru.sbt.mipt.oop.SensorEvent.SensorEvent;
import ru.sbt.mipt.oop.SensorEvent.SensorEventType;
import ru.sbt.mipt.oop.SmartDevices.Door;
import ru.sbt.mipt.oop.SmartDevices.Light;
import ru.sbt.mipt.oop.SmartHome.Actionable;
import ru.sbt.mipt.oop.SmartHome.Room;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

public class HallDoorHandler implements EventHandler {
    private final SmartHome smartHome;

    public HallDoorHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event.getType() != SensorEventType.DOOR_EVENT) return;
        if (event.getActionType() != DoorActionType.CLOSE) return;

        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Room)) return;
            Room room = (Room) actionable;
            if (!(room.getName().equals("hall"))) return;

            room.execute((Actionable hallActionable) -> {
                if (!(hallActionable instanceof Door)) return;
                if (!((Door) hallActionable).getId().equals(event.getObjectId())) return;
                turnAllLightsOff(smartHome);
            });
        });
    }

    public static void turnAllLightsOff(SmartHome smartHome){
        System.out.println("Turning all lights off.");
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Light)) return;
            ((Light) actionable).setOn(false);
        });
    }
}
