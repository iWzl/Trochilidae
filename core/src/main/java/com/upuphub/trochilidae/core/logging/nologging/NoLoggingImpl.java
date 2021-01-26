package com.upuphub.trochilidae.core.logging.nologging;


import com.upuphub.trochilidae.core.logging.AbstractLogger;

/**
 *
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-01-26 22:50
 */
public class NoLoggingImpl extends AbstractLogger {

  public NoLoggingImpl(String clazz) {
    // Do Nothing
  }

  @Override
  public boolean isDebugEnabled() {
    return false;
  }

  @Override
  public boolean isTraceEnabled() {
    return false;
  }

  @Override
  public boolean isInfoEnabled() {
    return false;
  }

  @Override
  public void error(String s, Throwable e) {
    // Do Nothing
  }

  @Override
  public void error(String s) {
    // Do Nothing
  }

  @Override
  public void info(String s) {
    // Do Nothing
  }

  @Override
  public void debug(String s) {
    // Do Nothing
  }

  @Override
  public void trace(String s) {
    // Do Nothing
  }

  @Override
  public void warn(String s) {
    // Do Nothing
  }

}
