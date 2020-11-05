package com.upuphub.trochilidae.web.resolver.impl;

import com.upuphub.trochilidae.web.resolver.ParameterResolver;
import io.netty.handler.codec.http.FullHttpRequest;

import java.lang.reflect.Parameter;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-05 12:37
 **/
public class PathVariableParameterResolver implements ParameterResolver {
    @Override
    public Object resolve(FullHttpRequest fullHttpRequest, Parameter parameter) {
        return null;
    }
}
