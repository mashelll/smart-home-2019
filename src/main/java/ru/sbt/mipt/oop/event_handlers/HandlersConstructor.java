package ru.sbt.mipt.oop.event_handlers;
import ru.sbt.mipt.oop.smart_home.SmartHome;

import java.util.ArrayList;
import java.util.List;

public class HandlersConstructor {
    public static List<EventHandler> constructHandlers(SmartHome smartHome) {
        List<EventHandler> handlers = new ArrayList<>();

        handlers.add(new LightHandler(smartHome));
        handlers.add(new DoorHandler(smartHome));
        handlers.add(new HallDoorHandler(smartHome));
        handlers.add(new AlarmHandler(smartHome));
        return handlers;
    }
}
