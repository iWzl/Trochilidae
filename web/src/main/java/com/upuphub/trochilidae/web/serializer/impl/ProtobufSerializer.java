package com.upuphub.trochilidae.web.serializer.impl;

import com.upuphub.trochilidae.web.serializer.Serializer;

/**
 * Protobuf的序列化器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-05 16:58
 **/
public class ProtobufSerializer implements Serializer {
    @Override
    public <T> T serializeToObject(byte[] bytes, Class<T> clazz) {
        return null;
    }

    @Override
    public byte[] serializeToByteArray(Object object) {
        return new byte[0];
    }
}
