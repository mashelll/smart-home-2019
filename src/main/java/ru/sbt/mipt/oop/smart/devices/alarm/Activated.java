package ru.sbt.mipt.oop.smart.devices.alarm;

public class Activated implements AlarmState {
    private Alarm alarm;

    public Activated(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (alarm.verifyCode(code)) {
            alarm.setState(new Deactivated(alarm));
        } else {
            alarm.triggerAlert();
        }
    }

    @Override
    public void triggerAlert() {
        alarm.setState(new Alert(alarm));
    }
}
