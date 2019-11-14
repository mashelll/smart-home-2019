package ru.sbt.mipt.oop.event.handlers;

import ru.sbt.mipt.oop.scenarios.LightScenarios;
import ru.sbt.mipt.oop.sensor.event.action.types.DoorActionType;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.types.DoorEvent;
import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;

public class HallDoorHandler implements EventHandler {
    private final SmartHome smartHome;

    public HallDoorHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (!(event instanceof DoorEvent)) return;
        if (event.getActionType() != DoorActionType.CLOSE) return;

        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Room)) return;
            Room room = (Room) actionable;
            if (!(room.getName().equals("hall"))) return;

            room.execute((Actionable hallActionable) -> {
                if (!(hallActionable instanceof Door)) return;
                if (!((Door) hallActionable).getId().equals(event.getObjectId())) return;
                LightScenarios.turnAllLightsOn(smartHome);
            });
        });
    }
}