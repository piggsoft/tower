package com.piggsoft.tower.core.decode.impl;

import com.google.common.collect.ConcurrentHashMultiset;
import com.piggsoft.tower.core.data.DataUtils;
import com.piggsoft.tower.core.data.FixedHead;
import com.piggsoft.tower.core.data.HeadCtrlCode;
import com.piggsoft.tower.core.data.VariableHead;
import com.piggsoft.tower.core.decode.IVariableHeadDecoder;
import com.piggsoft.tower.core.exception.PacketErrorException;
import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static com.piggsoft.tower.core.Context.CACHE;

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
    public VariableHead decode(FixedHead fixedHead, ByteBuf buf) throws Exception  {
        int length = DataUtils.readVariableHeaderLength(buf);
        if (PROTOCOL_NAME_LENGTH != length) {

        }
        CharSequence cs = buf.readCharSequence(length, CharsetUtil.UTF_8);
        if (!PROTOCOL_NAME.equals(cs.toString())) {

        }
        int protocolLevel = buf.readByte();
        if (PROTOCOL_LEVEL != protocolLevel) {

        }
        byte connectFlags = buf.readByte();



        return null;
    }

    private int[] readConnectFlags(Byte b) throws PacketErrorException, ExecutionException {
        int[] r = new int[6];
        //username flag
        r[0] = (b >> 7) & 0x01;
        //password flag
        r[1] = (b >> 6) & 0x01;
        //will retain
        r[2] = (b >> 5) & 0x01;
        //will qos
        r[3] = (b >> 4) & 0x03;
        //will flag
        r[4] = (b >> 2) & 0x01;
        //clean session
        r[5] = (b >> 1) & 0x01;
        int reserved = b & 0x01;
        if (CONNECT_FLAGS_RESERVED != reserved) {
            throw new PacketErrorException("the Connect Flags bit 0 must is 0");
        }
        CACHE.get("1", () -> ConcurrentHashMultiset.create());
        return r;
    }

    public static void main(String[] args) throws PacketErrorException, ExecutionException {
        System.out.println(Arrays.toString(new ConnectVariableHeadDecoder().readConnectFlags((byte) 0b11100110)));
    }
}
