package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.remote.control.RemoteControlImpl;
import ru.sbt.mipt.oop.remote.control.commands.CloseHallDoorCommand;
import ru.sbt.mipt.oop.smarthome.SmartHome;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoteTest {
    private SmartHome smartHome;
    private CloseHallDoorCommand closeHallDoorCommand = new CloseHallDoorCommand(smartHome);
}
