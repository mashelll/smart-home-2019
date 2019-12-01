package ru.sbt.mipt.oop.tests.remote;

import org.junit.jupiter.api.Test;
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

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class RemoteCommandsTests {
    Alarm alarm = new Alarm("1", "123456");

    @Test
    public void ActivateAlarmCommandTest() {
        ActivateAlarmCommand activateAlarmCommand = new ActivateAlarmCommand(alarm, "123456");
        activateAlarmCommand.execute();
        assertThat(alarm.getState(), instanceOf(Activated.class));
    }

    @Test
    public void CloseEntranceDoorCommandTest() {
        Light light1 = new Light("1", true);
        Light light2 = new Light("2", true);
        Light light3 = new Light("3", true);
        Light light4 = new Light("4", true);

        Door door1 = new Door(true, "1");
        Door door2 = new Door(true, "2");

        Collection<Light> lights1 = new ArrayList<>();
        lights1.add(light1);
        lights1.add(light2);
        Collection<Light> lights2 = new ArrayList<>();
        lights2.add(light3);
        lights2.add(light4);

        Collection<Door> doors1 = new ArrayList<>();
        doors1.add(door1);
        Collection<Door> doors2 = new ArrayList<>();
        doors2.add(door2);

        Room room1 = new Room(lights1, doors1, "hall");
        Room room2 = new Room(lights2, doors2, "kitchen");

        SmartHome smartHome = new SmartHome(alarm);
        smartHome.addRoom(room1);
        smartHome.addRoom(room2);

        CloseEntranceDoorCommand closeEntranceDoorCommand = new CloseEntranceDoorCommand(smartHome);
        closeEntranceDoorCommand.execute();
        assertFalse(door1.isOpen());
    }

        @Test
        public void EnableSignalingCommandTest() {
            SmartHome smartHome = new SmartHome(alarm);
            TriggerAlertCommand triggerAlertCommand = new TriggerAlertCommand(alarm);
            triggerAlertCommand.execute();
            assertThat(alarm.getState(), instanceOf(Alert.class));
        }


        @Test
        public void TurnOffLightCommandTest() {
            Light light1 = new Light("1", true);
            Light light2 = new Light("2", true);
            Light light3 = new Light("3", true);
            Light light4 = new Light("4", true);

            Door door1 = new Door(true, "1");
            Door door2 = new Door(true, "2");

            Collection<Light> lights1 = new ArrayList<>();
            lights1.add(light1);
            lights1.add(light2);
            Collection<Light> lights2 = new ArrayList<>();
            lights2.add(light3);
            lights2.add(light4);

            Collection<Door> doors1 = new ArrayList<>();
            doors1.add(door1);
            Collection<Door> doors2 = new ArrayList<>();
            doors2.add(door2);

            Room room1 = new Room(lights1, doors1, "hall");
            Room room2 = new Room(lights2, doors2, "kitchen");

            SmartHome smartHome = new SmartHome(alarm);
            smartHome.addRoom(room1);
            smartHome.addRoom(room2);


            TurnOffLightCommand turnOffLightsCommand = new TurnOffLightCommand(smartHome);
            turnOffLightsCommand.execute();

            for (Light light : lights1) {
                assertFalse(light.isOn());
            }
        }


        @Test
        public void TurnOnAllLightCommandTest() {
            Light light1 = new Light("1", true);
            Light light2 = new Light("2", true);
            Light light3 = new Light("3", true);
            Light light4 = new Light("4", true);

            Door door1 = new Door(true, "1");
            Door door2 = new Door(true, "2");

            Collection<Light> lights1 = new ArrayList<>();
            lights1.add(light1);
            lights1.add(light2);
            Collection<Light> lights2 = new ArrayList<>();
            lights2.add(light3);
            lights2.add(light4);

            Collection<Door> doors1 = new ArrayList<>();
            doors1.add(door1);
            Collection<Door> doors2 = new ArrayList<>();
            doors2.add(door2);

            Room room1 = new Room(lights1, doors1, "hall");
            Room room2 = new Room(lights2, doors2, "kitchen");

            SmartHome smartHome = new SmartHome(alarm);
            smartHome.addRoom(room1);
            smartHome.addRoom(room2);


            TurnOnLightCommand turnOnAllLightsCommand = new TurnOnLightCommand(smartHome);
            turnOnAllLightsCommand.execute();

            for (Light light : lights1) {
                assertTrue(light.isOn());
            }
        }


        @Test
        public void TurnOnHallLightCommandTest() {
            Light light1 = new Light("1", false);
            Light light2 = new Light("2", false);
            Light light3 = new Light("3", true);
            Light light4 = new Light("4", true);

            Door door1 = new Door(true, "1");
            Door door2 = new Door(true, "2");

            Collection<Light> lights1 = new ArrayList<>();
            lights1.add(light1);
            lights1.add(light2);
            Collection<Light> lights2 = new ArrayList<>();
            lights2.add(light3);
            lights2.add(light4);

            Collection<Door> doors1 = new ArrayList<>();
            doors1.add(door1);
            Collection<Door> doors2 = new ArrayList<>();
            doors2.add(door2);

            Room room1 = new Room(lights1, doors1, "hall");
            Room room2 = new Room(lights2, doors2, "kitchen");

            SmartHome smartHome = new SmartHome(alarm);
            smartHome.addRoom(room1);
            smartHome.addRoom(room2);

            TurnOnHallLightCommand turnOnLightInHallwayCommand = new TurnOnHallLightCommand(smartHome);
            turnOnLightInHallwayCommand.execute();
            assertTrue(light2.isOn());
            assertTrue(light2.isOn());

        }

}
