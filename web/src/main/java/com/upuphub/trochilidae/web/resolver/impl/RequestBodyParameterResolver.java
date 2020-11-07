package com.upuphub.trochilidae.web.resolver.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.upuphub.trochilidae.web.annotation.RequestBody;
import com.upuphub.trochilidae.web.common.entity.RequestParamManager;
import com.upuphub.trochilidae.web.exception.JsonFormartProcessException;
import com.upuphub.trochilidae.web.factory.SerializerFactory;
import com.upuphub.trochilidae.web.resolver.ParameterResolver;
import com.upuphub.trochilidae.web.serializer.Serializer;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;

import java.lang.reflect.Parameter;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-05 12:37
 **/
public class RequestBodyParameterResolver implements ParameterResolver {


    @Override
    public Object resolve(Parameter parameter, RequestParamManager requestParamManager) {
        String requestJsonBody = requestParamManager.getBodyByteBuf().toString(Charsets.toCharset(CharEncoding.UTF_8));
        if(null != parameter.getAnnotation(RequestBody.class) && null != requestJsonBody && !"".equals(requestJsonBody)){
            Serializer serializer = SerializerFactory.getDefaultSerializer();
            return serializer.serializeToObject(requestJsonBody.getBytes(),parameter.getType());
        }
        return null;
    }
}
