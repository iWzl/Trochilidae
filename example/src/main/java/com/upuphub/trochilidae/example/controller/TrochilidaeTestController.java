package com.upuphub.trochilidae.example.controller;

import com.upuphub.trochilidae.core.annotation.ioc.Autowired;
import com.upuphub.trochilidae.example.bean.UserReq;
import com.upuphub.trochilidae.example.bean.UserRsp;
import com.upuphub.trochilidae.example.service.TrochilidaeTestService;
import com.upuphub.trochilidae.web.annotation.*;
import com.upuphub.trochilidae.web.common.http.DefaultHttpRsp;
import com.upuphub.trochilidae.web.common.http.ServiceResponseMessage;
import com.upuphub.trochilidae.web.common.lang.HttpMediaType;

import java.util.HashMap;
import java.util.Map;
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

    @PostMapping(path = "/user",consumes = HttpMediaType.APPLICATION_JSON_UTF8_VALUE,produces = HttpMediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage<UserRsp> printRequest(@RequestBody UserReq userReq){
        UserRsp userRsp = new UserRsp();
        userRsp.setAge(userReq.getAge() + 1);
        userRsp.setName(userReq.getName() + "-Name");
        userRsp.setDescription(userReq.getDescription() + UUID.randomUUID().toString());
        return ServiceResponseMessage.createBySuccessCodeMessage(userRsp);
    }

    @GetMapping(path = "/test",produces = HttpMediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String,String> printRequest1(){
        Map<String, String> map = new HashMap<String, String>(4);
        map.put("hello", "World");
        return map;
    }


    @PutMapping(path = "/user",consumes = HttpMediaType.APPLICATION_JSON_UTF8_VALUE,produces = HttpMediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage<UserRsp> printRequesto(@RequestBody UserReq userReq){
        UserRsp userRsp = new UserRsp();
        userRsp.setAge(userReq.getAge() + 1);
        userRsp.setName(userReq.getName() + "-Name");
        userRsp.setDescription(userReq.getDescription() + UUID.randomUUID().toString());
        return ServiceResponseMessage.createBySuccessCodeMessage(userRsp);
    }

    @DeleteMapping(path = "/user",consumes = HttpMediaType.APPLICATION_JSON_UTF8_VALUE,produces = HttpMediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage<UserRsp> printRequestoo(@RequestBody UserReq userReq){
        UserRsp userRsp = new UserRsp();
        userRsp.setAge(userReq.getAge() + 1);
        userRsp.setName(userReq.getName() + "-Name");
        userRsp.setDescription(userReq.getDescription() + UUID.randomUUID().toString());
        return ServiceResponseMessage.createBySuccessCodeMessage(userRsp);
    }

}
