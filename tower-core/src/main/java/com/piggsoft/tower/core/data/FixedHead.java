package com.piggsoft.tower.core.data;

/**
 * @author piggsoft
 * @version 1.0
 * @create 2018/11/13
 * @since 1.0
 */
public class FixedHead {

    private int ctrlType;
    private int ctrlFlags;
    private int remainingLength;

    public FixedHead() {}

    public FixedHead(int ctrlType, int ctrlFlags, int remainingLength) {
        this.ctrlType = ctrlType;
        this.ctrlFlags = ctrlFlags;
        this.remainingLength = remainingLength;
    }

    public int getCtrlType() {
        return ctrlType;
    }

    public void setCtrlType(int ctrlType) {
        this.ctrlType = ctrlType;
    }

    public int getCtrlFlags() {
        return ctrlFlags;
    }

    public void setCtrlFlags(int ctrlFlags) {
        this.ctrlFlags = ctrlFlags;
    }

    public int getRemainingLength() {
        return remainingLength;
    }

    public void setRemainingLength(int remainingLength) {
        this.remainingLength = remainingLength;
    }

    @Override
    public String toString() {
        return "FixedHead{" +
                "ctrlType=" + ctrlType +
                ", ctrlFlags=" + ctrlFlags +
                ", remainingLength=" + remainingLength +
                '}';
    }
}
