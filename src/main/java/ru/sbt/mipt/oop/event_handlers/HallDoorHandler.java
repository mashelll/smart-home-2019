package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.sensor_event.types.DoorActionType;
import ru.sbt.mipt.oop.sensor_event.SensorEvent;
import ru.sbt.mipt.oop.sensor_event.types.SensorEventType;
import ru.sbt.mipt.oop.smart_devices.Door;
import ru.sbt.mipt.oop.smart_devices.Light;
import ru.sbt.mipt.oop.smart_home.Actionable;
import ru.sbt.mipt.oop.smart_home.Room;
import ru.sbt.mipt.oop.smart_home.SmartHome;

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
