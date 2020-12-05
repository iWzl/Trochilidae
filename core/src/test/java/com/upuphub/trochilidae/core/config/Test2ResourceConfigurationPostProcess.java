package com.upuphub.trochilidae.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 测试配置后处理器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-17 11:14
 **/
public class Test2ResourceConfigurationPostProcess implements ResourceConfigurationPostProcess{
    private static final Logger logger = LoggerFactory.getLogger( Test2ResourceConfigurationPostProcess.class);

    @Override
    public Map<String, String> processingHandler(Map<String, String> resourceMap) {
        logger.info("Test2ResourceConfigurationPostProcess Handler Running ....");
        return resourceMap;
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
