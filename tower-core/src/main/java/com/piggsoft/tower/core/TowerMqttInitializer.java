package com.piggsoft.tower.core;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author piggsoft
 * @version 1.0
 * @create 2018/11/29
 * @since 1.0
 */
public class TowerMqttInitializer extends ChannelInitializer<SocketChannel> {

    private boolean netty_logger = true;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        if (netty_logger) {
            pipeline.addLast(LoggingHandler.class.getName(), new LoggingHandler(LogLevel.DEBUG));
        }

    }
}
