package com.upuphub.trochilidae.core.ioc;

import com.upuphub.trochilidae.core.factory.BeanFactory;

/**
 * 依赖注入
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-17 18:32
 **/
public class DependencyInjection {

    /**
     * 遍历ioc容器所有bean的属性, 为所有带@Autowired注解的属性注入实例
     */
    public static void inject(String[] packageNames) {
        AutowiredBeanInitialization autowiredBeanInitialization = new AutowiredBeanInitialization(packageNames);
        //创建好的bean都放入对象工厂
        if (BeanFactory.BEANS.size() > 0) {
            BeanFactory.BEANS.values().forEach(autowiredBeanInitialization::initialize);
        }
    }
}
