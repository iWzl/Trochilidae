package com.upuphub.trochilidae.core.logging;

/**
 * Log
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-01-26 22:49
 **/
public interface Logger {

    boolean isDebugEnabled();

    boolean isTraceEnabled();

    boolean isInfoEnabled();

    void error(String content, Object[] values,Throwable e);

    void error(String content, Object... values);

    void info(String content, Object... values);

    void debug(String content, Object... values);

    void trace(String content, Object... values);

    void warn(String content, Object... values);

    void error(String content, Throwable e);

    void error(String content);

    void info(String content);

    void debug(String content);

    void trace(String content);

    void warn(String content);
}
