package com.momo.momopermissiongateway.configuration;

import com.google.common.collect.Lists;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.service.service
 * @Description: 拦截URL配置
 * @Author: Jie Li
 * @CreateDate: 2019/8/24 0024 13:33
 * @UpdateDate: 2019/8/24 0024 13:33
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Configuration
@ConfigurationProperties("momo")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = {"id"})
public class InterceptUrlConfiguration {
    private List<String> ignorerUrl = Lists.newArrayList();

    public boolean checkIgnoreUrl(String sysUserPhone) {
        if (StringUtils.isBlank(sysUserPhone)) {
            return false;
        }
        return ignorerUrl.contains(sysUserPhone);
    }
}
