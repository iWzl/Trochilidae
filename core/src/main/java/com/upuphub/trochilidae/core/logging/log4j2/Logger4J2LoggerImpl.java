package com.upuphub.trochilidae.core.logging.log4j2;

import com.upuphub.trochilidae.core.logging.AbstractLogger;
import com.upuphub.trochilidae.core.logging.LoggerFactory;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

/**
 *
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-01-26 22:50
 */
public class Logger4J2LoggerImpl extends AbstractLogger {

  private static final Marker MARKER = MarkerManager.getMarker(LoggerFactory.MARKER);

  private final org.apache.logging.log4j.Logger log;

  public Logger4J2LoggerImpl(org.apache.logging.log4j.Logger logger) {
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
    log.error(MARKER, s, e);
  }

  @Override
  public void error(String s) {
    log.error(MARKER, s);
  }

  @Override
  public void info(String s) {
      log.info(MARKER,s);
  }

  @Override
  public void debug(String s) {
    log.debug(MARKER, s);
  }

  @Override
  public void trace(String s) {
    log.trace(MARKER, s);
  }

  @Override
  public void warn(String s) {
    log.warn(MARKER, s);
  }

}
