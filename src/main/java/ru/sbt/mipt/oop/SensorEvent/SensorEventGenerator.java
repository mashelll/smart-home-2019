package ru.sbt.mipt.oop.SensorEvent;


import static java.lang.StrictMath.abs;

public class SensorEventGenerator implements SensorEventGetter{
    @Override
    public SensorEvent getNextSensorEvent() {
        SensorEvent event;
        String objectId = "" + (((int) (Math.random() * 100)) % 10);
        int actionType = (((int) (Math.random() * 100)) % 2);
        if (((int) (Math.random() * 100)) > 50) {
            event = new SensorEvent(SensorEventType.DOOR_EVENT, DoorActionType.values()[actionType], objectId);
        } else {
            event = new SensorEvent(SensorEventType.LIGHT_EVENT, LightActionType.values()[actionType], objectId);
        }
        return event;
    }
}