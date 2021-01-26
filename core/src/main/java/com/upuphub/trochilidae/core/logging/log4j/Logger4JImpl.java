package com.upuphub.trochilidae.core.logging.log4j;

import com.upuphub.trochilidae.core.logging.AbstractLogger;
import org.apache.log4j.Level;

/**
 *
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-01-26 22:50
 */
public class Logger4JImpl extends AbstractLogger {

  private static final String FQCN = Logger4JImpl.class.getName();

  private final org.apache.log4j.Logger log;

  public Logger4JImpl(String clazz) {
    log = org.apache.log4j.Logger.getLogger(clazz);
  }

  @Override
  public boolean isInfoEnabled() {
    return log.isInfoEnabled();
  }

  @Override
  public void info(String s) {
    log.log(FQCN, Level.INFO, s, null);
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
  public void error(String s, Throwable e) {
    log.log(FQCN, Level.ERROR, s, e);
  }

  @Override
  public void error(String s) {
    log.log(FQCN, Level.ERROR, s, null);
  }

  @Override
  public void debug(String s) {
    log.log(FQCN, Level.DEBUG, s, null);
  }

  @Override
  public void trace(String s) {
    log.log(FQCN, Level.TRACE, s, null);
  }

  @Override
  public void warn(String s) {
    log.log(FQCN, Level.WARN, s, null);
  }

}
