package com.upuphub.trochilidae.example.service.impl;

import com.upuphub.trochilidae.core.annotation.ioc.Component;
import com.upuphub.trochilidae.example.service.TrochilidaeTestService;

import java.util.Map;

@Component
public class TrochilidaeTestServiceImpl implements TrochilidaeTestService {

    public void sayHello() {
        System.out.println("Hello Trochilidae");
    }
}
