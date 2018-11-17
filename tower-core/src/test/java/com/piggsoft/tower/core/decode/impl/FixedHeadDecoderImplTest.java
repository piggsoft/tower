package com.piggsoft.tower.core.decode.impl;

import com.piggsoft.tower.core.data.DataUtils;
import com.piggsoft.tower.core.data.FixedHead;
import com.piggsoft.tower.core.decode.IFixedHeadDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

public class FixedHeadDecoderImplTest {

    @Test
    public void decode() throws Exception {
        ByteBuf buf = Unpooled.buffer(8);
        buf.writeByte(0x10);
        DataUtils.encodeRemainLength(buf, 2566);
        IFixedHeadDecoder decoder = new FixedHeadDecoderImpl();
        FixedHead fh = decoder.decode(buf);

        System.out.println(fh);
        System.out.println(buf.getByte(0));
        System.out.println(buf.getByte(1));
        System.out.println(buf.getByte(2));
    }


    @Test
    public void decode100W() throws Exception {
        ByteBuf buf = Unpooled.buffer(8);
        buf.writeByte(0x10);
        DataUtils.encodeRemainLength(buf, 2566);
        IFixedHeadDecoder decoder = new FixedHeadDecoderImpl();
        int times = 100 * 10000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            buf.resetReaderIndex();
            FixedHead fh = decoder.decode(buf);
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("Cycle " + times + " times use time " + time + "ms");

    }
}