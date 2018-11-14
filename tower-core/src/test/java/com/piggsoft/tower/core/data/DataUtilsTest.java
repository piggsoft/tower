package com.piggsoft.tower.core.data;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

public class DataUtilsTest {

    @Test
    public void encodeRemainLength() {
        ByteBuf heapBuf = Unpooled.buffer(8);
        DataUtils.encodeRemainLength(heapBuf, 127);
        System.out.println(DataUtils.bytesToString(heapBuf.array()));
        heapBuf.clear();
        DataUtils.encodeRemainLength(heapBuf, 16383);
        System.out.println(DataUtils.bytesToString(heapBuf.array()));
        heapBuf.clear();
        DataUtils.encodeRemainLength(heapBuf, 2097151);
        System.out.println(DataUtils.bytesToString(heapBuf.array()));
        heapBuf.clear();
        DataUtils.encodeRemainLength(heapBuf, 268435455);
        System.out.println(DataUtils.bytesToString(heapBuf.array()));
        heapBuf.clear();
    }


    @Test
    public void decodeRemainLength() {
        ByteBuf heapBuf = Unpooled.buffer(8);
        DataUtils.encodeRemainLength(heapBuf, 127);
        /*
        System.out.println(DataUtils.bytesToString(heapBuf.array()));
        heapBuf.clear();*/
        System.out.println(DataUtils.decodeRemainLength(heapBuf));
    }

    @Test
    public void exchange() {
        System.out.println(Integer.toHexString(DataUtils.exchange((byte) 0xF6)));
        System.out.println(Integer.toHexString(DataUtils.exchange((byte) 0x66)));
        System.out.println(Integer.toHexString(DataUtils.exchange((byte) 0xF5)));
        System.out.println(Integer.toHexString(DataUtils.exchange((byte) 0x4F)));
    }

    @Test
    public void readVariableHeaderLength() {
        ByteBuf heapBuf = Unpooled.buffer(8);
        heapBuf.writeByte(0x00).writeByte(0x40);
        System.out.println(Integer.toHexString(DataUtils.readVariableHeaderLength(heapBuf)));

        heapBuf.writeByte(0xF0).writeByte(0x40);
        System.out.println(Integer.toHexString(DataUtils.readVariableHeaderLength(heapBuf)));
    }

}