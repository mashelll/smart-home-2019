package ru.sbt.mipt.oop.tests.remote;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rc.RemoteControl;
import ru.sbt.mipt.oop.remote.control.Button;
import ru.sbt.mipt.oop.remote.control.RemoteControlImpl;
import ru.sbt.mipt.oop.remote.control.commands.*;
import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smart.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smart.devices.alarm.Alert;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class ButtonsTests {
    private RemoteControl remoteControl;
    private SmartHome smartHome;
    private Door door1;
    private Light light1;
    private Light light2;
    private Collection<Light> lights1;
    private Collection<Light> lights2;

    @Before
    public void setUp() {
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
        TriggerAlertCommand enableSignalingCommand = new TriggerAlertCommand(alarm);
        TurnOffLightCommand turnOffLightsCommand = new TurnOffLightCommand(smartHome);
        TurnOnLightCommand turnOnAllLightsCommand = new TurnOnLightCommand(smartHome);
        TurnOnHallLightCommand turnOnLightInHallwayCommand = new TurnOnHallLightCommand(smartHome);

        final HashMap<String, Button> buttons = new HashMap<>();
        buttons.put("A", new Button("A"));
        buttons.put("B", new Button("B"));
        buttons.put("C", new Button("C"));
        buttons.put("D", new Button("D"));
        buttons.put("1", new Button("1"));
        buttons.put("2", new Button("2"));
        buttons.put("3", new Button("3"));
        buttons.put("4", new Button("4"));
        RemoteControlImpl remoteControl = new RemoteControlImpl(buttons);
        remoteControl.setCommand("A", new ActivateAlarmCommand(alarm, "123456"));
        remoteControl.setCommand("B", new CloseEntranceDoorCommand(smartHome));
        remoteControl.setCommand("C", new TriggerAlertCommand(alarm));
        remoteControl.setCommand("D", new TurnOffLightCommand(smartHome));
        remoteControl.setCommand("1", new TurnOnHallLightCommand(smartHome));
        remoteControl.setCommand("2", new TurnOnLightCommand(smartHome));
    }

    @Test
    public void executeButtonATest() {
        remoteControl.onButtonPressed("A", "remote1");
        assertThat(smartHome.getAlarm().getState(), instanceOf(ActivateAlarmCommand.class));
    }

    @Test
    public void executeButtonBTest() {
        remoteControl.onButtonPressed("B", "remote1");
        assertFalse(door1.isOpen());
    }

    @Test
    public void executeButtonCTest() {
        remoteControl.onButtonPressed("C", "remote1");
        assertThat(smartHome.getAlarm().getState(), instanceOf(Alert.class));
    }

    @Test
    public void executeButtonDTest() {
        remoteControl.onButtonPressed("D", "remote1");
        for (Light light : lights1) {
            Assertions.assertFalse(light.isOn());
        }
    }

    @Test
    public void executeButton1Test() {
        remoteControl.onButtonPressed("1", "remote1");
        for (Light light : lights1) {
            assertTrue(light.isOn());
        }
    }

    @Test
    public void executeButton2Test() {
        remoteControl.onButtonPressed("2", "remote1");
        assertTrue(light2.isOn());
        assertTrue(light2.isOn());
    }
}
