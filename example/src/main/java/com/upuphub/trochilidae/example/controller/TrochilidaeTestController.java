package com.upuphub.trochilidae.example.controller;

import com.upuphub.trochilidae.core.annotation.ioc.Autowired;
import com.upuphub.trochilidae.example.service.TrochilidaeTestService;
import com.upuphub.trochilidae.web.annotation.GetMapping;
import com.upuphub.trochilidae.web.annotation.RequestParam;
import com.upuphub.trochilidae.web.annotation.RestController;
import com.upuphub.trochilidae.web.common.lang.HttpMediaType;

import java.util.Collections;
import java.util.Map;

@RestController
public class TrochilidaeTestController {

    @Autowired
    private TrochilidaeTestService trochilidaeTestService;

    @GetMapping
    public void printHello(){
        trochilidaeTestService.sayHello();
    }


    @GetMapping(path = "/test",produces = HttpMediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String,Integer> printHelloWithAge(@RequestParam("hello") Integer hello){
        return Collections.singletonMap("name",hello);
    }

}
