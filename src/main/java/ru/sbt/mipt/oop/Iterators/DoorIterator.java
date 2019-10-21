package ru.sbt.mipt.oop.Iterators;

import java.util.Iterator;

import ru.sbt.mipt.oop.SmartDevices.Door;
import ru.sbt.mipt.oop.SmartHome.HomeObject;
import ru.sbt.mipt.oop.SmartHome.Room;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

public class DoorIterator implements ObjectIterator {
    private SmartHome smartHome;
    private Iterator<Room> roomIterator;
    private Iterator<Door> doorIterator;
    private Room currentRoom;
    private Door currentDoor;

    DoorIterator(SmartHome smartHome) {
        this.smartHome = smartHome;
        roomIterator = smartHome.getRooms().iterator();
        currentRoom = null;
        doorIterator = null;
        currentDoor = null;
    }

    @Override
    public boolean hasNext() {
        return (roomIterator.hasNext()) || (doorIterator.hasNext());
    }

    @Override
    public HomeObject getNext() {
        if (hasNext()){
            if((doorIterator!=null)&&(doorIterator.hasNext())){
                currentDoor = doorIterator.next();
                return currentDoor;
            }else {
                currentRoom = roomIterator.next();
                doorIterator = currentRoom.getDoors().iterator();
                currentDoor = doorIterator.next();
                return currentDoor;
            }
        }
        return null;
    }

    @Override
    public void reset() {
        roomIterator = smartHome.getRooms().iterator();
        currentRoom = null;
        doorIterator = null;
        currentDoor = null;
    }
}
