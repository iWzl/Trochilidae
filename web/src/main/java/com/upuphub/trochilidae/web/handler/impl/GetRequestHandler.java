package com.upuphub.trochilidae.web.handler.impl;

import com.upuphub.trochilidae.web.common.entity.RequestMappingDetail;
import com.upuphub.trochilidae.web.handler.RequestHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Get请求的处理器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-03 23:17
 **/
public class GetRequestHandler implements RequestHandler {
    @Override
    public Object handle(FullHttpRequest fullHttpRequest) {
        String requestUri = fullHttpRequest.uri();
        fullHttpRequest.headers();
        Map<String, String> queryParameterMappings = getQueryParams(requestUri);
//        // get http request path，such as "/user"
//        String requestPath = UrlUtil.getRequestPath(requestUri);
//        // get target method
//        RequestMethodDetail methodDetail = RouteMethodMapper.getRequestMethodDetail(requestPath, HttpMethod.GET);
//        if (methodDetail == null) {
//            return null;
//        }
//        methodDetail.setQueryParameterMappings(queryParameterMappings);
//        Method targetMethod = methodDetail.getMethod();
//        if (targetMethod == null) {
//            return null;
//        }
//        //log.info("requestPath -> target method [{}]", targetMethod.getName());
//        Parameter[] targetMethodParameters = targetMethod.getParameters();
//        // target method parameters.
//        // notice! you should convert it to array when pass into the executeMethod method
//        List<Object> targetMethodParams = new ArrayList<>();
//        for (Parameter parameter : targetMethodParameters) {
//            ParameterResolver parameterResolver = ParameterResolverFactory.get(parameter);
//            if (parameterResolver != null) {
//                Object param = parameterResolver.resolve(methodDetail, parameter);
//                targetMethodParams.add(param);
//            }
//        }
//        String beanName = BeanHelper.getBeanName(methodDetail.getMethod().getDeclaringClass());
//        Object targetObject = BeanFactory.BEANS.get(beanName);
//        return FullHttpResponseFactory.getSuccessResponse(targetMethod, targetMethodParams, targetObject);
        return null;
    }


    /**
     * get the parameters of uri
     */
    private Map<String, String> getQueryParams(String uri) {
        QueryStringDecoder queryDecoder = new QueryStringDecoder(uri, Charsets.toCharset(CharEncoding.UTF_8));
        Map<String, List<String>> parameters = queryDecoder.parameters();
        Map<String, String> queryParams = new HashMap<>();
        for (Map.Entry<String, List<String>> attr : parameters.entrySet()) {
            for (String attrVal : attr.getValue()) {
                queryParams.put(attr.getKey(), attrVal);
            }
        }
        return queryParams;
    }
}
