package com.way.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 */
@Configuration
@Slf4j
@MapperScan(basePackages = {"com.way.dao_second"},
        sqlSessionFactoryRef = SecondDatasourceConf.SECOND_SESSION_FACTORY)
public class SecondDatasourceConf {
    public static final String SECOND_SESSION_FACTORY = "secondSessionFactory";

    @Primary
    @Bean(name = SECOND_SESSION_FACTORY)
    public SqlSessionFactory secondSessionFactory(// 如果是用的mybatis plus插件，且用了配置，这里配置文件是MybatisPlusProperties properties
                                                  MybatisProperties properties) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 如果是用的mybatis plus插件需要这个类 Invalid bound statement (not found)
        // MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(secondDatasource());
        sqlSessionFactoryBean.setMapperLocations(properties.resolveMapperLocations());
        return sqlSessionFactoryBean.getObject();
    }

    @Primary
    @Bean
    public DataSource secondDatasource() {
        log.debug("init second datasource.");
        return new HikariDataSource(secondHikariConfig());
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-second.hikari")
    public HikariConfig secondHikariConfig() {
        return new HikariConfig();
    }


}