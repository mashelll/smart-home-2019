package ru.sbt.mipt.oop.SmartDevices;

import ru.sbt.mipt.oop.SmartHome.Action;
import ru.sbt.mipt.oop.SmartHome.Actionable;

public class SmartDevice implements Actionable {
    @Override
    public void execute(Action action) {
        action.run(this);
    }
}
