package com.upuphub.trochilidae.core.config;

import java.util.Base64;
import java.util.Map;

/**
 * 测试配置后处理器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-17 11:14
 **/
public class TestResourceConfigurationPostProcess implements ResourceConfigurationPostProcess{
    @Override
    public Map<String, String> handler(Map<String, String> resourceMap) {
        String url = resourceMap.get("trochilidae.application.url");
        resourceMap.put("trochilidae.application.url", new String(Base64.getDecoder().decode(url)));
        return resourceMap;
    }
}
