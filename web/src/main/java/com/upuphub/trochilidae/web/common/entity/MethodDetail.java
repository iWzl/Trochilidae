package com.upuphub.trochilidae.web.common.entity;

import com.upuphub.trochilidae.web.common.util.UrlUtil;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 方法明细
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-18 16:12
 **/
public class MethodDetail {
    /**
     * 触发的方法
     */
    private Method method;
    // url parameter mapping
    private Map<String, String> urlParameterMappings;
    // url query parameter mapping
    private Map<String, String> queryParameterMappings;
    // json type http post request data
    private String json;

    public void build(String requestPath, Map<String, Method> requestMappings, Map<String, String> urlMappings) {
        requestMappings.forEach((key, value) -> {
            Pattern pattern = Pattern.compile(key);
            boolean b = pattern.matcher(requestPath).find();
            if (b) {
                this.setMethod(value);
                String url = urlMappings.get(key);
                Map<String, String> urlParameterMappings = UrlUtil.getUrlParameterMappings(requestPath, url);
                this.setUrlParameterMappings(urlParameterMappings);
            }
        });
    }


    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Map<String, String> getUrlParameterMappings() {
        return urlParameterMappings;
    }

    public void setUrlParameterMappings(Map<String, String> urlParameterMappings) {
        this.urlParameterMappings = urlParameterMappings;
    }

    public Map<String, String> getQueryParameterMappings() {
        return queryParameterMappings;
    }

    public void setQueryParameterMappings(Map<String, String> queryParameterMappings) {
        this.queryParameterMappings = queryParameterMappings;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
