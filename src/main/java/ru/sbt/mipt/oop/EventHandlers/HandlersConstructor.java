package ru.sbt.mipt.oop.EventHandlers;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

import java.util.ArrayList;
import java.util.List;

public class HandlersConstructor {
    public static List<EventHandler> constructHandlers(SmartHome smartHome) {
        List<EventHandler> handlers = new ArrayList<>();

        handlers.add(new LightHandler(smartHome));
        handlers.add(new DoorHandler(smartHome));
        handlers.add(new HallDoorHandler(smartHome));
        return handlers;
    }
}
