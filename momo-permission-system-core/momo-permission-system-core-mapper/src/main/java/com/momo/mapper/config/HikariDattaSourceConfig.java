package com.momo.mapper.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 李杰 on 2018/10/20.
 */
@Configuration
//@ConfigurationProperties(prefix = "spring.datasource")
public class HikariDattaSourceConfig {
    @Value("${spring.datasource.jdbc-url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.type}")
    private String type;
    @Value("${spring.datasource.continue-on-error}")
    private String continueOnError;
    @Value("${spring.datasource.hikari.auto-commit}")
    private String autoCommit;
    @Value("${spring.datasource.hikari.idle-timeout}")
    private String idleTimeout;
    @Value("${spring.datasource.hikari.pool-name}")
    private String poolName;
    @Value("${spring.datasource.hikari.max-lifetime}")
    private String maxlifetime;
    @Value("${spring.datasource.hikari.connection-timeout}")
    private String connectionTimeout;
    @Value("${spring.datasource.hikari.minimum-idle}")
    private String minimumTdle;
    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private String maximumPoolSize;
    @Value("${spring.datasource.hikari.connection-test-query}")
    private String connectionTestQuery;

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContinueOnError() {
        return continueOnError;
    }

    public void setContinueOnError(String continueOnError) {
        this.continueOnError = continueOnError;
    }

    public String getAutoCommit() {
        return autoCommit;
    }

    public void setAutoCommit(String autoCommit) {
        this.autoCommit = autoCommit;
    }

    public String getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(String idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public String getMaxlifetime() {
        return maxlifetime;
    }

    public void setMaxlifetime(String maxlifetime) {
        this.maxlifetime = maxlifetime;
    }

    public String getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(String connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String getMinimumTdle() {
        return minimumTdle;
    }

    public void setMinimumTdle(String minimumTdle) {
        this.minimumTdle = minimumTdle;
    }

    public String getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(String maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public String getConnectionTestQuery() {
        return connectionTestQuery;
    }

    public void setConnectionTestQuery(String connectionTestQuery) {
        this.connectionTestQuery = connectionTestQuery;
    }
}
