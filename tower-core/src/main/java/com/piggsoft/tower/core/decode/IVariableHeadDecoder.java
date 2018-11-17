package com.piggsoft.tower.core.decode;

import com.piggsoft.tower.core.data.FixedHead;
import com.piggsoft.tower.core.data.VariableHead;
import io.netty.buffer.ByteBuf;

public interface IVariableHeadDecoder {
    int getCtl();
    VariableHead decode(FixedHead fixedHead, ByteBuf buf) throws Exception ;
}
