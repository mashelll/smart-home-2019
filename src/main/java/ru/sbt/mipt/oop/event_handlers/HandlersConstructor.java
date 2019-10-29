package ru.sbt.mipt.oop.event_handlers;
import ru.sbt.mipt.oop.event_handlers.decorators.AlarmStateDecorator;
import ru.sbt.mipt.oop.smart_devices.alarm.Alarm;
import ru.sbt.mipt.oop.smart_home.SmartHome;

import java.util.ArrayList;
import java.util.List;

public class HandlersConstructor {
    public static List<EventHandler> constructHandlers(SmartHome smartHome) {
        List<EventHandler> handlers = new ArrayList<>();
        Alarm alarm = smartHome.getAlarm();
        handlers.add(new AlarmStateDecorator(new LightHandler(smartHome), alarm));
        handlers.add(new AlarmStateDecorator(new DoorHandler(smartHome), alarm));
        handlers.add(new AlarmStateDecorator(new HallDoorHandler(smartHome), alarm));
        handlers.add(new AlarmHandler(smartHome));
        return handlers;
    }
}
