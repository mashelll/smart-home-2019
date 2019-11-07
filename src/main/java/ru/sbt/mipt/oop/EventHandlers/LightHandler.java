package ru.sbt.mipt.oop.EventHandlers;

import ru.sbt.mipt.oop.SensorEvent.LightActionType;
import ru.sbt.mipt.oop.SensorEvent.SensorEvent;
import ru.sbt.mipt.oop.SensorEvent.SensorEventType;
import ru.sbt.mipt.oop.SmartDevices.Light;
import ru.sbt.mipt.oop.SmartHome.Actionable;
import ru.sbt.mipt.oop.SmartHome.SmartHome;


public class LightHandler implements EventHandler {

    private final SmartHome smartHome;

    public LightHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event.getType() != SensorEventType.LIGHT_EVENT){
            return;
        }

        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Light)) return;
            Light light = (Light) actionable;
            if (!(light.getId().equals(event.getObjectId()))) return;

            if (event.getType().equals(LightActionType.ON)) {
                setLightOn(light);
            }
            if (event.getType().equals(LightActionType.OFF)) {
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
