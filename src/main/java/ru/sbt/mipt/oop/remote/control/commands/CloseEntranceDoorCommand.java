package ru.sbt.mipt.oop.remote.control.commands;

import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;

public class CloseEntranceDoorCommand implements Command {
    private final SmartHome smartHome;

    public CloseEntranceDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Room)) return;
            Room room = (Room) actionable;
            if (!(room.getName().equals("hall"))) return;

            room.execute((Actionable hallActionable) -> {
                if (!(hallActionable instanceof Door)) return;
                ((Door) hallActionable).setOpen(false);
            });
        });
    }

}