package com.piggsoft.tower.core.event;

public class ConnectionEvent {
    public int num;

    public ConnectionEvent() {

    }

    public ConnectionEvent(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "ConnectionEvent{" +
                "num=" + num +
                '}';
    }
}
