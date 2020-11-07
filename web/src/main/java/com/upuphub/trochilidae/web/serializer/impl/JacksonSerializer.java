package com.upuphub.trochilidae.web.serializer.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upuphub.trochilidae.web.exception.JsonFormartProcessException;
import com.upuphub.trochilidae.web.serializer.Serializer;

import java.io.IOException;


/**
 * Json的序列化器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-21 10:09
 **/
public class JacksonSerializer implements Serializer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T serializeToObject(byte[] bytes, Class<T> clazz){
        T object = null;
        try {
            object = objectMapper.readValue(bytes, clazz);
        } catch (IOException e) {
            throw new JsonFormartProcessException(e);
        }
        return object;
    }

    @Override
    public byte[] serializeToByteArray(Object object){
        byte[] bytes = new byte[0];
        try {
            // Java object to JSON string, default compact-print
            bytes = objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            logger.error("JacksonSerializer.serializeToByteArray Error {}",e.getMessage(),e);
        }
        return bytes;
    }
}
