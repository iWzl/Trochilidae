package com.upuphub.trochilidae.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.Map;

/**
 * 测试配置后处理器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-17 11:14
 **/
public class TestResourceConfigurationPostProcess implements ResourceConfigurationPostProcess{
    private static final Logger logger = LoggerFactory.getLogger( TestResourceConfigurationPostProcess.class);

    @Override
    public Map<String, String> handler(Map<String, String> resourceMap) {
        String url = resourceMap.get("trochilidae.application.url");
        resourceMap.put("trochilidae.application.url", new String(Base64.getDecoder().decode(url)));
        logger.info("TestResourceConfigurationPostProcess Handler Running ....");
        return resourceMap;
    }
}
