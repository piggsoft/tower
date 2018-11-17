package com.piggsoft.tower.core.decode.impl;

import com.piggsoft.tower.core.data.DataUtils;
import com.piggsoft.tower.core.data.FixedHead;
import com.piggsoft.tower.core.decode.IFixedHeadDecoder;
import com.piggsoft.tower.core.exception.PacketErrorException;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.piggsoft.tower.core.data.HeadCtrlCode.*;

public class FixedHeadDecoderImpl implements IFixedHeadDecoder {

    private static final Logger logger = LoggerFactory.getLogger(FixedHeadDecoderImpl.class);

    @Override
    public FixedHead decode(ByteBuf buf) throws Exception {
        //第一个byte
        byte byte1 = buf.readByte();

        int ctlType = DataUtils.getH4Bit(byte1);
        validateHeadCtl(ctlType);

        int ctlFlags = DataUtils.getL4Bit(byte1);
        validateHeadFlags(ctlType, ctlFlags);

        int remainingLength = DataUtils.decodeRemainLength(buf);


        FixedHead fixedHead = new FixedHead(ctlType, ctlFlags, remainingLength);
        fixedHead.setRemainingLength(remainingLength);
        return fixedHead;
    }

    private void validateHeadFlags(int ctlType, int ctlFlags) throws PacketErrorException {
        boolean checkFlag = false;
        switch (ctlType) {
            case HEAD_CONNECT:
                checkFlag = ctlFlags == 0;
                break;
            case HEAD_CONNACK:
                checkFlag = ctlFlags == 0;
                break;
            case HEAD_PUBLISH:
                checkFlag = true;
                break;
            case HEAD_PUBACK:
                checkFlag = ctlFlags == 0;
                break;
            case HEAD_PUBREC:
                checkFlag = ctlFlags == 0;
                break;
            case HEAD_PUBREL:
                checkFlag = ctlFlags == 2;
                break;
            case HEAD_PUBCOMP:
                checkFlag = ctlFlags == 0;
                break;
            case HEAD_SUBSCRIBE:
                checkFlag = ctlFlags == 2;
                break;
            case HEAD_SUBACK:
                checkFlag = ctlFlags == 0;
                break;
            case HEAD_UNSUBSCRIBE:
                checkFlag = ctlFlags == 2;
                break;
            case HEAD_UNSUBACK:
                checkFlag = ctlFlags == 0;
                break;
            case HEAD_PINGREQ:
                checkFlag = ctlFlags == 0;
                break;
            case HEAD_PINGRESP:
                checkFlag = ctlFlags == 0;
                break;
            case HEAD_DISCONNECT:
                checkFlag = ctlFlags == 0;
                break;
        }
        if (!checkFlag) throw new PacketErrorException("fixed head ctrl flags is wrong");
    }

    private void validateHeadCtl(int ctl) throws PacketErrorException {
        if (ctl == HEAD_RESERVED || ctl == HEAD_RESERVED_END) {
            logger.warn("fixed head ctrl {} is reserved", ctl);
            throw new PacketErrorException("fixed head ctrl is reserved");
        }
    }
}
