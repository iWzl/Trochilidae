package com.upuphub.trochilidae.core.logging.log4j2;

import com.upuphub.trochilidae.core.logging.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.spi.AbstractLogger;

/**
 *
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-01-26 22:50
 */
public class Logger4J2Impl extends com.upuphub.trochilidae.core.logging.AbstractLogger  {

  private final Logger log;

  public Logger4J2Impl(String clazz) {
    org.apache.logging.log4j.Logger logger = LogManager.getLogger(clazz);

    if (logger instanceof AbstractLogger) {
      log = new Logger4J2AbstractLoggerImpl((AbstractLogger) logger);
    } else {
      log = new Logger4J2LoggerImpl(logger);
    }
  }

  @Override
  public boolean isDebugEnabled() {
    return log.isDebugEnabled();
  }

  @Override
  public boolean isTraceEnabled() {
    return log.isTraceEnabled();
  }

  @Override
  public boolean isInfoEnabled() {
    return log.isInfoEnabled();
  }

  @Override
  public void error(String s, Throwable e) {
    log.error(s, e);
  }

  @Override
  public void error(String s) {
    log.error(s);
  }

  @Override
  public void info(String s) {
    log.info(s);
  }

  @Override
  public void debug(String s) {
    log.debug(s);
  }

  @Override
  public void trace(String s) {
    log.trace(s);
  }

  @Override
  public void warn(String s) {
    log.warn(s);
  }

}
