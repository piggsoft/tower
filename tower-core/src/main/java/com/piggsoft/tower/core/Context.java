package com.piggsoft.tower.core;

import com.google.common.cache.Cache;
import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.eventbus.EventBus;

public class Context {

    public static Cache<String, ConcurrentHashMultiset> CACHE;

    private EventBus eventBus;

    public void init() {
        if (eventBus == null) {
            eventBus = new EventBus("tower");

        }
    }

}
