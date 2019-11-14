package ru.sbt.mipt.oop.scenarios;

import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.command.SensorCommand;
import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.SmartHome;

public class LightScenarios implements Scenario {
    public static void turnAllLightsOff(SmartHome smartHome){
        System.out.println("Turning all lights off.");
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Light)) return;
            ((Light) actionable).setOn(false);
            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, ((Light) actionable).getId());
            CommandSender.sendCommand(command);
        });
    }

    public static void turnAllLightsOn(SmartHome smartHome){
        System.out.println("Turning all lights on.");
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Light)) return;
            ((Light) actionable).setOn(true);
            SensorCommand command = new SensorCommand(CommandType.LIGHT_ON, ((Light) actionable).getId());
            CommandSender.sendCommand(command);
        });
    }
}
