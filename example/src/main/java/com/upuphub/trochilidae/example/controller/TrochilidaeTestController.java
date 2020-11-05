package com.upuphub.trochilidae.example.controller;

import com.upuphub.trochilidae.core.annotation.ioc.Autowired;
import com.upuphub.trochilidae.example.bean.UserRsp;
import com.upuphub.trochilidae.example.service.TrochilidaeTestService;
import com.upuphub.trochilidae.web.annotation.GetMapping;
import com.upuphub.trochilidae.web.annotation.PathVariable;
import com.upuphub.trochilidae.web.annotation.RequestParam;
import com.upuphub.trochilidae.web.annotation.RestController;
import com.upuphub.trochilidae.web.common.http.DefaultHttpRsp;
import com.upuphub.trochilidae.web.common.http.ServiceResponseMessage;
import com.upuphub.trochilidae.web.common.lang.HttpMediaType;

import java.util.UUID;

@RestController
public class TrochilidaeTestController {

    @Autowired
    private TrochilidaeTestService trochilidaeTestService;

    @GetMapping
    public ServiceResponseMessage<DefaultHttpRsp> hello(){
        trochilidaeTestService.sayHello();
        return ServiceResponseMessage.createBySuccessCodeMessage();
    }


    @GetMapping(path = "/user/{name}",produces = HttpMediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage<UserRsp> printHelloWithAge(@RequestParam("hello") Integer age, @PathVariable("name")String name){
        UserRsp userRsp = new UserRsp();
        userRsp.setAge(age);
        userRsp.setName(name);
        userRsp.setDescription(UUID.randomUUID().toString());
        return ServiceResponseMessage.createBySuccessCodeMessage(userRsp);
    }

}
