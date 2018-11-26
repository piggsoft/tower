package com.piggsoft.tower.core.decode.impl;

import com.piggsoft.tower.core.data.DataUtils;
import com.piggsoft.tower.core.data.Payload;
import com.piggsoft.tower.core.decode.IPayloadDecoder;
import io.netty.buffer.ByteBuf;

public class PayloadDecoderImpl implements IPayloadDecoder {
    @Override
    public Payload decode(ByteBuf buf) throws Exception  {
        int clientIdLength = DataUtils.readLength(buf);
        CharSequence clientId = DataUtils.readData(buf, clientIdLength);




        return null;
    }
}
