package ru.sbt.mipt.oop.smart.devices.alarm;

public interface AlarmState {
    void activate(String code);
    void deactivate(String code);
    void triggerAlert();
}
