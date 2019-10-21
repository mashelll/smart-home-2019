package ru.sbt.mipt.oop.SmartDevices;

import ru.sbt.mipt.oop.SmartHome.HomeObject;

public class Light extends SmartDevice implements HomeObject {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}
