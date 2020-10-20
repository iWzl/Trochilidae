package com.upuphub.trochilidae.example.controller;

import com.upuphub.trochilidae.core.annotation.ioc.Autowired;
import com.upuphub.trochilidae.example.service.TrochilidaeTestService;
import com.upuphub.trochilidae.web.annotation.GetMapping;
import com.upuphub.trochilidae.web.annotation.RestController;

@RestController
public class TrochilidaeTestController {

    @Autowired
    private TrochilidaeTestService trochilidaeTestService;

    @GetMapping
    public void printHello(){
        trochilidaeTestService.sayHello();
    }

}
