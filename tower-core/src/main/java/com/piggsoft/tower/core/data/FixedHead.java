package com.piggsoft.tower.core.data;

/**
 * @author piggsoft
 * @version 1.0
 * @create 2018/11/13
 * @since 1.0
 */
public class FixedHead {

    private int ctlType;
    private int ctlFlags;
    private int remainingLength;

    public int getCtlType() {
        return ctlType;
    }

    public void setCtlType(int ctlType) {
        this.ctlType = ctlType;
    }

    public int getCtlFlags() {
        return ctlFlags;
    }

    public void setCtlFlags(int ctlFlags) {
        this.ctlFlags = ctlFlags;
    }

    public int getRemainingLength() {
        return remainingLength;
    }

    public void setRemainingLength(int remainingLength) {
        this.remainingLength = remainingLength;
    }
}
