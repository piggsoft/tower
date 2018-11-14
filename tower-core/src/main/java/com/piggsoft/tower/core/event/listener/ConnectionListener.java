package com.piggsoft.tower.core.event.listener;

import com.google.common.eventbus.Subscribe;
import com.piggsoft.tower.core.event.ConnectionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionListener.class);

    @Subscribe
    public void onEvent(ConnectionEvent event) {
        LOGGER.info("on event: {}", event);
    }

}
