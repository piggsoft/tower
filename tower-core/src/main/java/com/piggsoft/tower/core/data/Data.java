package com.piggsoft.tower.core.data;

/**
 * @author piggsoft
 * @version 1.0
 * @create 2018/11/13
 * @since 1.0
 */
public class Data {

    private FixedHead fixedHead;
    private VariableHead variableHead;
    private Payload payload;

    public FixedHead getFixedHead() {
        return fixedHead;
    }

    public void setFixedHead(FixedHead fixedHead) {
        this.fixedHead = fixedHead;
    }

    public VariableHead getVariableHead() {
        return variableHead;
    }

    public void setVariableHead(VariableHead variableHead) {
        this.variableHead = variableHead;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
