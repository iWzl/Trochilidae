package com.upuphub.trochilidae.core.logging.jdk14;

import com.upuphub.trochilidae.core.logging.AbstractLogger;

import java.util.logging.Level;

/**
 *
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-01-26 22:50
 */
public class Jdk14LoggingImpl extends AbstractLogger {

  private final java.util.logging.Logger log;

  public Jdk14LoggingImpl(String clazz) {
    log = java.util.logging.Logger.getLogger(clazz);
  }

  @Override
  public boolean isDebugEnabled() {
    return log.isLoggable(Level.FINE);
  }

  @Override
  public boolean isTraceEnabled() {
    return log.isLoggable(Level.FINER);
  }

  @Override
  public boolean isInfoEnabled() {
    return  log.isLoggable(Level.INFO);
  }

  @Override
  public void error(String s, Throwable e) {
    log.log(Level.SEVERE, s, e);
  }

  @Override
  public void error(String s) {
    log.log(Level.SEVERE, s);
  }

  @Override
  public void info(String s) {
    log.log(Level.INFO,s);
  }

  @Override
  public void debug(String s) {
    log.log(Level.FINE, s);
  }

  @Override
  public void trace(String s) {
    log.log(Level.FINER, s);
  }

  @Override
  public void warn(String s) {
    log.log(Level.WARNING, s);
  }

}
