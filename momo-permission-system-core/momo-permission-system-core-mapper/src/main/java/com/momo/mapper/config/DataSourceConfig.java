/**
 * Copyright (c) 2018-2019, Jie Li 李杰 (mqgnsds@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.momo.mapper.config;

import com.github.pagehelper.PageInterceptor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by 李杰 on 2018/10/20.
 *
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(basePackages = DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "primarySqlSessionFactory")
public class DataSourceConfig {
    private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
    private static String MYBATIS_CONFIG = "classpath:/mybatis-config.xml";
    /**
     * mybatis masterMapper resource 路径
     */
    private static String MAPPER_PATH = "/com/momo/mapper/**/*.xml";
    private static String TYPE_ALIASES_Package = "com.momo.mapper.dataobject";
    static final String PACKAGE = "com.momo.mapper";
    @Autowired
    private HikariDattaSourceConfig hikariDattaSourceConfig;

    @Bean(name = "primaryDataSource")
    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariDataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(hikariDattaSourceConfig.getDriverClassName());
        hikariConfig.setJdbcUrl(hikariDattaSourceConfig.getJdbcUrl());
        hikariConfig.setUsername(hikariDattaSourceConfig.getUsername());
        hikariConfig.setPassword(hikariDattaSourceConfig.getPassword());
        hikariConfig.setMaxLifetime(Integer.valueOf(hikariDattaSourceConfig.getMaxlifetime()));
        hikariConfig.setConnectionTestQuery(hikariDattaSourceConfig.getConnectionTestQuery());
        hikariConfig.setPoolName(hikariDattaSourceConfig.getPoolName());
        hikariConfig.setIdleTimeout(Integer.valueOf(hikariDattaSourceConfig.getIdleTimeout()));
        hikariConfig.setAutoCommit(true);
        hikariConfig.setConnectionTimeout(Integer.valueOf(hikariDattaSourceConfig.getConnectionTimeout()));
        hikariConfig.setMinimumIdle(Integer.valueOf(hikariDattaSourceConfig.getMinimumTdle()));
        hikariConfig.setMaximumPoolSize(Integer.valueOf(hikariDattaSourceConfig.getMaximumPoolSize()));
        hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
        hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "primaryTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(this.dataSource());
    }

    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setTypeAliasesPackage(TYPE_ALIASES_Package);
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_PATH;

        //添加插件
        sessionFactory.setPlugins(new Interceptor[]{pageInterceptor()});

        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sessionFactory.getObject();
    }

    private PageInterceptor pageInterceptor() {
        //分页插件
        PageInterceptor pageHelper = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "false");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        properties.setProperty("autoRuntimeDialect", "true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
