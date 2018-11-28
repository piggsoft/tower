package com.piggsoft.tower.core.protocol;

import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.MqttMessage;

public abstract class Protocol {

    /**
     *
     * @param channel
     * @param msg
     */
    public void process(Channel channel, MqttMessage msg) {
        if (msg.decoderResult().isFailure()) {
            MqttMessage ack = onFailure(msg.decoderResult().cause());
            if (ack != null) {
                channel.writeAndFlush(ack);
            }
            channel.close();
        }
        MqttMessage ack = doProcess(msg);
        channel.writeAndFlush(ack);
    }

    /**
     * 处理消息
     * @param msg
     * @return ack message
     */
    protected abstract MqttMessage doProcess(MqttMessage msg);

    /**
     * 消息解码出现异常
     * @param cause
     * @return error ack message, can be null
     */
    protected abstract MqttMessage onFailure(Throwable cause);

}
