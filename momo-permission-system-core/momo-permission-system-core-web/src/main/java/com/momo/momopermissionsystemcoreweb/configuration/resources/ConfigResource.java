package com.momo.momopermissionsystemcoreweb.configuration.resources;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by 李杰 on 2018/7/26.
 */
@Configuration
@ConfigurationProperties(prefix = "com.momo")
@PropertySource(value = "classpath:config/resource.properties",encoding = "UTF-8")
public class ConfigResource {
    private String name;
    private String website;
    private String language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
