package com.upuphub.trochilidae.web.serializer;

import com.upuphub.trochilidae.core.logging.Logger;
import com.upuphub.trochilidae.core.logging.LoggerFactory;

/**
 * Bean 序列化器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-21 09:53
 **/
public interface Serializer {

    final Logger logger = LoggerFactory.getLogger(Serializer.class);

    /**
     * 将二进制转成对象
     *
     * @param bytes 需要转换的二进制
     * @param clazz 转换成的对象的类
     * @param <T> 转换成的对象的类的类型
     * @return 转换成功后的结果
     */
    <T> T serializeToObject(byte[] bytes,Class<T> clazz);

    /**
     * 将对象转换成二进制数组
     *
     * @param object 需要转换的数据对象
     * @return 转换后的处理结果
     */
    byte[] serializeToByteArray(Object object);
}
