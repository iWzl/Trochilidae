package com.upuphub.trochilidae.orm.mybatis.dataSource;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.util.Properties;

/**
 * DataSourceLoader
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-01-27 22:06
 **/

public final class DataSourceLoader {
    public static UnpooledDataSource createUnpooledDataSource(String resource) throws IOException {
        Properties props = Resources.getResourceAsProperties(resource);
        UnpooledDataSource ds = new UnpooledDataSource();
        ds.setDriver(props.getProperty("driver"));
        ds.setUrl(props.getProperty("url"));
        ds.setUsername(props.getProperty("username"));
        ds.setPassword(props.getProperty("password"));
        return ds;
    }

}
