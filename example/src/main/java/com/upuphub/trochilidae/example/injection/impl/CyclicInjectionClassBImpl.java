package com.upuphub.trochilidae.example.injection.impl;

import com.upuphub.trochilidae.core.annotation.ioc.Autowired;
import com.upuphub.trochilidae.core.annotation.ioc.Component;
import com.upuphub.trochilidae.example.injection.CyclicInjectionClassB;
import com.upuphub.trochilidae.example.injection.CyclicInjectionClassC;

/**
 * CyclicInjectionClassBImpl
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-22 19:43
 **/

@Component
public class CyclicInjectionClassBImpl implements CyclicInjectionClassB {

    @Autowired
    private CyclicInjectionClassC cyclicInjectionClassC;

    @Override
    public void printClassName() {

    }
}
