package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.sensor_event.types.LightActionType;
import ru.sbt.mipt.oop.sensor_event.SensorEvent;
import ru.sbt.mipt.oop.sensor_event.types.SensorEventType;
import ru.sbt.mipt.oop.smart_devices.Light;
import ru.sbt.mipt.oop.smart_home.Actionable;
import ru.sbt.mipt.oop.smart_home.SmartHome;


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

        smartHome.execute(
                (Actionable actionable) -> {
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
