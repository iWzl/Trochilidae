package com.upuphub.trochilidae.core.logging;

import com.upuphub.trochilidae.core.util.BracePlaceholder;

/**
 * BaseLogger
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-01-27 00:01
 **/
public abstract class AbstractLogger implements Logger{

    @Override
    public void error(String content, Object[] values, Throwable e) {
        this.error(BracePlaceholder.resolve(content,values),e);
    }

    @Override
    public void error(String content, Object... values) {
        this.error(BracePlaceholder.resolve(content,values));
    }

    @Override
    public void info(String content, Object... values) {
        this.info(BracePlaceholder.resolve(content,values));
    }

    @Override
    public void debug(String content, Object... values) {
        this.debug(BracePlaceholder.resolve(content,values));
    }

    @Override
    public void trace(String content, Object... values) {
        this.trace(BracePlaceholder.resolve(content,values));
    }

    @Override
    public void warn(String content, Object... values) {
        this.warn(BracePlaceholder.resolve(content,values));
    }
}
