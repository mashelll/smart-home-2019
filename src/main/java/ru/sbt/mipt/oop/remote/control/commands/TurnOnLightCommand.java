package ru.sbt.mipt.oop.remote.control.commands;

import ru.sbt.mipt.oop.remote.control.commands.Command;
import ru.sbt.mipt.oop.scenarios.LightScenarios;
import ru.sbt.mipt.oop.smarthome.SmartHome;

public class TurnOnLightCommand implements Command {
    private final SmartHome smartHome;

    public TurnOnLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        LightScenarios.turnAllLightsOn(smartHome);
    }
}
