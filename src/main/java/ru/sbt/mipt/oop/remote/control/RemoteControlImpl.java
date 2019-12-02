package ru.sbt.mipt.oop.remote.control;

import rc.RemoteControl;
import ru.sbt.mipt.oop.remote.control.commands.Command;

import java.util.HashMap;

public class RemoteControlImpl implements RemoteControl {
    public final HashMap<String, Command> commands = new HashMap<>();
    public HashMap<String, Button> buttons = new HashMap<>();

    public void setCommand(String button, Command command) {
        commands.put(button, command);
        return;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        Command command = commands.get(buttonCode);
        if (command == null) return;
        command.execute();
    }

}
