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
    @DisplayName("Resource Get From Configuration Factory - String")
    public void propertiesResourceLoadFromFactoryTestString(){
        assertEquals(ConfigurationFactory.getDefaultConfig().getString("trochilidae.application.name"),"Trochilidae-Test");
    }

    @Test
    @DisplayName("Resource Get From Configuration Factory - ReLoad-String")
    public void propertiesResourceLoadFromFactoryTestReloadString(){
        assertEquals(ConfigurationFactory.getDefaultConfig().getString("trochilidae.application.url"),"Trochilidae-URL");
    }

    @Test
    @DisplayName("Resource Get From Configuration Factory - Int")
    public void propertiesResourceLoadFromFactoryTestInt(){
        assertEquals(ConfigurationFactory.getDefaultConfig().getInt("trochilidae.application.numberInt"),123456);
    }

    @Test
    @DisplayName("Resource Get From Configuration Factory - Long")
    public void propertiesResourceLoadFromFactoryTestLong(){
        assertEquals(ConfigurationFactory.getDefaultConfig().getLong("trochilidae.application.numberLong"),1234567899876L);
    }

    @Test
    @DisplayName("Resource Get From Configuration Factory - Bool")
    public void propertiesResourceLoadFromFactoryTestBool(){
        assertEquals(ConfigurationFactory.getDefaultConfig().getBoolean("trochilidae.application.bool"),true);
    }

    @Test
    @DisplayName("Resource Get From Configuration Factory - Double")
    public void propertiesResourceLoadFromFactoryTestDouble(){
        assertEquals(ConfigurationFactory.getDefaultConfig().getDouble("trochilidae.application.numberDouble"),12345.6789);
    }


    @Test
    @DisplayName("Resource Get From Bean Container - String")
    public void propertiesResourceLoadFromBeanContainerTestString(){
        Configuration configuration =(Configuration)BeanFactory.getInstanceByClazz(ConfigurationManager.class);
        assertEquals(configuration.getString("trochilidae.application.name"),"Trochilidae-Test");
    }

    @Test
    @DisplayName("Resource Get From Bean Container - ReLoad-String")
    public void propertiesResourceLoadFromBeanContainerTestReloadString(){
        Configuration configuration =(Configuration)BeanFactory.getInstanceByClazz(ConfigurationManager.class);
        assertEquals(configuration.getString("trochilidae.application.url"),"Trochilidae-URL");
    }

    @Test
    @DisplayName("Resource Get From Bean Container - Int")
    public void propertiesResourceLoadFromBeanContainerTestInt(){
        Configuration configuration =(Configuration)BeanFactory.getInstanceByClazz(ConfigurationManager.class);
        assertEquals(configuration.getInt("trochilidae.application.numberInt"),123456);
    }

    @Test
    @DisplayName("Resource Get From Bean Container - Long")
    public void propertiesResourceLoadFromBeanContainerTestLong(){
        Configuration configuration =(Configuration)BeanFactory.getInstanceByClazz(ConfigurationManager.class);
        assertEquals(configuration.getLong("trochilidae.application.numberLong"),1234567899876L);
    }

    @Test
    @DisplayName("Resource Get From Bean Container - Bool")
    public void propertiesResourceLoadFromBeanContainerTestBool(){
        Configuration configuration =(Configuration)BeanFactory.getInstanceByClazz(ConfigurationManager.class);
        assertEquals(configuration.getBoolean("trochilidae.application.bool"),true);
    }

    @Test
    @DisplayName("Resource Get From Bean Container - Double")
    public void propertiesResourceLoadFromBeanContainerTestDouble(){
        Configuration configuration =(Configuration)BeanFactory.getInstanceByClazz(ConfigurationManager.class);
        assertEquals(configuration.getDouble("trochilidae.application.numberDouble"),12345.6789);
    }
}
