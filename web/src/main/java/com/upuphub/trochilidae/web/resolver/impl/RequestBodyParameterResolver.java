package com.upuphub.trochilidae.web.resolver.impl;

import com.upuphub.trochilidae.web.common.entity.RequestParamManager;
import com.upuphub.trochilidae.web.resolver.ParameterResolver;

import java.lang.reflect.Parameter;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-05 12:37
 **/
public class RequestBodyParameterResolver implements ParameterResolver {


    @Override
    public Object resolve(Parameter parameter, RequestParamManager requestParamManager) {
        return null;
    }
}
