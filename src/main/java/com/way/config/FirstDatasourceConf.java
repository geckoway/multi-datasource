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

import javax.sql.DataSource;

/**
 */
@Configuration
@Slf4j
@MapperScan(basePackages = {"com.way.dao"}, sqlSessionFactoryRef = FirstDatasourceConf.FIRST_SESSION_FACTORY)
public class FirstDatasourceConf {
    public static final String FIRST_SESSION_FACTORY = "firstSessionFactory";

    @Bean(name = FIRST_SESSION_FACTORY)
    public SqlSessionFactory fristSessionFactory(// 如果是用的mybatis plus插件，且用了配置，这里配置文件是MybatisPlusProperties properties
                                                 MybatisProperties properties) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 如果是用的mybatis plus插件需要这个类 Invalid bound statement (not found)
        // MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(firstDataSource());
        sqlSessionFactoryBean.setMapperLocations(properties.resolveMapperLocations());
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public DataSource firstDataSource() {
        log.debug("init first datasource.");
        return new HikariDataSource(firstHikariConfig());
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-first.hikari")
    public HikariConfig firstHikariConfig() {
        return new HikariConfig();
    }

}