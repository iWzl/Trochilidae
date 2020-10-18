package com.upuphub.trochilidae.web.common.lang;

/**
 * HTTP请求的类型
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-17 18:33
 **/
public enum HttpMethod {
    /**
     * HTTP请求的类型
     */
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");


    /**
     * 请求方法类型
     */
    private final String methodType;

    HttpMethod(String methodType) {
        this.methodType = methodType;
    }

    public String getMethodType() {
        return methodType;
    }
}
