package com.piggsoft.tower.core.decode;

import com.piggsoft.tower.core.data.Payload;
import io.netty.buffer.ByteBuf;

public interface IPayloadDecoder {
    Payload decode(ByteBuf buf) throws Exception ;
}
