package ru.sbt.mipt.oop.event.handlers.decorators;

import ru.sbt.mipt.oop.event.handlers.EventHandler;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;

public class HandlerDecorator implements EventHandler {
    private final EventHandler wrapped;

    HandlerDecorator(EventHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        wrapped.handleEvent(event);
    }

}