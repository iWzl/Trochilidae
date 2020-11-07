package com.upuphub.trochilidae.example.bean;

import com.upuphub.trochilidae.web.common.http.HttpRequset;

/**
 * 用户请求回包
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-05 22:50
 **/
public class UserReq implements HttpRequset {
    private String name;
    private Integer age;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
