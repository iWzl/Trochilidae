package com.upuphub.trochilidae.web.handler.impl;

import com.upuphub.trochilidae.core.common.util.ReflectionUtil;
import com.upuphub.trochilidae.core.factory.BeanFactory;
import com.upuphub.trochilidae.web.common.entity.RequestMappingDetail;
import com.upuphub.trochilidae.web.common.lang.HttpMediaType;
import com.upuphub.trochilidae.web.common.lang.HttpMethod;
import com.upuphub.trochilidae.web.common.util.BeanHelper;
import com.upuphub.trochilidae.web.common.util.UrlUtil;
import com.upuphub.trochilidae.web.exception.HttpMediaTypeNotSupportException;
import com.upuphub.trochilidae.web.exception.RequestMappingNotFindException;
import com.upuphub.trochilidae.web.factory.FullHttpResponseFactory;
import com.upuphub.trochilidae.web.factory.ParameterResolverFactory;
import com.upuphub.trochilidae.web.factory.RouteMethodMapper;
import com.upuphub.trochilidae.web.handler.RequestHandler;
import com.upuphub.trochilidae.web.resolver.ParameterResolver;
import io.netty.handler.codec.http.FullHttpRequest;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

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
        String httpContentType = fullHttpRequest.headers().get(HttpMediaType.CONTENT_TYPE_VALUE);
        // get http request path
        String requestPath = UrlUtil.getRequestPath(requestUri);
        // get target request Mapping detail
        RequestMappingDetail requestMappingDetail = RouteMethodMapper.getRequestMappingDetail(requestPath,HttpMethod.GET);
        if(null == requestMappingDetail || null == requestMappingDetail.getTargetMethod()){
            throw new RequestMappingNotFindException("RequestPath Handler Not Find : " + requestPath);
        }
        if(requestMappingDetail.checkRequestContentType(httpContentType)){
            throw new HttpMediaTypeNotSupportException("Http MediaType Not Support : "+ httpContentType);
        }
        Method targetMethod = requestMappingDetail.getTargetMethod();
        // Get request, the mailbox gets the request parameters carried in the request path
        Parameter[] targetMethodParameters = targetMethod.getParameters();
        // target method parameters.
        // notice! you should convert it to array when pass into the executeMethod method
        List<Object> targetMethodParams = new ArrayList<>();
        for (Parameter parameter : targetMethodParameters) {
            ParameterResolver parameterResolver = ParameterResolverFactory.get(parameter);
            if (parameterResolver != null) {
                Object param = parameterResolver.resolve(fullHttpRequest, parameter);
                targetMethodParams.add(param);
            }
        }
        String beanName = BeanHelper.getBeanName(requestMappingDetail.getTargetMethod().getDeclaringClass());
        Object targetObject = BeanFactory.BEANS.get(beanName);
        if (targetMethod.getReturnType() == void.class) {
            ReflectionUtil.executeTargetMethodNoResult(targetObject, targetMethod, targetMethodParams.toArray());
            return FullHttpResponseFactory.buildDefaultFullHttpResponseNoResult(requestMappingDetail.getProduces());
        }else {
            Object result = ReflectionUtil.executeTargetMethod(targetObject, targetMethod, targetMethodParams.toArray());
            return FullHttpResponseFactory.buildDefaultFullHttpResponseWithResult(requestMappingDetail.getProduces(),result);
        }
    }
}
