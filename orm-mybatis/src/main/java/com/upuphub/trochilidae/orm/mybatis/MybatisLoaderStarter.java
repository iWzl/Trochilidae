package com.upuphub.trochilidae.orm.mybatis;


import com.upuphub.trochilidae.orm.mybatis.dataSource.DataSourceLoader;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-01-27 21:41
 **/
public class MybatisLoaderStarter {
    public static void main(String[] args) throws IOException {
        DataSource dataSource = DataSourceLoader.createUnpooledDataSource("");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
//        configuration.addMapper(BlogMapper.class);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }
}
