package com.piggsoft.tower.core.data.connect;

public class ConnectionVariableHeadBuilder {



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

    public static ConnectionVariableHeadBuilder create() {
        return new ConnectionVariableHeadBuilder();
    }

    public ConnectionVariableHeadBuilder protocolNameLength(int protocolNameLength) {
        this.protocolNameLength = protocolNameLength;
        return this;
    }

    public ConnectionVariableHeadBuilder protocolName(CharSequence protocolName) {
        this.protocolName = protocolName;
        return this;
    }

    public ConnectionVariableHeadBuilder protocolLevel(int protocolLevel) {
        this.protocolLevel = protocolLevel;
        return this;
    }

    public ConnectionVariableHeadBuilder keepAlive(int keepAlive) {
        this.keepAlive = keepAlive;
        return this;
    }

    public ConnectionVariableHeadBuilder usernameFlag(int usernameFlag) {
        this.usernameFlag = usernameFlag;
        return this;
    }

    public ConnectionVariableHeadBuilder passwordFlag(int passwordFlag) {
        this.passwordFlag = passwordFlag;
        return this;
    }

    public ConnectionVariableHeadBuilder willRetain(int willRetain) {
        this.willRetain = willRetain;
        return this;
    }

    public ConnectionVariableHeadBuilder willQos(int willQos) {
        this.willQos = willQos;
        return this;
    }

    public ConnectionVariableHeadBuilder willFlag(int willFlag) {
        this.willFlag = willFlag;
        return this;
    }

    public ConnectionVariableHeadBuilder cleanSession(int cleanSession) {
        this.cleanSession = cleanSession;
        return this;
    }

    public ConnectionVariableHeadBuilder reserved(int reserved) {
        this.reserved = reserved;
        return this;
    }

    public ConnectionVariableHead build() {
        ConnectionVariableHead head = new ConnectionVariableHead();
        head.setCleanSession(cleanSession);
        head.setKeepAlive(keepAlive);
        head.setPasswordFlag(passwordFlag);
        head.setProtocolLevel(protocolLevel);
        head.setProtocolName(protocolName);
        head.setProtocolNameLength(protocolNameLength);
        head.setReserved(reserved);
        head.setUsernameFlag(usernameFlag);
        head.setWillFlag(willFlag);
        head.setWillQos(willQos);
        head.setWillRetain(willRetain);
        return head;

    }

}
