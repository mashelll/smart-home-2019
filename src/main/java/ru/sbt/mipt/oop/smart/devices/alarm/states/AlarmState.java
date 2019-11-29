package ru.sbt.mipt.oop.smart.devices.alarm.states;

public interface AlarmState {
    void activate(int code);
    void deactivate(int code);
    void triggerAlert();
}
