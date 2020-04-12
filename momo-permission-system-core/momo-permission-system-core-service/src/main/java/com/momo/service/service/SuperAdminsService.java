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
 * @Description: 这里是我自己定义了一个超级管理员规则，可以是配置文件获取，
 * 可以指定某个用户，也可以指定某个角色
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
    private Long teantId;
    private List<String> superAdmins = Lists.newArrayList();

    /**
     * true->超级管理员
     *
     * @param sysUserPhone
     * @return
     */
    public boolean checkIsSuperAdmin(String sysUserPhone) {
        if (StringUtils.isBlank(sysUserPhone)) {
            return false;
        }
        return superAdmins.contains(sysUserPhone);
    }
}
