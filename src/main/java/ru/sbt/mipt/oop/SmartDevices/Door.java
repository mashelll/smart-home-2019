package ru.sbt.mipt.oop.SmartDevices;

import ru.sbt.mipt.oop.SmartHome.HomeObject;

public class Door extends SmartDevice implements HomeObject {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
