package com.upuphub.trochilidae.core.config;

import java.util.Map;

/**
 * 配置加载完成后的后处理
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-16 22:28
 **/
@FunctionalInterface
public interface ResourceConfigurationPostProcess {
    Map<String,String> handler(Map<String, String> resourceMap);
}
