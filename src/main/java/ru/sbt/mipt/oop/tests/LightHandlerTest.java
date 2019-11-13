package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.event.handlers.EventHandler;
import ru.sbt.mipt.oop.event.handlers.LightHandler;
import ru.sbt.mipt.oop.reading.utils.SmartHomeReader;
import ru.sbt.mipt.oop.reading.utils.SmartHomeReaderJSON;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.action_types.LightActionType;
import ru.sbt.mipt.oop.sensor.event.action_types.SensorEventType;
import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.SmartHome;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class LightHandlerTest {
    private SmartHome smartHome;
    private EventHandler eventHandler;
    private List<String> turnedOnLights;
    private List<String> turnedOffLights;
    private Random randomGenerator;

    @BeforeEach
    void setUp() throws IOException {
        SmartHomeReader smartHomeReader = new SmartHomeReaderJSON();
        SmartHome smartHome = smartHomeReader.read();
        eventHandler = new LightHandler(smartHome);
        turnedOnLights = getLights(true);
        turnedOffLights = getLights(false);
        randomGenerator = new Random();
    }

    @Test
    void testTurnedOffLightTurnsOn() {
        int randInt = randomGenerator.nextInt(5);
        String lightId = turnedOffLights.get(randInt);
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_EVENT, LightActionType.ON, lightId);
        eventHandler.handleEvent(event);
        turnedOnLights = getLights(true);
        turnedOffLights = getLights(false);
        turnedOffLights.remove(lightId);
        turnedOnLights.add(lightId);

        checkLightsCondition(turnedOnLights, true);
        checkLightsCondition(turnedOffLights, false);
    }

    @Test
    void testTurnedOnLightTurnsOff() {
        int randInt = randomGenerator.nextInt(turnedOnLights.size());
        String lightId = turnedOnLights.get(randInt);
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_EVENT, LightActionType.OFF, lightId);

        eventHandler.handleEvent(event);
        turnedOnLights.remove(lightId);
        turnedOffLights.add(lightId);

        checkLightsCondition(turnedOnLights, true);
        checkLightsCondition(turnedOffLights, false);
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

    private void checkLightsCondition(List<String> lightIds, boolean condition) {
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Light)) return;
            Light light = (Light) actionable;
            if (!(lightIds.contains(light.getId()))) return;
            assertEquals(light.isOn(), condition);
        });
    }

}