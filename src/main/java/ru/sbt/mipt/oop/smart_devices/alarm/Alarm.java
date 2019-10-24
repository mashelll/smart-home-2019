package ru.sbt.mipt.oop.smart_devices.alarm;

import ru.sbt.mipt.oop.smart_devices.SmartDevice;
import ru.sbt.mipt.oop.smart_devices.alarm.states.AlarmState;
import ru.sbt.mipt.oop.smart_devices.alarm.states.Deactivated;

public class Alarm extends SmartDevice {
    private AlarmState state;
    private final int code;
    private boolean isAlert;

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
