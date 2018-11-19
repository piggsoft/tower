package com.piggsoft.tower.core.data.connect;

import com.piggsoft.tower.core.data.VariableHead;

/**
 * @author piggsoft
 * @version 1.0
 * @create 2018/11/13
 * @since 1.0
 */
public class ConnectionVariableHead extends VariableHead {

    private int protocolNameLength;
    private CharSequence protocolName;
    private int protocolLevel;
    private int keepAlive;
    private int usernameFlag;
    private int passwordFlag;
    private int willRetain;
    private int willQos;
    private int willFlag;
    private int cleanSession;
    private int reserved;

    public int getProtocolNameLength() {
        return protocolNameLength;
    }

    public void setProtocolNameLength(int protocolNameLength) {
        this.protocolNameLength = protocolNameLength;
    }

    public CharSequence getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(CharSequence protocolName) {
        this.protocolName = protocolName;
    }

    public int getProtocolLevel() {
        return protocolLevel;
    }

    public void setProtocolLevel(int protocolLevel) {
        this.protocolLevel = protocolLevel;
    }

    public int getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(int keepAlive) {
        this.keepAlive = keepAlive;
    }

    public int getUsernameFlag() {
        return usernameFlag;
    }

    public void setUsernameFlag(int usernameFlag) {
        this.usernameFlag = usernameFlag;
    }

    public int getPasswordFlag() {
        return passwordFlag;
    }

    public void setPasswordFlag(int passwordFlag) {
        this.passwordFlag = passwordFlag;
    }

    public int getWillRetain() {
        return willRetain;
    }

    public void setWillRetain(int willRetain) {
        this.willRetain = willRetain;
    }

    public int getWillQos() {
        return willQos;
    }

    public void setWillQos(int willQos) {
        this.willQos = willQos;
    }

    public int getWillFlag() {
        return willFlag;
    }

    public void setWillFlag(int willFlag) {
        this.willFlag = willFlag;
    }

    public int getCleanSession() {
        return cleanSession;
    }

    public void setCleanSession(int cleanSession) {
        this.cleanSession = cleanSession;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }
}
