package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.smarthome.HomeObject;

public class Door extends SmartDevice implements HomeObject {
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        super(id);
        this.isOpen = isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
