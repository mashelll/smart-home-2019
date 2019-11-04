package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.event_handlers.EventHandler;
import ru.sbt.mipt.oop.reading_utils.SmartHomeReader;
import ru.sbt.mipt.oop.reading_utils.SmartHomeReaderJSON;
import ru.sbt.mipt.oop.sensor_event.SensorEventAdapter;
import ru.sbt.mipt.oop.smart_devices.alarm.Alarm;
import ru.sbt.mipt.oop.smart_home.SmartHome;

@Configuration
public class SpringConfiguration {

        @Bean
        SmartHomeReader smartHomeReader() {
            return new SmartHomeReaderJSON();
        }

        @Bean
        Alarm alarm(String alarmId, int alarmCode) {
            return new Alarm(alarmId, alarmCode);
        }

        @Bean
        SmartHome smartHome() {
            return  smartHomeReader().read();
        }

        @Bean
        Executor executor() {
            return new SensorEventAdapter();
        }


}
