package com.piggsoft.tower.core.data;


import com.piggsoft.tower.core.exception.PacketErrorException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
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
    public void decodeRemainLength() throws PacketErrorException {
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


        heapBuf.clear();
        System.out.println(heapBuf.readableBytes());
        System.out.println(Integer.toHexString(heapBuf.getByte(0)));
        System.out.println(Integer.toHexString(heapBuf.getByte(1)));
        System.out.println(Integer.toHexString(heapBuf.getByte(3)));
        System.out.println(Integer.toHexString(heapBuf.getByte(4)));
        System.out.println(Integer.toHexString(heapBuf.getByte(5)));
        System.out.println(Integer.toHexString(heapBuf.getByte(6)));
        System.out.println(Integer.toHexString(heapBuf.getByte(7)));
    }

    @Test
    public void readCharSequence() {
        ByteBuf buf = Unpooled.buffer(8);
        buf.writeByte(0b01001101)
                .writeByte(0b01010001)
                .writeByte(0b01010100)
                .writeByte(0b01010100);

        buf.writeCharSequence("哈哈哈哈", CharsetUtil.UTF_8);

        CharSequence cs = buf.readCharSequence(buf.readableBytes(), CharsetUtil.UTF_8);
        System.out.println(cs);
    }

}