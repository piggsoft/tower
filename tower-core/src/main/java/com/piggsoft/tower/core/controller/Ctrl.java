package com.piggsoft.tower.core.controller;

import com.piggsoft.tower.core.data.DataUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author piggsoft
 * @version 1.0
 * @create 2018/11/13
 * @since 1.0
 */
public class Ctrl extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte fixedHead = buf.readByte();
        int ctl = DataUtils.getH4Bit(fixedHead);

        int length = DataUtils.decodeRemainLength(buf);
        super.channelRead(ctx, msg);
    }

}
