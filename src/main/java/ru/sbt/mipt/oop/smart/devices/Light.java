package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.smarthome.HomeObject;

public class Light extends SmartDevice implements HomeObject {
    private boolean isOn;

    public Light(String id, boolean isOn) {
        super(id);
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

}
