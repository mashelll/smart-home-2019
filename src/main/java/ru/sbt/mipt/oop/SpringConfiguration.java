package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.reading.utils.SmartHomeReader;
import ru.sbt.mipt.oop.reading.utils.SmartHomeReaderJSON;
import ru.sbt.mipt.oop.sensor.event.SensorEventAdapter;
import ru.sbt.mipt.oop.smart.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.SmartHome;

@Configuration
public class SpringConfiguration {

        @Bean
        SmartHomeReader smartHomeReader() {
            return new SmartHomeReaderJSON();
        }

        @Bean
        Alarm alarm(String alarmId, String alarmCode) {
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
