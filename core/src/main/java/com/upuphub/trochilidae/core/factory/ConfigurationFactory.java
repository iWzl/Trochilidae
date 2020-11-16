package com.upuphub.trochilidae.core.factory;

import com.upuphub.trochilidae.core.config.Configuration;
import com.upuphub.trochilidae.core.config.ConfigurationManager;
import com.upuphub.trochilidae.core.config.DefaultConfiguration;

/**
 * 配置需要的相关管理器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-16 18:06
 **/
public class ConfigurationFactory {
    public static Configuration getConfig() {
        return SingleConfigurationHolder.INSTANCE_CONFIGURATION;
    }

    public static ConfigurationManager getDefaultConfigurationManager(){
        return SingleConfigurationHolder.INSTANCE_CONFIGURATION_MANAGER;
    }

    private static class SingleConfigurationHolder {

        private static final Configuration INSTANCE_CONFIGURATION = buildConfiguration();

        private static final ConfigurationManager INSTANCE_CONFIGURATION_MANAGER = buildConfigurationManager();

        private static Configuration buildConfiguration() {
            return new DefaultConfiguration();
        }

        private static ConfigurationManager buildConfigurationManager() {
            return new ConfigurationManager(INSTANCE_CONFIGURATION);
        }
    }

}
