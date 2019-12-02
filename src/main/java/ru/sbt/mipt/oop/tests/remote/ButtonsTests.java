package ru.sbt.mipt.oop.tests.remote;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.remote.control.RemoteControlImpl;
import ru.sbt.mipt.oop.remote.control.commands.*;
import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smart.devices.alarm.Activated;
import ru.sbt.mipt.oop.smart.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smart.devices.alarm.Alert;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class ButtonsTests {
    @Test
    public void ExecuteTest() {
        SmartHome smartHome;
        Door door1;
        Light light1;
        Light light2;
        Collection<Light> lights1;
        Collection<Light> lights2;

        light1 = new Light("1", true);
        light2 = new Light("2", true);
        Light light3 = new Light("3", true);
        Light light4 = new Light("4", true);

        door1 = new Door(true, "1");
        Door door2 = new Door(true, "2");

        lights1 = new ArrayList<>();
        lights1.add(light1);
        lights1.add(light2);
        lights2 = new ArrayList<>();
        lights2.add(light3);
        lights2.add(light4);

        Collection<Door> doors1 = new ArrayList<>();
        doors1.add(door1);
        Collection<Door> doors2 = new ArrayList<>();
        doors2.add(door2);

        Room room1 = new Room(lights1, doors1, "hall");
        Room room2 = new Room(lights2, doors2, "kitchen");

        Alarm alarm = new Alarm("1", "123456");
        smartHome = new SmartHome(alarm);
        smartHome.addRoom(room1);
        smartHome.addRoom(room2);

        ActivateAlarmCommand activateAlarmCommand = new ActivateAlarmCommand(alarm, "123456");
        CloseEntranceDoorCommand closeEntranceDoorCommand = new CloseEntranceDoorCommand(smartHome);
        TriggerAlertCommand triggerAlertCommand = new TriggerAlertCommand(alarm);
        TurnOffLightCommand turnOffLightCommand = new TurnOffLightCommand(smartHome);
        TurnOnLightCommand turnOnLightCommand = new TurnOnLightCommand(smartHome);
        TurnOnHallLightCommand turnOnHallLightCommand = new TurnOnHallLightCommand(smartHome);

        RemoteControlImpl remoteControl = new RemoteControlImpl();
        remoteControl.setCommand("A", activateAlarmCommand);
        remoteControl.setCommand("B", closeEntranceDoorCommand);
        remoteControl.setCommand("C", triggerAlertCommand);
        remoteControl.setCommand("D", turnOnLightCommand);
        remoteControl.setCommand("1", turnOffLightCommand);
        remoteControl.setCommand("2", turnOnHallLightCommand);

        remoteControl.onButtonPressed("A", "remote1");
        assertThat(alarm.getState(), instanceOf(Activated.class));

        remoteControl.onButtonPressed("B", "remote1");
        assertFalse(door1.isOpen());

        remoteControl.onButtonPressed("C", "remote1");
        assertThat(smartHome.getAlarm().getState(), instanceOf(Alert.class));

        remoteControl.onButtonPressed("D", "remote1");
        for (Light light : lights1) {
            assertTrue(light.isOn());
        }

        remoteControl.onButtonPressed("1", "remote1");
        for (Light light : lights1) {
            assertFalse(light.isOn());
        }

        remoteControl.onButtonPressed("2", "remote1");
        assertTrue(light2.isOn());
        assertTrue(light2.isOn());
    }
}

