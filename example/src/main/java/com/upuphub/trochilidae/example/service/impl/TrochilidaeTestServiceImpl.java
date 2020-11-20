package com.upuphub.trochilidae.example.service.impl;

import com.upuphub.trochilidae.core.annotation.ioc.Component;
import com.upuphub.trochilidae.core.annotation.ioc.Value;
import com.upuphub.trochilidae.example.service.TrochilidaeTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

/**
 * @author leo
 */
@Component
public class TrochilidaeTestServiceImpl implements TrochilidaeTestService {
    private final static Logger loger = LoggerFactory.getLogger(TrochilidaeTestService.class);

    @Value("test.age")
    private Integer age;

    @Override
    public void sayHello() {
        System.out.println("Hello Trochilidae");
    }

    @Override
    public String base64EncodeName(String name) {
        loger.warn("base64EncodeName Hello Trochilidae Run ...");
        loger.warn("Value Age    >>>>>>>   " + age);
        return Base64.getEncoder().encodeToString(name.getBytes());
    }
}
