package com.upuphub.trochilidae.web.common.entity;

import com.upuphub.trochilidae.web.common.util.UrlUtil;
import com.upuphub.trochilidae.web.factory.RouteMethodMapper;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 方法明细
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-18 16:12
 **/
public class RequestMethodDetail {
    /**
     * 触发的方法
     */
    private Method method;

    private Map<String, String> urlParameterMappings;

    private Map<String, String> queryParameterMappings;

    private RequestMappingDetail requestMappingDetail;


    public void build(String requestPath, RequestMappingDetail requestMappingDetail) {
//        Map<String, Method> requestMappings = RouteMethodMapper.getRequestMethodMappingMapByHttpType(requestMappingDetail.getHttpMethod());
//        Map<String, String> urlMappings =  RouteMethodMapper.getRequestUrlMappingMapByHttpType(requestMappingDetail.getHttpMethod());
//        requestMappings.forEach((key, value) -> {
//            Pattern pattern = Pattern.compile(key);
//            boolean b = pattern.matcher(requestPath).find();
//            if (b) {
//                this.setMethod(value);
//                String url = urlMappings.get(key);
//                Map<String, String> urlParameterMappings = UrlUtil.getUrlParameterMappings(requestPath, url);
//                this.setUrlParameterMappings(urlParameterMappings);
//            }
//        });
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

    public RequestMappingDetail getRequestMappingDetail() {
        return requestMappingDetail;
    }

    public void setRequestMappingDetail(RequestMappingDetail requestMappingDetail) {
        this.requestMappingDetail = requestMappingDetail;
    }
}
