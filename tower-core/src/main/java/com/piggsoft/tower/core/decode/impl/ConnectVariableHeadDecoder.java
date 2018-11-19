package com.piggsoft.tower.core.decode.impl;

import com.piggsoft.tower.core.data.DataUtils;
import com.piggsoft.tower.core.data.FixedHead;
import com.piggsoft.tower.core.data.HeadCtrlCode;
import com.piggsoft.tower.core.data.VariableHead;
import com.piggsoft.tower.core.data.connect.ConnectionVariableHeadBuilder;
import com.piggsoft.tower.core.decode.IVariableHeadDecoder;
import com.piggsoft.tower.core.exception.PacketErrorException;
import io.netty.buffer.ByteBuf;

import java.util.concurrent.ExecutionException;

public class ConnectVariableHeadDecoder implements IVariableHeadDecoder {

    private static final int PROTOCOL_NAME_LENGTH = 4;
    private static final int PROTOCOL_LEVEL = 4;
    private static final String PROTOCOL_NAME = "MQTT";
    private static final int CONNECT_FLAGS_RESERVED = 0;

    @Override
    public int getCtl() {
        return HeadCtrlCode.HEAD_CONNECT;
    }

    @Override
    public VariableHead decode(FixedHead fixedHead, ByteBuf buf) throws Exception {

        ConnectionVariableHeadBuilder builder = ConnectionVariableHeadBuilder.create();

        DataUtils.checkLength(buf, 2, "not enough length(%s) to decode protocolNameLength", 2);
        int protocolNameLength = DataUtils.readLength(buf);

        DataUtils.checkLength(buf, protocolNameLength, "not enough length(%s) to decode protocolName", protocolNameLength);
        CharSequence protocolName = DataUtils.readData(buf, protocolNameLength);

        DataUtils.checkLength(buf, 1, "not enough length(%s) to decode protocolLevel", 1);
        int protocolLevel = buf.readByte();

        DataUtils.checkLength(buf, 1, "not enough length(%s) to decode connectFlags", 1);
        byte connectFlags = buf.readByte();



        readConnectFlags(buf.readByte(), builder);




        return null;
    }

    private void readConnectFlags(Byte b, ConnectionVariableHeadBuilder builder) throws PacketErrorException, ExecutionException {
        //username flag
        builder.usernameFlag((b >> 7) & 0x01)
                .passwordFlag((b >> 6) & 0x01)
                .willRetain((b >> 5) & 0x01)
                .willQos((b >> 4) & 0x03)
                .willFlag((b >> 2) & 0x01)
                .cleanSession((b >> 1) & 0x01)
                .reserved(b & 0x01);
    }
}
