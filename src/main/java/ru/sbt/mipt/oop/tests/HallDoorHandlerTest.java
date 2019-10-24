package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.event_handlers.EventHandler;
import ru.sbt.mipt.oop.reading_utils.SmartHomeReader;
import ru.sbt.mipt.oop.reading_utils.SmartHomeReaderJSON;
import ru.sbt.mipt.oop.sensor_event.SensorEvent;
import ru.sbt.mipt.oop.sensor_event.types.DoorActionType;
import ru.sbt.mipt.oop.sensor_event.types.SensorEventType;
import ru.sbt.mipt.oop.smart_devices.Door;
import ru.sbt.mipt.oop.smart_devices.Light;
import ru.sbt.mipt.oop.smart_home.Actionable;
import ru.sbt.mipt.oop.smart_home.SmartHome;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class HallDoorHandlerTest {
    private SmartHome smartHome;
    private EventHandler eventHandler;
    private List<String> turnedOnLights;
    private List<String> turnedOffLights;
    private String HALL_DOOR_ID;

    @BeforeEach
    void setUp() throws IOException {
        SmartHomeReader smartHomeReader = new SmartHomeReaderJSON();
        SmartHome smartHome = smartHomeReader.read();
        turnedOnLights = getLights(true);
        turnedOffLights = getLights(false);
        HALL_DOOR_ID = "4";
    }

    @Test
    void testAllTurnedOnLightsTurnOffAfterClosingHallDoor() {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_EVENT, DoorActionType.CLOSE, HALL_DOOR_ID);
        checkLightsCondition(turnedOnLights, true);
        eventHandler.handleEvent(event);
        checkLightsCondition(turnedOnLights, false);
    }

    @Test
    void testAllTurnedOffLightsRemainTurnedOffAfterClosingHallDoor() {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_EVENT, DoorActionType.CLOSE, HALL_DOOR_ID);
        checkLightsCondition(turnedOffLights, false);
        eventHandler.handleEvent(event);
        checkLightsCondition(turnedOffLights, false);
    }

    @Test
    void testNothingChangesAfterClosingNotHallDoor() {
        List<String> notHallDoorIds = getNotHallDoorIds();
        for (String notHallDoor : notHallDoorIds) {
            SensorEvent event = new SensorEvent(SensorEventType.DOOR_EVENT, DoorActionType.CLOSE, notHallDoor);
            checkLightsCondition(turnedOnLights, true);
            checkLightsCondition(turnedOffLights, false);
            eventHandler.handleEvent(event);
            checkLightsCondition(turnedOnLights, true);
            checkLightsCondition(turnedOffLights, false);
        }

    }

    private List<String> getNotHallDoorIds() {
        List<String> notHallDoorIds = new ArrayList<>();
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Door)) return;
            Door door = (Door) actionable;
            if (door.getId().equals(HALL_DOOR_ID)) return;
            notHallDoorIds.add(door.getId());
        });
        return notHallDoorIds;
    }

    private void checkLightsCondition(List<String> lightIds, boolean condition) {
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Light)) return;
            Light light = (Light) actionable;
            if (!(lightIds.contains(light.getId()))) return;
            assertEquals(light.isOn(), condition);
        });
    }

    private List<String> getLights(boolean condition) {
        List<String> lights = new ArrayList<>();
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Light)) return;
            Light light = (Light) actionable;
            if (light.isOn() == condition) {
                lights.add(light.getId());
            }
        });
        return lights;
    }
}