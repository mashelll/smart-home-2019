package ru.sbt.mipt.oop.Command;


public class CommandSender {
    public static void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
