package com.piggsoft.tower.core.server;

import com.piggsoft.tower.core.config.TowProperties;
import com.piggsoft.tower.core.handler.TowerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class TowerServer {

    public static final Logger LOGGER = LoggerFactory.getLogger(TowerServer.class);

    @Autowired
    private TowProperties tp;

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    private Channel mqttChannel;


    @PostConstruct
    public void start() {
        LOGGER.info("tower server is starting...");
        bossGroup = tp.isUseEpoll() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        workerGroup = tp.isUseEpoll() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
    }

    private void mqttServer() throws Exception {
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(bossGroup, workerGroup)
                .channel(tp.isUseEpoll() ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                // handler在初始化时就会执行
                .handler(new LoggingHandler(LogLevel.INFO))
                // childHandler会在客户端成功connect后才执行
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline channelPipeline = socketChannel.pipeline();
                        // Netty提供的心跳检测
                        channelPipeline.addFirst("idle", new IdleStateHandler(0, 0, tp.getKeepAlive()));
                        // Netty提供的SSL处理
                        /*SSLEngine sslEngine = sslContext.newEngine(socketChannel.alloc());
                        sslEngine.setUseClientMode(false);        // 服务端模式
                        sslEngine.setNeedClientAuth(false);        // 不需要验证客户端
                        channelPipeline.addLast("ssl", new SslHandler(sslEngine));*/
                        channelPipeline.addLast("decoder", new MqttDecoder());
                        channelPipeline.addLast("encoder", MqttEncoder.INSTANCE);
                        channelPipeline.addLast("broker", new TowerHandler(protocolProcess));
                    }
                })
                .option(ChannelOption.SO_BACKLOG, tp.getSoBacklog())
                .childOption(ChannelOption.SO_KEEPALIVE, tp.isSoKeepAlive());
        mqttChannel = sb.bind(tp.getSslPort()).sync().channel();
    }

}
