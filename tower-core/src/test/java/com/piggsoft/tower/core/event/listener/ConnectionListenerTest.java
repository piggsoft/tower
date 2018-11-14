package com.piggsoft.tower.core.event.listener;

import com.google.common.eventbus.EventBus;
import com.piggsoft.tower.core.event.ConnectionEvent;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ConnectionListenerTest {

    @Test
    public void onEvent() {
        EventBus bus = new EventBus("process");
        bus.register(new ConnectionListener());
        bus.post(new ConnectionEvent());

    }

    @Test
    public void onEventMulti() {
        for(int i = 0; i < 100; i++) {
            EventBus bus = new EventBus("process");
            bus.register(new ConnectionListener());
            bus.post(new ConnectionEvent());
        }

    }

    @Test
    public void onEventMultiThread() throws IOException {
        EventBus bus = new EventBus("process");
        bus.register(new ConnectionListener());
        for(int i = 0; i < 100; i++) {
            final int index = i + 1;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    bus.post(new ConnectionEvent(index));
                }
            }).start();
        }
        System.in.read();
    }
}