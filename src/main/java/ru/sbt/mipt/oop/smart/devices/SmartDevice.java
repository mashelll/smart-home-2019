package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Actionable;

public class SmartDevice implements Actionable {
    private final String id;

    public SmartDevice(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public void execute(Action action) {
        action.run(this);
    }
}
