package com.upuphub.trochilidae.core.scanner;

import com.upuphub.trochilidae.core.annotation.bean.ComponentScan;
import com.upuphub.trochilidae.core.factory.ClassFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * 类扫描相关的实现注解
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-16 16:38
 **/
@ComponentScan("com.upuphub.trochilidae")
public class ClassScannerTest {

    @Test
    public void scanClassByPackageName(){
        ClassFactory.loadClass(this.getClass());
        assertEquals(1, ClassFactory.CLASSES.size());
    }

}
