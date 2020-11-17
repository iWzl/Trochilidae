package com.upuphub.trochilidae.core.factory;

import com.upuphub.trochilidae.core.common.util.IocUtil;
import com.upuphub.trochilidae.core.common.util.ReflectionUtil;
import com.upuphub.trochilidae.core.config.Configuration;
import com.upuphub.trochilidae.core.config.ConfigurationManager;
import com.upuphub.trochilidae.core.config.DefaultConfiguration;
import com.upuphub.trochilidae.core.config.ResourceConfigurationPostProcess;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 配置需要的相关管理器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-16 18:06
 **/
public class ConfigurationFactory {
    private static List<ResourceConfigurationPostProcess> resourceConfigurationPostProcessList = new LinkedList<>();

    public static Configuration getDefaultConfig() {
        return SingleConfigurationHolder.INSTANCE_CONFIGURATION;
    }


    public static void loadConfigurationManger(Class<?> bootstrapClazz){
        ConfigurationManager configurationManager = SingleConfigurationHolder.INSTANCE_CONFIGURATION_MANAGER;
        BeanFactory.insertBean(IocUtil.getBeanName(ConfigurationManager.class),configurationManager);
        initResourceConfigurationPostProcessList(bootstrapClazz);
        configurationManager.loadConfigurationResources(bootstrapClazz);
    }


    private static void initResourceConfigurationPostProcessList(Class<?> bootstrapClazz){
        Set<Class<? extends ResourceConfigurationPostProcess>> resourceConfigurationPostProcessList
                = ReflectionUtil.getSubClass(bootstrapClazz.getPackage().getName(), ResourceConfigurationPostProcess.class);
        if(null != resourceConfigurationPostProcessList && 0 != resourceConfigurationPostProcessList.size()){
            for (Class<? extends ResourceConfigurationPostProcess>  resourceConfigurationPostProcessClazz: resourceConfigurationPostProcessList) {
                ResourceConfigurationPostProcess resourceConfigurationPostProcess = (ResourceConfigurationPostProcess)
                        BeanFactory.getInstanceByClazz(resourceConfigurationPostProcessClazz);
                if(null == resourceConfigurationPostProcess){
                    resourceConfigurationPostProcess = ReflectionUtil.newInstance(resourceConfigurationPostProcessClazz);
                }
                ConfigurationFactory.resourceConfigurationPostProcessList.add(resourceConfigurationPostProcess);
                BeanFactory.insertBean(IocUtil.getBeanName(resourceConfigurationPostProcessClazz),resourceConfigurationPostProcess);
            }
            ConfigurationFactory.resourceConfigurationPostProcessList = ConfigurationFactory.resourceConfigurationPostProcessList.stream()
                    .sorted(Comparator.comparing(ResourceConfigurationPostProcess::getOrder)).collect(Collectors.toList());
        }
    }

    public static Map<String, String> runResourceConfigurationPostProcess(Map<String, String> resourceConfigurationMap){
        for (ResourceConfigurationPostProcess resourceConfigurationPostProcess : resourceConfigurationPostProcessList) {
            resourceConfigurationMap = resourceConfigurationPostProcess.handler(resourceConfigurationMap);
        }
        return resourceConfigurationMap;
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
