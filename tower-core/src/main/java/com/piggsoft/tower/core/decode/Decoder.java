package com.piggsoft.tower.core.decode;

import com.piggsoft.tower.core.data.FixedHead;
import com.piggsoft.tower.core.data.Payload;
import com.piggsoft.tower.core.data.VariableHead;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.Map;

/**
 * @author piggsoft
 * @version 1.0
 * @create 2018/11/13
 * @since 1.0
 */
public class Decoder extends ByteToMessageDecoder {

    private IFixedHeadDecoder fixedHeadDecoder;
    private Map<Integer, IVariableHeadDecoder> variableHeadDecoders;
    private Map<Integer, IPayloadDecoder> payloadDecoders;


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        FixedHead fixedHead = fixedHeadDecoder.decode(in);
        VariableHead variableHead = variableHeadDecoders.get(fixedHead.getCtrlType()).decode(fixedHead, in);
        Payload payload = payloadDecoders.get(fixedHead.getCtrlType()).decode(in);
    }

}
