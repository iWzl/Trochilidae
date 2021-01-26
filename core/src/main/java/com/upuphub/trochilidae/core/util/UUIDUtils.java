package com.upuphub.trochilidae.core.util;

import java.util.UUID;

/**
 * uuid工具类
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-01-26 22:50
 */
public class UUIDUtils {

    /**
     * 生成uuid，并去除横线
     * @return uuid
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 生成uuid
     * @return uuid
     */
    public static String generateDefaultUUID() {
        return UUID.randomUUID().toString();
    }
}
