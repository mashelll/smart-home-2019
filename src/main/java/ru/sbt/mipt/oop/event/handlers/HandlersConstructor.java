package ru.sbt.mipt.oop.event.handlers;
import ru.sbt.mipt.oop.event.handlers.decorators.AlarmStateDecorator;
import ru.sbt.mipt.oop.notifiers.Notifier;
import ru.sbt.mipt.oop.notifiers.SMSNotification;
import ru.sbt.mipt.oop.smart.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.SmartHome;

import java.util.ArrayList;
import java.util.List;

public class HandlersConstructor {
    public static List<SensorEventHandler> constructHandlers(SmartHome smartHome) {
        List<SensorEventHandler> handlers = new ArrayList<>();
        Notifier notifier = new SMSNotification();
        Alarm alarm = smartHome.getAlarm();
        handlers.add(new AlarmStateDecorator(new LightHandler(smartHome), alarm, notifier));
        handlers.add(new AlarmStateDecorator(new DoorHandler(smartHome), alarm, notifier));
        handlers.add(new AlarmStateDecorator(new HallDoorHandler(smartHome), alarm, notifier));
        handlers.add(new AlarmHandler(smartHome));
        return handlers;
    }
}
