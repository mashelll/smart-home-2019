package ru.sbt.mipt.oop.event.handlers;

import ru.sbt.mipt.oop.sensor.event.types.LightActionType;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.types.SensorEventType;
import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.SmartHome;


public class LightHandler implements SensorEventHandler {

    private final SmartHome smartHome;

    public LightHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (!(event.getType() != SensorEventType.LIGHT_EVENT)) {
            return;
        }

        smartHome.execute(
                (Actionable actionable) -> {
                    if (!(actionable instanceof Light)) return;
                    Light light = (Light) actionable;
                    if (!(light.getId().equals(event.getObjectId()))) return;

                    if (event.getActionType() == LightActionType.ON) {
                        setLightOn(light);
                    }
                    if (event.getActionType() == LightActionType.OFF) {
                        setLightOff(light);
                    }

                });
    }

    private void setLightOff(Light light) {
        light.setOn(false);
        System.out.println("Light " + light.getId() + " was turned off.");
    }

    private void setLightOn(Light light) {
        light.setOn(true);
        System.out.println("Light " + light.getId() + " was turned on.");
    }
}
