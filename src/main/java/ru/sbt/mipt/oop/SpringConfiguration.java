package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.event.handlers.*;
import ru.sbt.mipt.oop.event.handlers.decorators.AlarmStateDecorator;
import ru.sbt.mipt.oop.notifiers.Notifier;
import ru.sbt.mipt.oop.notifiers.SMSNotification;
import ru.sbt.mipt.oop.reading.utils.SmartHomeReader;
import ru.sbt.mipt.oop.reading.utils.SmartHomeReaderJSON;
import ru.sbt.mipt.oop.event.handlers.SensorEventAdapter;
import ru.sbt.mipt.oop.remote.control.RemoteControlImpl;
import ru.sbt.mipt.oop.remote.control.commands.*;
import ru.sbt.mipt.oop.sensor.event.factories.DoorEventFactory;
import ru.sbt.mipt.oop.sensor.event.factories.LightEventFactory;
import ru.sbt.mipt.oop.sensor.event.factories.SensorEventFactory;
import ru.sbt.mipt.oop.smart.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.SmartHome;

import java.util.*;

import static ru.sbt.mipt.oop.sensor.event.types.DoorActionType.CLOSE;
import static ru.sbt.mipt.oop.sensor.event.types.DoorActionType.OPEN;
import static ru.sbt.mipt.oop.sensor.event.types.LightActionType.OFF;
import static ru.sbt.mipt.oop.sensor.event.types.LightActionType.ON;


@Configuration
public class SpringConfiguration {

    @Bean
    SmartHomeReader smartHomeReader() {
        return new SmartHomeReaderJSON();
    }

    @Bean
    Alarm alarm() {
        return smartHome().getAlarm();
    }

    @Bean
    SmartHome smartHome() {
        return  smartHomeReader().read();
    }

    private Map<String, SensorEventFactory> eventFactories() {
        Map<String, SensorEventFactory> eventFactories = new HashMap<>();
        eventFactories.put("LightIsOn", new LightEventFactory(ON));
        eventFactories.put("LightIsOff", new LightEventFactory(OFF));
        eventFactories.put("DoorIsOpen", new DoorEventFactory(OPEN));
        eventFactories.put("DoorIsClosed", new DoorEventFactory(CLOSE));
        eventFactories.put("DoorIsLocked", new DoorEventFactory(CLOSE));
        eventFactories.put("DoorIsUnlocked", new DoorEventFactory(OPEN));
        return eventFactories;
    }

    @Bean
    SensorEventsManager sensorEventsManager() {
        SensorEventsManager manager = new SensorEventsManager();
        for (SensorEventHandler sensorEventHandler : sensorEventHandlers()) {
            manager.registerEventHandler(eventHandler(sensorEventHandler));
        }
        return manager;
    }

    EventHandler eventHandler(SensorEventHandler sensorEventHandler) {
        return new SensorEventAdapter(sensorEventHandler, eventFactories());
    }

    @Bean
    Notifier notifier() {
        return new SMSNotification();
    }

    @Bean
    List<SensorEventHandler> sensorEventHandlers(){
        List<SensorEventHandler> sensorEventHandlers = new ArrayList<>();
        sensorEventHandlers.add(hallDoorHandler());
        sensorEventHandlers.add(lightHandler());
        sensorEventHandlers.add(doorHandler());
        sensorEventHandlers.add(alarmHandler());
        return sensorEventHandlers;
    }

    @Bean
    SensorEventHandler hallDoorHandler() {
        return new AlarmStateDecorator(new HallDoorHandler(smartHome()), alarm(), notifier());
    }

    @Bean
    SensorEventHandler lightHandler() {
        return new AlarmStateDecorator(new LightHandler(smartHome()), alarm(), notifier());
    }

    @Bean
    SensorEventHandler doorHandler() {
        return new AlarmStateDecorator(new DoorHandler(smartHome()), alarm(), notifier());
    }

    @Bean
    SensorEventHandler alarmHandler() {
        return new AlarmHandler(smartHome());
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry() {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(remoteControl(), "remote1");
        return remoteControlRegistry;
    }


    @Bean
    RemoteControlImpl remoteControl() {
        RemoteControlImpl remoteControl = new RemoteControlImpl();
        remoteControl.setCommand("A", activateAlarmCommand());
        remoteControl.setCommand("B", closeEntranceDoorCommand());
        remoteControl.setCommand("C", triggerAlertCommand());
        remoteControl.setCommand("D", turnOffLightCommand());
        remoteControl.setCommand("1", turnOnHallLightCommand());
        remoteControl.setCommand("2", turnOnLightCommand());
        return remoteControl;
    }

    @Bean
    ActivateAlarmCommand activateAlarmCommand() {
        return new ActivateAlarmCommand(alarm(), "123456");
    }

    @Bean
    TriggerAlertCommand triggerAlertCommand() {
        return new TriggerAlertCommand(alarm());
    }

    @Bean
    CloseEntranceDoorCommand closeEntranceDoorCommand() {
        return new CloseEntranceDoorCommand(smartHome());
    }

    @Bean
    TurnOffLightCommand turnOffLightCommand() {
        return new TurnOffLightCommand(smartHome());
    }

    @Bean
    TurnOnLightCommand turnOnLightCommand() {
        return new TurnOnLightCommand(smartHome());
    }

    @Bean
    TurnOnHallLightCommand turnOnHallLightCommand() {
        return new TurnOnHallLightCommand(smartHome());
    }
}

