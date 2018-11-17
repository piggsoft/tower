package com.piggsoft.tower.core.decode;

import com.piggsoft.tower.core.data.FixedHead;
import io.netty.buffer.ByteBuf;

public interface IFixedHeadDecoder {

    FixedHead decode(ByteBuf buf) throws Exception ;

}
