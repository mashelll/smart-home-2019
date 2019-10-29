package ru.sbt.mipt.oop.event_handlers.decorators;

import ru.sbt.mipt.oop.event_handlers.EventHandler;
import ru.sbt.mipt.oop.sensor_event.SensorEvent;

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
