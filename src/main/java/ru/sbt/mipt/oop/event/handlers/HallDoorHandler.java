package ru.sbt.mipt.oop.event.handlers;

import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.command.SensorCommand;
import ru.sbt.mipt.oop.sensor.event.types.DoorActionType;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.types.SensorEventType;
import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;

public class HallDoorHandler implements SensorEventHandler {
    private final SmartHome smartHome;

    public HallDoorHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (!(event.getType() != SensorEventType.DOOR_EVENT)) return;
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
            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, ((Light) actionable).getId());
            CommandSender.sendCommand(command);
        });
    }
}
