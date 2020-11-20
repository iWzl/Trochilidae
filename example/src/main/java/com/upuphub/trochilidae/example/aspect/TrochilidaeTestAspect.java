package com.upuphub.trochilidae.example.aspect;

import com.upuphub.trochilidae.core.annotation.aop.*;
import com.upuphub.trochilidae.core.annotation.ioc.Component;
import com.upuphub.trochilidae.core.aop.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
@Order(value = 1)
public class TrochilidaeTestAspect {
    private final static Logger logger = LoggerFactory.getLogger(TrochilidaeTestAspect.class);

    @Pointcut("com.upuphub.trochilidae.example.service.*Service*")
    public void oneAspect() {
    }

    @Before
    public void beforeAction(JoinPoint params) {
        logger.debug("aspect headmaster : before to do something");
    }

    @After
    public void afterAction(Object result, JoinPoint joinPoint) {
        logger.debug("aspect headmaster after to do something");
    }

}
