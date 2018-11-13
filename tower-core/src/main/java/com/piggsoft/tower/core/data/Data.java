package com.piggsoft.tower.core.data;

/**
 * @author piggsoft
 * @version 1.0
 * @create 2018/11/13
 * @since 1.0
 */
public class Data {

    private FixedHead fixedHead;
    private VariableHeader variableHeader;
    private Payload payload;

    public FixedHead getFixedHead() {
        return fixedHead;
    }

    public void setFixedHead(FixedHead fixedHead) {
        this.fixedHead = fixedHead;
    }

    public VariableHeader getVariableHeader() {
        return variableHeader;
    }

    public void setVariableHeader(VariableHeader variableHeader) {
        this.variableHeader = variableHeader;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
