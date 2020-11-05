package com.upuphub.trochilidae.web.common.entity;

import com.upuphub.trochilidae.web.common.util.UrlUtil;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求中可能携带的数据管理器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-05 22:34
 **/
public class RequestParamManager {
    private Map<String,String> pathVariableParameterMap;
    private Map<String, String> paramParameterMap;
    private String jsonParameter;

    public static RequestParamManager build(FullHttpRequest fullHttpRequest, RequestMappingDetail requestMappingDetail) {
        String requestUri = fullHttpRequest.uri();
        String requestPath = UrlUtil.getRequestPath(requestUri);
        RequestParamManager requestParamManager = new RequestParamManager();
        requestParamManager.setParamParameterMap(requestParamManager.getQueryParams(requestUri));
        Map<String, String> requestUrlParamMap = new HashMap<>();
        for (String path : requestMappingDetail.getPath()) {
            requestUrlParamMap.putAll(requestParamManager.getUrlParameterMappings(requestPath,path));
        }
        requestParamManager.setPathVariableParameterMap(requestUrlParamMap);
        return requestParamManager;
    }



    /**
     * Match the request path parameter to the URL parameter
     * <p>
     * eg: requestPath="/user/1" url="/user/{id}"
     * this method will return:{"id" -> "1","user" -> "user"}
     * </p>
     */
    private Map<String, String> getUrlParameterMappings(String requestPath, String url) {
        String[] requestParams = requestPath.split("/");
        String[] urlParams = url.split("/");
        Map<String, String> urlParameterMappings = new HashMap<>();
        for (int i = 1; i < urlParams.length; i++) {
            urlParameterMappings.put(urlParams[i].replace("{", "").replace("}", ""), requestParams[i]);
        }
        return urlParameterMappings;
    }


    /**
     * get the parameters of uri
     */
    private Map<String, String> getQueryParams(String uri) {
        QueryStringDecoder queryDecoder = new QueryStringDecoder(uri, Charsets.toCharset(CharEncoding.UTF_8));
        Map<String, List<String>> parameters = queryDecoder.parameters();
        Map<String, String> queryParams = new HashMap<>(16);
        for (Map.Entry<String, List<String>> attr : parameters.entrySet()) {
            for (String attrVal : attr.getValue()) {
                queryParams.put(attr.getKey(), attrVal);
            }
        }
        return queryParams;
    }

    public Map<String, String> getPathVariableParameterMap() {
        return pathVariableParameterMap;
    }

    public void setPathVariableParameterMap(Map<String, String> pathVariableParameterMap) {
        this.pathVariableParameterMap = pathVariableParameterMap;
    }

    public Map<String, String> getParamParameterMap() {
        return paramParameterMap;
    }

    public void setParamParameterMap(Map<String, String> paramParameterMap) {
        this.paramParameterMap = paramParameterMap;
    }

    public String getJsonParameter() {
        return jsonParameter;
    }

    public void setJsonParameter(String jsonParameter) {
        this.jsonParameter = jsonParameter;
    }
}
