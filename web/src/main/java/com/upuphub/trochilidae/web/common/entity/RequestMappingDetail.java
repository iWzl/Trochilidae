package com.upuphub.trochilidae.web.common.entity;

import com.upuphub.trochilidae.web.common.lang.HttpMethod;

/**
 * 请求产生的数据映射
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-20 14:25
 **/
public class RequestMappingDetail {

    private HttpMethod httpMethod;

    private String name;

    private String[] path;

    private String[] headers;

    private String[] consumes;

    private String[] produces;


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

        public RequestMappingDetail build(){
            return new RequestMappingDetail(httpMethod,name,path,headers,consumes,produces);
        }
    }

    private RequestMappingDetail(HttpMethod httpMethod, String name, String[] path, String[] headers, String[] consumes, String[] produces) {
        this.httpMethod = httpMethod;
        this.name = name;
        this.path = path;
        this.headers = headers;
        this.consumes = consumes;
        this.produces = produces;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPath() {
        return path;
    }

    public void setPath(String[] path) {
        this.path = path;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public String[] getConsumes() {
        return consumes;
    }

    public void setConsumes(String[] consumes) {
        this.consumes = consumes;
    }

    public String[] getProduces() {
        return produces;
    }

    public void setProduces(String[] produces) {
        this.produces = produces;
    }


    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }
}
