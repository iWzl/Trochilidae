package com.upuphub.trochilidae.core.config;

import com.upuphub.trochilidae.core.factory.ConfigurationFactory;
import org.junit.jupiter.api.Test;

/**
 * 资源加载配置
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-16 20:26
 **/
public class ResourceConfigLoadTest {
    @Test
    public void propertiesResourceLoadTest(){
        ConfigurationFactory.getDefaultConfigurationManager().loadConfigurationResources(this.getClass());
    }
}
