package com.upuphub.trochilidae.web.common.entity;

import com.upuphub.trochilidae.web.common.lang.HttpMediaType;
import com.upuphub.trochilidae.web.common.lang.HttpMethod;

import java.lang.reflect.Method;

/**
 * 请求产生的数据映射
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-20 14:25
 **/
public class RequestMappingDetail {

    private final HttpMethod httpMethod;

    private final String name;

    private String[] path;

    private final String[] headers;

    private final String[] consumes;

    private final String[] produces;

    private final Method targetMethod;


    public static Builder newBuilder(){
        return new Builder();
    }

    public static class Builder{

        private HttpMethod httpMethod;

        private String name;

        private String[] path;

        private String[] headers;

        private String[] consumes;

        private String[] produces;

        private Method targetMethod;

        public Builder httpMethod(HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder path(String[] path) {
            this.path = path;
            return this;
        }

        public Builder headers(String[] headers) {
            this.headers = headers;
            return this;
        }

        public Builder consumes(String[] consumes) {
            this.consumes = consumes;
            return this;
        }

        public Builder produces(String[] produces) {
            this.produces = produces;
            return this;
        }

        public Builder targetMethod(Method targetMethod){
            this.targetMethod = targetMethod;
            return this;
        }

        public RequestMappingDetail build(){
            if(null != path && path.length == 0){ ;
                path = new String[1];
                path[0] = "/";
            }
            return new RequestMappingDetail(httpMethod,name,path,headers,consumes,produces,targetMethod);
        }
    }

    private RequestMappingDetail(HttpMethod httpMethod, String name, String[] path, String[] headers, String[] consumes, String[] produces,Method targetMethod) {
        this.httpMethod = httpMethod;
        this.name = name;
        this.path = path;
        this.headers = headers;
        this.consumes = consumes;
        this.produces = produces;
        this.targetMethod = targetMethod;
    }

    public String getName() {
        return name;
    }

    public String[] getPath() {
        return path;
    }

    public String[] getHeaders() {
        return headers;
    }

    public String[] getConsumes() {
        return consumes;
    }

    public boolean checkRequestContentType(String httpContentType){
        if(null != httpContentType && null != this.getConsumes() && this.getConsumes().length != 0){
            for (String consume : this.getConsumes()) {
                if(HttpMediaType.ALL_VALUE.equals(consume)){
                    return false;
                }else if(consume.equalsIgnoreCase(httpContentType)){
                    return false;
                }
            }
            return true;
        }else {
            return false;
        }
    }



    public String[] getProduces() {
        return produces;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public void setPath(String[] path) {
        this.path = path;
    }
}
