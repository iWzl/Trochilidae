package com.upuphub.trochilidae.core.logging.slf4j;

import com.upuphub.trochilidae.core.logging.AbstractLogger;

/**
 *
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-01-26 22:50
 */
class Slf4jLoggerImpl extends AbstractLogger {

  private final org.slf4j.Logger log;

  public Slf4jLoggerImpl(org.slf4j.Logger logger) {
    log = logger;
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
