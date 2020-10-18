package com.upuphub.trochilidae.example.controller;

import com.upuphub.trochilidae.core.annotation.ioc.Autowired;
import com.upuphub.trochilidae.core.annotation.springmvc.RestController;
import com.upuphub.trochilidae.example.service.TrochilidaeTestService;

@RestController
public class TrochilidaeTestController {

    @Autowired
    private TrochilidaeTestService trochilidaeTestService;

    public void printHello(){
        trochilidaeTestService.sayHello();
    }

}
