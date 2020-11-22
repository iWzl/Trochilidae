package com.upuphub.trochilidae.example.injection.impl;

import com.upuphub.trochilidae.core.annotation.ioc.Autowired;
import com.upuphub.trochilidae.core.annotation.ioc.Component;
import com.upuphub.trochilidae.example.injection.CyclicInjectionClassA;
import com.upuphub.trochilidae.example.injection.CyclicInjectionClassB;

/**
 * CyclicInjectionClassAImpl
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-22 19:43
 **/

@Component
public class CyclicInjectionClassAImpl implements CyclicInjectionClassA {
    @Autowired
    private CyclicInjectionClassB cyclicInjectionClassB;


    @Override
    public void printClassName() {

    }
}
