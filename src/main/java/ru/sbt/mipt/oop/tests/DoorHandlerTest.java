package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.sensor_event_handlers.DoorHandler;
import ru.sbt.mipt.oop.sensor_event_handlers.SensorEventHandler;
import ru.sbt.mipt.oop.reading_utils.SmartHomeReader;
import ru.sbt.mipt.oop.reading_utils.SmartHomeReaderJSON;
import ru.sbt.mipt.oop.sensor_event.SensorEvent;
import ru.sbt.mipt.oop.sensor_event.types.DoorActionType;
import ru.sbt.mipt.oop.sensor_event.types.SensorEventType;
import ru.sbt.mipt.oop.smart_devices.Door;
import ru.sbt.mipt.oop.smart_home.Actionable;
import ru.sbt.mipt.oop.smart_home.SmartHome;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class DoorHandlerTest {
    private SmartHome smartHome;
    private SensorEventHandler sensorEventHandler;
    private List<String> openDoors;
    private List<String> closedDoors;
    private Random randomGenerator;

    @BeforeEach
    void setUp() throws IOException {
        SmartHomeReader smartHomeReader = new SmartHomeReaderJSON();
        SmartHome smartHome = smartHomeReader.read();
        sensorEventHandler = new DoorHandler(smartHome);
        openDoors = getDoors(true);
        closedDoors = getDoors(false);
        randomGenerator = new Random();
    }

    @Test
    void testClosedDoorOpens() {
        try {
            setUp();
        } catch(IOException e) {
            System.out.println("IOException");
        }
        int randInt = randomGenerator.nextInt(closedDoors.size());
        String doorId = closedDoors.get(randInt);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_EVENT, DoorActionType.OPEN, doorId);

        sensorEventHandler.handleEvent(event);
        closedDoors.remove(doorId);
        openDoors.add(doorId);

        checkDoorsCondition(openDoors, true);
        checkDoorsCondition(closedDoors, false);
    }

    @Test
    void testOpenDoorCloses() {
        int randInt = randomGenerator.nextInt(openDoors.size());
        String doorId = openDoors.get(randInt);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_EVENT, DoorActionType.CLOSE, doorId);

        sensorEventHandler.handleEvent(event);
        openDoors.remove(doorId);
        closedDoors.add(doorId);

        checkDoorsCondition(openDoors, true);
        checkDoorsCondition(closedDoors, false);
    }

    private List<String> getDoors(boolean condition) {
        List<String> doors = new ArrayList<>();
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Door)) return;
            Door door = (Door) actionable;
            if (door.isOpen() == condition) {
                doors.add(door.getId());
            }
        });
        return doors;
    }

    private void checkDoorsCondition(List<String> doorIds, boolean condition) {
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Door)) return;
            Door door = (Door) actionable;
            if (!(doorIds.contains(door.getId()))) return;
            assertEquals(door.isOpen(), condition);
        });
    }


}