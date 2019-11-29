package ru.sbt.mipt.oop.smart.devices.alarm;

import ru.sbt.mipt.oop.smart.devices.SmartDevice;

public class Alarm extends SmartDevice {
    private AlarmState state;
    private int code;

    public Alarm(String id, int code) {
        super(id);
        this.code = code;
        this.state = new Deactivated(this);
    }

    public void setState(AlarmState state) {
        this.state = state;
    }

    public AlarmState getState() {
        return state;
    }

    void setCode(int code) {
        this.code = code;
    }

    public boolean verifyCode(int code) {
        return (this.code == code);
    }

    public void activate(int code) {
        state.activate(code);
    }

    public void deactivate(int code) {
        state.deactivate(code);
    }

    public void triggerAlert() {
        state.triggerAlert();
    }
}