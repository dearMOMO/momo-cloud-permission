package com.momo.service.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.service.service
 * @Description: TODO
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
public class SuperAdminsService {
    private List<String> superAdmins = Lists.newArrayList();

    public boolean checkIsSuperAdmin(String sysUserPhone) {
        if (StringUtils.isBlank(sysUserPhone)) {
            return false;
        }
        return superAdmins.contains(sysUserPhone);
    }
}
