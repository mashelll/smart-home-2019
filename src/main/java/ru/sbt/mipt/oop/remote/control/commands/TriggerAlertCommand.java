package ru.sbt.mipt.oop.remote.control.commands;

import ru.sbt.mipt.oop.remote.control.commands.Command;
import ru.sbt.mipt.oop.smart.devices.alarm.Alarm;

public class TriggerAlertCommand implements Command {

    private final Alarm alarm;

    public TriggerAlertCommand(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.triggerAlert();
    }

}
