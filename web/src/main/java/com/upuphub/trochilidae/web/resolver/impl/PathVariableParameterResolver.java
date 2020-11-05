package com.upuphub.trochilidae.web.resolver.impl;

import com.upuphub.trochilidae.web.annotation.PathVariable;
import com.upuphub.trochilidae.web.common.entity.RequestParamManager;
import com.upuphub.trochilidae.web.common.util.ObjectUtil;
import com.upuphub.trochilidae.web.resolver.ParameterResolver;

import java.lang.reflect.Parameter;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-05 12:37
 **/
public class PathVariableParameterResolver implements ParameterResolver {
    @Override
    public Object resolve(Parameter parameter, RequestParamManager requestParamManager) {
        PathVariable pathVariable = parameter.getDeclaredAnnotation(PathVariable.class);
        String requestParameterKey = pathVariable.value();
        String requestParameterValue = requestParamManager.getPathVariableParameterMap().get(requestParameterKey);
        // convert the parameter to the specified type
        return ObjectUtil.convert(parameter.getType(), requestParameterValue);
    }
}
