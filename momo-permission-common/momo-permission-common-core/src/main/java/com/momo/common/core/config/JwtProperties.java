package com.momo.common.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * jwt相关配置
 */
@Configuration
@ConfigurationProperties(prefix = JwtProperties.JWT_PREFIX)
@PropertySource(value = "classpath:config/jwt.properties",encoding = "UTF-8")
public class JwtProperties {

    public static final String JWT_PREFIX = "jwt";
    @Value("${jwt.header}")
    private String header = "Authorization";
    @Value("${jwt.secret}")
    private String secret = "mySecret";
    @Value("${jwt.expiration}")
    private Long expiration = 604800L;
    @Value("${jwt.auth-path}")
    private String authPath = "auth";
    @Value("${jwt.md5-key}")
    private String md5Key = "randomKey";
    @Value("${jwt.ignore-url}")
    private String ignoreUrl = "";

    public String getIgnoreUrl() {
        return ignoreUrl;
    }

    public void setIgnoreUrl(String ignoreUrl) {
        this.ignoreUrl = ignoreUrl;
    }

    public static String getJwtPrefix() {
        return JWT_PREFIX;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getAuthPath() {
        return authPath;
    }

    public void setAuthPath(String authPath) {
        this.authPath = authPath;
    }

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }
}
