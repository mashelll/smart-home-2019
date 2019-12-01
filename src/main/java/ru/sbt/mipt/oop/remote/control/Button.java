package ru.sbt.mipt.oop.remote.control;

import ru.sbt.mipt.oop.remote.control.commands.Command;

public class Button {

    private final String buttonName;
    private Command command;

    public Button(String buttonName) {
        this.buttonName = buttonName;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
