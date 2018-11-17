package com.piggsoft.tower.core.data;

public class HeadCtrlCode {

    public static final int HEAD_RESERVED = 0;//禁止保留
    public static final int HEAD_CONNECT = 1;//客户端到服务端客户端请求连接服务端
    public static final int HEAD_CONNACK = 2;//服务端到客户端连接报文确认
    public static final int HEAD_PUBLISH = 3;//两个方向都允许发布消息
    public static final int HEAD_PUBACK = 4;//两个方向都允许QOS 1消息发布收到确认
    public static final int HEAD_PUBREC = 5;//两个方向都允许发布收到（保证交付第一步）
    public static final int HEAD_PUBREL = 6;//两个方向都允许发布释放（保证交付第二步）
    public static final int HEAD_PUBCOMP = 7;//两个方向都允许QOS 2消息发布完成（保证交互第三步）
    public static final int HEAD_SUBSCRIBE = 8;//客户端到服务端客户端订阅请求
    public static final int HEAD_SUBACK = 9;//服务端到客户端订阅请求报文确认
    public static final int HEAD_UNSUBSCRIBE = 10;//客户端到服务端客户端取消订阅请求
    public static final int HEAD_UNSUBACK = 11;//服务端到客户端取消订阅报文确认
    public static final int HEAD_PINGREQ = 12;//客户端到服务端心跳请求
    public static final int HEAD_PINGRESP = 13;//服务端到客户端心跳响应
    public static final int HEAD_DISCONNECT = 14;//客户端到服务端客户端断开连接
    public static final int HEAD_RESERVED_END = 15;//禁止保留


}
