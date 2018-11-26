package com.piggsoft.tower.core.decode.impl;

import com.google.common.base.Verify;
import com.piggsoft.tower.core.data.DataUtils;
import com.piggsoft.tower.core.data.FixedHead;
import com.piggsoft.tower.core.data.HeadCtrlCode;
import com.piggsoft.tower.core.data.VariableHead;
import com.piggsoft.tower.core.data.connect.ConnectionVariableHeadBuilder;
import com.piggsoft.tower.core.decode.IVariableHeadDecoder;
import io.netty.buffer.ByteBuf;

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
        Verify.verify(PROTOCOL_NAME_LENGTH == protocolNameLength, "protocolNameLength is only support 4");

        DataUtils.checkLength(buf, protocolNameLength, "not enough length(%s) to decode protocolName", protocolNameLength);
        CharSequence protocolName = DataUtils.readData(buf, protocolNameLength);
        Verify.verify(PROTOCOL_NAME.equals(protocolName.toString()), "protocolName is only support '%s'", PROTOCOL_NAME);

        DataUtils.checkLength(buf, 1, "not enough length(%s) to decode protocolLevel", 1);
        int protocolLevel = buf.readByte();
        Verify.verify(PROTOCOL_LEVEL == protocolLevel, "protocol level is only support %s", PROTOCOL_LEVEL);

        DataUtils.checkLength(buf, 1, "not enough length(%s) to decode connectFlags", 1);
        byte connectFlags = buf.readByte();
        int usernameFlag = (connectFlags >> 7) & 0x01;
        int passwordFlag = (connectFlags >> 6) & 0x01;
        Verify.verify(usernameFlag == 0 ? passwordFlag == 0 : true, "when username flag is 0 then the password flag is must be 0");

        int willRetain = (connectFlags >> 5) & 0x01;
        int willQos = (connectFlags >> 4) & 0x03;
        int willFlag = (connectFlags >> 2) & 0x01;
        Verify.verify(willFlag == 0 ? willQos == 0 : true, "when will flag is 0 then the will qos is must be 0");
        Verify.verify(willFlag == 0 ? willRetain == 0 : true, "when will flag is 0 then the will retain is must be 0");


        Verify.verify(willFlag == 1 ? willQos != 3 : true, "when will flag is 1 then the will qos is must not be 3");

        int cleanSession = (connectFlags >> 1) & 0x01;
        int reserved = connectFlags & 0x01;

        int keepAlive = DataUtils.readLength(buf);

        builder.protocolNameLength(protocolNameLength)
                .protocolName(protocolName)
                .protocolLevel(protocolLevel);
        readConnectFlags(buf.readByte(), builder);


        return null;
    }

    private void readConnectFlags(Byte b, ConnectionVariableHeadBuilder builder) {
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
