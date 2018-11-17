package com.piggsoft.tower.core.data.connect;

import com.piggsoft.tower.core.data.DataUtils;
import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;

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

    public static ConnectionVariableHead read(ByteBuf buf) {
        VariableHeader vh = new VariableHeader();
        vh.length = DataUtils.readVariableHeaderLength(buf);
        CharSequence cs = buf.readCharSequence(vh.length, CharsetUtil.UTF_8);
        return null;
    }
}

class Payload {

}
