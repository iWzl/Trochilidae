package com.upuphub.trochilidae.core.config;

import java.util.Map;

/**
 * 项目配置项属性信息
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-16 17:25
 **/
public interface Configuration {
    String[] DEFAULT_CONFIG_NAMES = {"application.properties", "application.yaml","application.yml"};

    int getInt(String key);

    String getString(String key);

    Boolean getBoolean(String key);

    Long getLong(String key);

    Double getDouble(String key);

    Map<String, String> getAll();

    default void put(String id, String content) {
    }

    default void putAll(Map<String, String> maps) {
    }
}
