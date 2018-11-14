package com.piggsoft.tower.core;

import com.google.common.eventbus.EventBus;

public class Context {

    private EventBus eventBus;

    public void init() {
        if (eventBus == null) {
            eventBus = new EventBus("tower");
        }
    }

}
