package com.upuphub.trochilidae.example.controller;

import com.upuphub.trochilidae.example.TrochilidaeExampleApplication;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

/**
 * 控制器测试
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-13 16:16
 **/
public class TestController {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:8081";
    }

    @Test
    void get_user_by_id() {
        when().get("/test").
                then().
                statusCode(200).
                body("hello", equalTo("World"));

    }
}
