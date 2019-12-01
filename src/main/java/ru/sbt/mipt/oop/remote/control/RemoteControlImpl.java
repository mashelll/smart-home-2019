package ru.sbt.mipt.oop.remote.control;

import rc.RemoteControl;
import ru.sbt.mipt.oop.remote.control.commands.Command;

import java.util.HashMap;

public class RemoteControlImpl implements RemoteControl {
    private final HashMap<Button, Command> commands = new HashMap<>();
    private HashMap<String, Button> buttons = new HashMap<>();

    public RemoteControlImpl(HashMap<String, Button> buttons) {
        this.buttons = buttons;

    }

    public void setCommand(String button, Command command) {
        if (!buttons.containsKey(button)) return;
        commands.put(buttons.get(button), command);
        return;
    }

    public Command getCommand(String button) {
        return commands.get(button);
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (!buttons.containsKey(buttonCode)) return;
        Command command = commands.get(buttonCode);
        if (command == null) return;
        command.execute();
    }

}
