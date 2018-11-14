package com.piggsoft.tower.core.data.connect;

import com.piggsoft.tower.core.data.DataUtils;
import io.netty.buffer.ByteBuf;

public class ConnectionEvent {
}

class VariableHeader {

    private int length;
    private int usernameFlag;
    private int passwordFlag;
    private int willRetain;
    private int willQos;
    private int willFlag;
    private int cleanSession;
    private int reserved;

    public static ConnectionVariableHeader read(ByteBuf buf) {
        VariableHeader vh = new VariableHeader();
        vh.length = DataUtils.readVariableHeaderLength(buf);
        ByteBuf vhs = buf.readBytes(vh.length);
        return null;
    }
}

class Payload {

}
