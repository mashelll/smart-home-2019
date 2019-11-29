package ru.sbt.mipt.oop.smart.devices.alarm;

import ru.sbt.mipt.oop.smart.devices.SmartDevice;

public class Alarm extends SmartDevice {
    private AlarmState state;
    private String code;

    public Alarm(String id, String code) {
        super(id);
        this.code = code;
        this.state = new Deactivated(this);
    }

    public String getCode() {
        return this.code;
    }

    public void setState(AlarmState state) {
        this.state = state;
    }

    public AlarmState getState() {
        return state;
    }

    void setCode(String code) {
        this.code = code;
    }

    public boolean verifyCode(String code) {
        return (this.code == code);
    }

    public void activate(String code) {
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void triggerAlert() {
        state.triggerAlert();
    }
}