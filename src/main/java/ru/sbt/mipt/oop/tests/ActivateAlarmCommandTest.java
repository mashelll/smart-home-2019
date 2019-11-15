package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.remote.control.commands.ActivateAlarmCommand;
import ru.sbt.mipt.oop.smart.devices.alarm.Activated;
import ru.sbt.mipt.oop.smart.devices.alarm.Alarm;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

class ActivateAlarmCommandTest {
    @Test
    public void executeTest() {
        String code = "default";
        Alarm alarm = new Alarm("1234", code);
        ActivateAlarmCommand activateAlarmCommand = new ActivateAlarmCommand(alarm, code);
        activateAlarmCommand.execute();
        assertThat(alarm.getState(), instanceOf(Activated.class));
    }

}