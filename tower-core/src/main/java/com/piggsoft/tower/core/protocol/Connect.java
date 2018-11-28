package com.piggsoft.tower.core.protocol;


import io.netty.handler.codec.mqtt.MqttConnAckVariableHeader;
import io.netty.handler.codec.mqtt.MqttConnectMessage;
import io.netty.handler.codec.mqtt.MqttConnectPayload;
import io.netty.handler.codec.mqtt.MqttConnectReturnCode;
import io.netty.handler.codec.mqtt.MqttConnectVariableHeader;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttIdentifierRejectedException;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageFactory;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.netty.handler.codec.mqtt.MqttUnacceptableProtocolVersionException;

public class Connect extends Protocol {

    @Override
    protected MqttMessage doProcess(MqttMessage msg) {
        MqttConnectMessage message = (MqttConnectMessage) msg;
        MqttConnectVariableHeader vh = message.variableHeader();
        MqttConnectPayload payload = message.payload();


        return null;
    }

    @Override
    protected MqttMessage onFailure(Throwable cause) {
        MqttMessage connAckMessage = null;
        if (cause instanceof MqttUnacceptableProtocolVersionException) {
            // 不支持的协议版本
            connAckMessage = createError(MqttConnectReturnCode.CONNECTION_REFUSED_UNACCEPTABLE_PROTOCOL_VERSION);
        } else if (cause instanceof MqttIdentifierRejectedException) {
            // 不合格的clientId
            connAckMessage = createError(MqttConnectReturnCode.CONNECTION_REFUSED_IDENTIFIER_REJECTED);
        }
        return connAckMessage;
    }

    private MqttMessage createError(MqttConnectReturnCode returnCode) {
        return MqttMessageFactory.newMessage(
                new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 2),
                new MqttConnAckVariableHeader(returnCode, false), null);
    }
}
