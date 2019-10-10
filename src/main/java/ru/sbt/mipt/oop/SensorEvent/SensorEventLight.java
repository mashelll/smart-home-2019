package main.java.ru.sbt.mipt.oop.SensorEvent;

import main.java.ru.sbt.mipt.oop.Command.CommandSender;
import main.java.ru.sbt.mipt.oop.Command.CommandType;
import main.java.ru.sbt.mipt.oop.Command.SensorCommand;
import main.java.ru.sbt.mipt.oop.HomeCreator.SmartHome;
import main.java.ru.sbt.mipt.oop.HomeFeatures.Light;
import main.java.ru.sbt.mipt.oop.HomeFeatures.Room;

import static main.java.ru.sbt.mipt.oop.SensorEvent.SensorEventType.LIGHT_ON;

public class SensorEventLight extends SensorEvent {
    public SensorEventLight(SensorEventType type, String objectId) {
        super(type, objectId);
    }

    @Override
    public void performEvent(SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(this.getObjectId())) {
                    if (this.getType() == LIGHT_ON) {
                        light.setOn(true);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                    } else {
                        light.setOn(false);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                    }
                }
            }
        }
    }

    public static void turnAllLightsOff(SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                CommandSender commandSender = new CommandSender();
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                commandSender.sendCommand(command);
            }
        }
    }
}
