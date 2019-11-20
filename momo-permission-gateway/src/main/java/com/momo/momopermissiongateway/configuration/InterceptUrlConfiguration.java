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
    private Long teantId;
    //忽略拦截URL
    private List<String> ignorerUrl = Lists.newArrayList();

    //#企业相关操作URL，只有MOMO企业下的员工才可以操作
    private List<String> enterpriseUrl = Lists.newArrayList();
    private List<String> superAdmins = Lists.newArrayList();

    public boolean checkIsSuperAdmin(String sysUserPhone) {
        if (StringUtils.isBlank(sysUserPhone)) {
            return false;
        }
        return superAdmins.contains(sysUserPhone);
    }

    public boolean checkIgnoreUrl(String gatewayUrl) {
        if (StringUtils.isBlank(gatewayUrl)) {
            return true;
        }
        return ignorerUrl.contains(gatewayUrl);
    }

    public boolean checkEnterpriseUrl(String gatewayUrl) {
        if (StringUtils.isBlank(gatewayUrl)) {
            return true;
        }
        return enterpriseUrl.contains(gatewayUrl);
    }
}
