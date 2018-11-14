package com.piggsoft.tower.core.controller;

import com.piggsoft.tower.core.Context;
import com.piggsoft.tower.core.data.FixedHead;
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

    private Context context;

    public Ctrl() {}

    public Ctrl(Context context) {
        this.context = context;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        FixedHead head = FixedHead.read(buf);



        super.channelRead(ctx, msg);
    }

}
