package com.piggsoft.tower.core.data;

import com.google.common.base.Verify;
import com.piggsoft.tower.core.exception.PacketErrorException;
import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;

import java.util.Arrays;

/**
 * @author piggsoft
 * @version 1.0
 * @create 2018/11/13
 * @since 1.0
 */
public class DataUtils {

    /**
     * 延续位, 最高位为1，代表后续还有
     */
    public static final int CONTINUATION_BIT_VALUE = 128;
    /**
     * 每个byte长度的最大值
     */
    public static final int LENGTH_MAX_VALUE = 127;

    /**
     * 读取剩余长度 Remaining Length。 剩余长度（Remaining Length）表示当前报文剩余部分的字节数，包括可变报头和负载的数据。
     * 剩余长度不包括用于编码剩余长度字段本身的字节数。 剩余长度字段使用一个变长度编码方案，对小于128的值它使用单字节编码。
     * 更大的值按下面的方式处理。低7位有效位用于编码数据，最高有效位用于指示是否有更多的字节。
     * 因此每个字节可以编码128个数值和一个延续位（continuation bit）。剩余长度字段最大4个字节。
     *
     * @param out
     * @param length
     */
    public static void encodeRemainLength(ByteBuf out, int length) {
        int l = length;
        do {
            int encodedByte = l % CONTINUATION_BIT_VALUE;
            l = l / CONTINUATION_BIT_VALUE;
            if (l > 0) {
                encodedByte = encodedByte | CONTINUATION_BIT_VALUE;
            }
            out.writeByte(intToByte(encodedByte));
        } while (l > 0);
    }

    /**
     * 读取剩余长度 Remaining Length。 剩余长度（Remaining Length）表示当前报文剩余部分的字节数，包括可变报头和负载的数据。
     * 剩余长度不包括用于编码剩余长度字段本身的字节数。 剩余长度字段使用一个变长度编码方案，对小于128的值它使用单字节编码。
     * 更大的值按下面的方式处理。低7位有效位用于编码数据，最高有效位用于指示是否有更多的字节。
     * 因此每个字节可以编码128个数值和一个延续位（continuation bit）。剩余长度字段最大4个字节。
     *
     * @param in
     */
    public static int decodeRemainLength(ByteBuf in) throws PacketErrorException {
        int multiplier = 1;
        int value = 0;
        byte encodedByte;
        do {
            encodedByte = in.readByte();
            value += (encodedByte & LENGTH_MAX_VALUE) * multiplier;
            multiplier *= CONTINUATION_BIT_VALUE;
            if (multiplier > CONTINUATION_BIT_VALUE * CONTINUATION_BIT_VALUE * CONTINUATION_BIT_VALUE) {
                throw new PacketErrorException("wrong head length");
            }
        } while ((encodedByte & CONTINUATION_BIT_VALUE) != 0);
        return value;
    }

    public static byte intToByte(int i) {
        if (i > 0xFF) {
            throw new IllegalArgumentException("数字太大, {" + i + "}");
        }
        return (byte) (i & 0xFF);
    }

    public static double log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }

    /**
     * 获取最高位
     *
     * @param data
     * @return
     */
    public static int getTopBit(byte data) {
        return (data & 0x80) >> 7;
    }

    /**
     * 获取除了最高位之外的7位
     *
     * @param data
     * @return
     */
    public static int getBitExcludeTop(byte data) {
        return (data & 0x7F);
    }

    /**
     * 获取高4位
     *
     * @param data
     * @return
     */
    public static int getH4Bit(byte data) {
        return (data & 0xF0) >> 4;
    }

    /**
     * 获取低4位
     *
     * @param data
     * @return
     */
    public static int getL4Bit(byte data) {
        return (data & 0x0F);
    }

    public static String byteToString(byte b) {
        return Integer.toHexString(0xFF & b);
    }

    public static String bytesToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(byteToString(b));
        }
        return sb.toString();
    }

    /**
     * 将一个byte的高4位和低四位进行交换
     *
     * @param b
     * @return
     */
    public static int exchange(byte b) {
        return (byte) getL4Bit(b) & 0xFF | (getH4Bit(b) & 0xFF) << 4;
    }


    /**
     * 要两个字节的顺序是MSB(Most Significant Bit) LSB(Last/Least Significant Bit)，翻译成中文就是，最高有效位，最低有效位。
     * 最高有效位在最低有效位左边/上面，表示这是一个大端字节/网络字节序，符合人的阅读习惯，高位在最左边。
     * 但凡如此表示的，都可以视为一个16位无符号short类型整数，两个字节表示。在JAVA中处理比较简单：
     *
     * @param buf
     * @return
     */
    public static int readLength(ByteBuf buf) {
        return buf.readUnsignedShort();
    }

    public static void checkLength(ByteBuf buf, int length)  {
        checkLength(buf, length, "the readable length is less then " + length);
    }

    public static void checkLength(ByteBuf buf, int length, String message, Object... args)  {
        Verify.verify(buf.readableBytes() >= length, message, args);
    }

    public static CharSequence readData(ByteBuf buf, int length) {
        CharSequence cs = buf.readCharSequence(length, CharsetUtil.UTF_8);
        return cs;
    }

}


