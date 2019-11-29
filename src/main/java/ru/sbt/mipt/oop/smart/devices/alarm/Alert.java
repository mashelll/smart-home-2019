package ru.sbt.mipt.oop.smart.devices.alarm;

public class Alert implements AlarmState {
    private Alarm alarm;

    public Alert(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (alarm.verifyCode(code)) {
            alarm.setState(new Deactivated(alarm));
        }
    }

    @Override
    public void triggerAlert() {
    }
}