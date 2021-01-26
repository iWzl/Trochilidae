package com.upuphub.trochilidae.core.exception;

/**
 * 配置加载异常
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-01-26 22:53
 **/
public class ConfigClassInitException extends TrochilidaeRuntimeException{
    public ConfigClassInitException(String message) {
        super(message);
    }

    public ConfigClassInitException(String message, Throwable cause) {
        super(message, cause);
    }
}
