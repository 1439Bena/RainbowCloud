package com.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

/**
 * 我 Batis 配置
 *
 * @author CC
 * @date 2024/05/24
 */
@Configuration
@MapperScan("com.mapper")
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        // 设置 MyBatis 的 XML 配置文件路径
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml");
        sessionFactory.setMapperLocations(resources);

        // 注册自定义的 TypeHandler
        sessionFactory.setTypeHandlersPackage("com.handler.AccountAvatarTypeHandler");
        sessionFactory.setTypeHandlersPackage("com.handler.PostPimageTypeHandler");
        sessionFactory.setTypeHandlersPackage("com.handler.PostPvideoTypeHandler");
        sessionFactory.setTypeHandlersPackage("com.handler.PostParentIdTypeHandler");

        return sessionFactory.getObject();
    }
}
