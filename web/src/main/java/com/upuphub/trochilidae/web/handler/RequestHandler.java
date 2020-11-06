package com.upuphub.trochilidae.web.handler;

import com.upuphub.trochilidae.core.common.util.ReflectionUtil;
import com.upuphub.trochilidae.core.factory.BeanFactory;
import com.upuphub.trochilidae.web.common.entity.RequestMappingDetail;
import com.upuphub.trochilidae.web.common.entity.RequestParamManager;
import com.upuphub.trochilidae.web.common.lang.HttpMediaType;
import com.upuphub.trochilidae.web.common.util.BeanHelper;
import com.upuphub.trochilidae.web.common.util.UrlUtil;
import com.upuphub.trochilidae.web.exception.HttpMediaTypeNotSupportException;
import com.upuphub.trochilidae.web.exception.RequestMappingNotFindException;
import com.upuphub.trochilidae.web.factory.FullHttpResponseFactory;
import com.upuphub.trochilidae.web.factory.ParameterResolverFactory;
import com.upuphub.trochilidae.web.resolver.ParameterResolver;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求处理器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-19 16:38
 **/
public interface RequestHandler {
    /**
     * Http请求的处理
     *
     * @param fullHttpRequest Http请求
     * @return 处理的返回结果
     */
    FullHttpResponse handle(FullHttpRequest fullHttpRequest);


    /**
     * 根据请求参数，完成相关映射的调用的实现并返回相关属性属性
     *
     * @param fullHttpRequest 请求携带的数据信息
     * @param requestMappingDetail 请求方法映射属性
     * @return 处理完成后的方法结果返回
     */
    default FullHttpResponse run(FullHttpRequest fullHttpRequest, RequestMappingDetail requestMappingDetail){
        String httpContentType = fullHttpRequest.headers().get(HttpMediaType.CONTENT_TYPE_VALUE);
        if(null == requestMappingDetail || null == requestMappingDetail.getTargetMethod()){
            throw new RequestMappingNotFindException("RequestPath Handler Not Find : " + UrlUtil.getRequestPath(fullHttpRequest.uri()));
        }
        if(requestMappingDetail.checkRequestContentType(httpContentType)){
            throw new HttpMediaTypeNotSupportException("Http MediaType Not Support : "+ httpContentType);
        }
        Method targetMethod = requestMappingDetail.getTargetMethod();
        // Get request, the mailbox gets the request parameters carried in the request path
        Parameter[] targetMethodParameters = targetMethod.getParameters();
        // target method parameters.
        // notice! you should convert it to array when pass into the executeMethod method
        RequestParamManager requestParamManager = RequestParamManager.build(fullHttpRequest,requestMappingDetail);
        List<Object> targetMethodParams = new ArrayList<>();
        for (Parameter parameter : targetMethodParameters) {
            ParameterResolver parameterResolver = ParameterResolverFactory.get(parameter);
            if (parameterResolver != null) {
                Object param = parameterResolver.resolve(parameter,requestParamManager);
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
