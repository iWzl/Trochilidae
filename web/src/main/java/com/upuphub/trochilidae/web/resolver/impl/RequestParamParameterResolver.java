package com.upuphub.trochilidae.web.resolver.impl;

import com.upuphub.trochilidae.web.annotation.RequestParam;
import com.upuphub.trochilidae.web.common.entity.RequestParamManager;
import com.upuphub.trochilidae.web.common.util.ObjectUtil;
import com.upuphub.trochilidae.web.resolver.ParameterResolver;

import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-05 12:37
 **/
public class RequestParamParameterResolver implements ParameterResolver {
    @Override
    public Object resolve(Parameter parameter, RequestParamManager requestParamManager){
        RequestParam requestParam = parameter.getDeclaredAnnotation(RequestParam.class);
        String requestParameter = requestParam.value();
        String requestParameterValue = requestParamManager.getParamParameterMap().get(requestParameter);
        // convert the parameter to the specified type
        return ObjectUtil.convert(parameter.getType(), requestParameterValue);
    }

}
