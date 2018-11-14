package com.piggsoft.tower.core.data.connect;

import com.piggsoft.tower.core.data.VariableHeader;

/**
 * @author piggsoft
 * @version 1.0
 * @create 2018/11/13
 * @since 1.0
 */
public class ConnectionVariableHeader extends VariableHeader {

    private int msb;
    private int lsb;
    private int length;
    private int usernameFlag;
    private int passwordFlag;
    private int willRetain;
    private int willQos;
    private int willFlag;
    private int cleanSession;
    private int reserved;

    public static ConnectionVariableHeader read() {
        return null;
    }

}
