package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.sensor_event_handlers.*;
import ru.sbt.mipt.oop.reading_utils.SmartHomeReader;
import ru.sbt.mipt.oop.reading_utils.SmartHomeReaderJSON;
import ru.sbt.mipt.oop.sensor_event.SensorEventAdapter;
import ru.sbt.mipt.oop.sensor_event_handlers.decorators.AlarmStateDecorator;
import ru.sbt.mipt.oop.smart_devices.alarm.Alarm;
import ru.sbt.mipt.oop.smart_home.SmartHome;

import java.util.ArrayList;
import java.util.Collection;
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
    List<SensorEventHandler> eventHandlers (){
        List<SensorEventHandler> eventHandlers = new ArrayList<>();
        Alarm alarm = smartHome().getAlarm();
        eventHandlers.add(new AlarmStateDecorator(new LightHandler(smartHome()), alarm));
        eventHandlers.add(new AlarmStateDecorator(new DoorHandler(smartHome()), alarm));
        eventHandlers.add(new AlarmStateDecorator(new HallDoorHandler(smartHome()), alarm));
        eventHandlers.add(new AlarmHandler(smartHome()));
        return eventHandlers;
    }

    @Bean
    SensorEventHandler hallDoorHandler() {
        return new AlarmStateDecorator(new HallDoorHandler(smartHome()), alarm());
    }

    @Bean
    SensorEventHandler LightHandler() {
        return new AlarmStateDecorator(new LightHandler(smartHome()), alarm());
    }

    @Bean
    SensorEventHandler DoorHandler() {
        return new AlarmStateDecorator(new DoorHandler(smartHome()), alarm());
    }

    @Bean
    SensorEventHandler AlarmHandler() {
        return new AlarmHandler(smartHome());
    }
}
