package ru.sbt.mipt.oop.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.EventHandlers.DoorHandler;
import ru.sbt.mipt.oop.EventHandlers.EventHandler;
import ru.sbt.mipt.oop.ReadingUtils.SmartHomeReader;
import ru.sbt.mipt.oop.ReadingUtils.SmartHomeReaderJSON;
import ru.sbt.mipt.oop.SensorEvent.SensorEvent;
import ru.sbt.mipt.oop.SensorEvent.SensorEventGetter;
import ru.sbt.mipt.oop.SensorEvent.Types.DoorActionType;
import ru.sbt.mipt.oop.SensorEvent.Types.SensorEventType;
import ru.sbt.mipt.oop.SmartDevices.Door;
import ru.sbt.mipt.oop.SmartHome.Actionable;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class DoorHandlerTest {
    private SmartHome smartHome;
    private EventHandler eventHandler;
    private List<String> openDoors;
    private List<String> closedDoors;
    private Random randomGenerator;

    @BeforeEach
    void setUp() throws IOException {
        SmartHomeReader smartHomeReader = new SmartHomeReaderJSON();
        SmartHome smartHome = smartHomeReader.read();
        eventHandler = new DoorHandler(smartHome);
        openDoors = getDoors(true);
        closedDoors = getDoors(false);
        randomGenerator = new Random();
    }

    @Test
    void testClosedDoorOpens() {
        int randInt = randomGenerator.nextInt(closedDoors.size());
        String doorId = closedDoors.get(randInt);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_EVENT, DoorActionType.OPEN, doorId);

        eventHandler.handleEvent(event);
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

        eventHandler.handleEvent(event);
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