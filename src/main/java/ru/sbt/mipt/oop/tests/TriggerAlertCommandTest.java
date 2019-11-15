package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.remote.control.commands.TriggerAlertCommand;
import ru.sbt.mipt.oop.smart.devices.alarm.Alarm;
import ru.sbt.mipt.oop.smart.devices.alarm.Alert;
import ru.sbt.mipt.oop.smarthome.SmartHome;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TriggerAlertCommandTest {

    @Test
    public void executeTest() {
        String code = "default";
        Alarm alarm = new Alarm("1234", code);

        TriggerAlertCommand triggerAlertCommand = new TriggerAlertCommand(alarm);
        triggerAlertCommand.execute();
        assertThat(alarm.getState(), instanceOf(Alert.class));
    }

}