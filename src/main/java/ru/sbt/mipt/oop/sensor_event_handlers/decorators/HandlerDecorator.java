package ru.sbt.mipt.oop.sensor_event_handlers.decorators;

import ru.sbt.mipt.oop.sensor_event_handlers.SensorEventHandler;
import ru.sbt.mipt.oop.sensor_event.SensorEvent;

public class HandlerDecorator implements SensorEventHandler {
    private final SensorEventHandler wrapped;

    HandlerDecorator(SensorEventHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        wrapped.handleEvent(event);
    }


}
