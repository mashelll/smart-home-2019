package ru.sbt.mipt.oop.remote.control;

import rc.RemoteControl;
import ru.sbt.mipt.oop.remote.control.commands.Command;

import java.util.HashMap;

public class RemoteControlImpl implements RemoteControl {
    private final HashMap<Button, Command> commands = new HashMap<>();
    private final HashMap<String, Button> buttons = new HashMap<>();

    public RemoteControlImpl() {
        this.buttons.put("A", new Button("A"));
        this.buttons.put("B", new Button("B"));
        this.buttons.put("C", new Button("C"));
        this.buttons.put("D", new Button("D"));
        this.buttons.put("1", new Button("1"));
        this.buttons.put("2", new Button("2"));
        this.buttons.put("3", new Button("3"));
        this.buttons.put("4", new Button("4"));
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
