package com.upuphub.trochilidae.core.exception;

/**
 * @author Leo Wang
 */
public abstract class TrochilidaeRuntimeException extends RuntimeException{
    public TrochilidaeRuntimeException() {
        super();
    }

    public TrochilidaeRuntimeException(String message) {
        super(message);
    }

    public TrochilidaeRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TrochilidaeRuntimeException(Throwable cause) {
        super(cause);
    }

    protected TrochilidaeRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
