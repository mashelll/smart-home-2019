package ru.sbt.mipt.oop.remote.control.commands;

import ru.sbt.mipt.oop.scenarios.LightScenarios;
import ru.sbt.mipt.oop.smarthome.SmartHome;

public class TurnOffLightCommand implements Command {
    private final SmartHome smartHome;

    public TurnOffLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        LightScenarios.turnAllLightsOff(smartHome);
    }
}
