package com.piggsoft.tower.core.exception;

public class PacketErrorException extends Exception {

    public PacketErrorException() {
    }

    public PacketErrorException(String message) {
        super(message);
    }

    public PacketErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public PacketErrorException(Throwable cause) {
        super(cause);
    }

    public PacketErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
