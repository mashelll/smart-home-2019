package ru.sbt.mipt.oop.Iterators;

import ru.sbt.mipt.oop.SmartHome.HomeObject;

public interface ObjectIterator {
    boolean hasNext();
    HomeObject getNext();
    void reset();
}
