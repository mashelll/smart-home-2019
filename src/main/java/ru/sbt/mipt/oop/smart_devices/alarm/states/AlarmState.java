package ru.sbt.mipt.oop.smart_devices.alarm.states;

public interface AlarmState {
    void activate(int code);
    void deactivate(int code);
    void triggerAlert();
}
