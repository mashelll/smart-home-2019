package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.event.handlers.*;
import ru.sbt.mipt.oop.event.handlers.decorators.AlarmStateDecorator;
import ru.sbt.mipt.oop.notifiers.Notifier;
import ru.sbt.mipt.oop.notifiers.SMSNotification;
import ru.sbt.mipt.oop.reading.utils.SmartHomeReader;
import ru.sbt.mipt.oop.reading.utils.SmartHomeReaderJSON;
import ru.sbt.mipt.oop.event.handlers.SensorEventAdapter;
import ru.sbt.mipt.oop.remote.control.Button;
import ru.sbt.mipt.oop.remote.control.RemoteControlImpl;
import ru.sbt.mipt.oop.remote.control.commands.*;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.smart.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.SmartHome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



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

    @Bean
    SensorEventsManager sensorEventsManager() {
        SensorEventsManager manager = new SensorEventsManager();
        for (SensorEventHandler sensorEventHandler : eventHandlers()) {
            manager.registerEventHandler(new SensorEventAdapter(sensorEventHandler));
        }
        return manager;
    }

    @Bean
    Notifier notifier() {
        return new SMSNotification();
    }

    @Bean
    List<SensorEventHandler> eventHandlers (){
        List<SensorEventHandler> eventHandlers = new ArrayList<>();
        Alarm alarm = smartHome().getAlarm();
        eventHandlers.add(hallDoorHandler());
        eventHandlers.add(lightHandler());
        eventHandlers.add(doorHandler());
        eventHandlers.add(AlarmHandler());
        return eventHandlers;
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
    SensorEventHandler AlarmHandler() {
        return new AlarmHandler(smartHome());
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry() {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(remoteControl(), "remote1");
        return remoteControlRegistry;
    }

    @Bean
    private final HashMap<String, Button> buttons() {
        final HashMap<String, Button> buttons = new HashMap<>();
        buttons.put("A", new Button("A"));
        buttons.put("B", new Button("B"));
        buttons.put("C", new Button("C"));
        buttons.put("D", new Button("D"));
        buttons.put("1", new Button("1"));
        buttons.put("2", new Button("2"));
        buttons.put("3", new Button("3"));
        buttons.put("4", new Button("4"));
        return buttons;
    }


    @Bean
    RemoteControlImpl remoteControl() {
        RemoteControlImpl remoteControl = new RemoteControlImpl(buttons());
        remoteControl.setCommand("A", activateAlarmCommand());
        remoteControl.setCommand("B", closeEntranceDoorCommand());
        remoteControl.setCommand("C", triggerAlertCommand());
        remoteControl.setCommand("D", turnOffLightCommand());
        remoteControl.setCommand("1", turnOnHallLightCommand());
        remoteControl.setCommand("2", turnOnLightCommand());
        return remoteControl();
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

