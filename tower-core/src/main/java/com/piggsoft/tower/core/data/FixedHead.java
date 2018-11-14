package com.piggsoft.tower.core.data;

import io.netty.buffer.ByteBuf;

/**
 * @author piggsoft
 * @version 1.0
 * @create 2018/11/13
 * @since 1.0
 */
public class FixedHead {

    private int ctlType;
    private int ctlFlags;
    private int remainingLength;

    public static FixedHead read(ByteBuf buf) {
        //第一个byte
        byte byte1 = buf.readByte();
        FixedHead fixedHead = new FixedHead();
        fixedHead.setCtlType(DataUtils.getH4Bit(byte1));
        fixedHead.setCtlFlags(DataUtils.getL4Bit(byte1));
        fixedHead.setRemainingLength(DataUtils.decodeRemainLength(buf));
        return fixedHead;
    }

    public int getCtlType() {
        return ctlType;
    }

    public void setCtlType(int ctlType) {
        this.ctlType = ctlType;
    }

    public int getCtlFlags() {
        return ctlFlags;
    }

    public void setCtlFlags(int ctlFlags) {
        this.ctlFlags = ctlFlags;
    }

    public int getRemainingLength() {
        return remainingLength;
    }

    public void setRemainingLength(int remainingLength) {
        this.remainingLength = remainingLength;
    }
}
