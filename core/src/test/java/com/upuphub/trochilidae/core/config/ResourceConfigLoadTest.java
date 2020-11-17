package com.upuphub.trochilidae.core.config;

import com.upuphub.trochilidae.core.factory.BeanFactory;
import com.upuphub.trochilidae.core.factory.ConfigurationFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 资源加载配置
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-16 20:26
 **/
public class ResourceConfigLoadTest {

    @BeforeAll
    public static void initResourceLoad(){
        ConfigurationFactory.loadConfigurationManger(ResourceConfigLoadTest.class);
    }

    @Test
    @DisplayName("Resource Get From Configuration Factory")
    public void propertiesResourceLoadFromFactoryTest(){
        assertEquals(ConfigurationFactory.getDefaultConfig().getString("trochilidae.application.name"),"Trochilidae-Test");
        assertEquals(ConfigurationFactory.getDefaultConfig().getString("trochilidae.application.url"),"Trochilidae-URL");
    }


    @Test
    @DisplayName("Resource Get From Bean Container")
    public void propertiesResourceLoadFromBeanContainerTest(){
        Configuration configuration =(Configuration)BeanFactory.getInstanceByClazz(ConfigurationManager.class);
        assertEquals(configuration.getString("trochilidae.application.name"),"Trochilidae-Test");
        assertEquals(configuration.getString("trochilidae.application.url"),"Trochilidae-URL");
    }
}
