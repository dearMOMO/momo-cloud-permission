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
package com.momo.service.service.authority;

import com.momo.common.core.entity.RedisUser;
import com.momo.mapper.req.sysmain.DynamicMenuAuthorReq;
import com.momo.mapper.res.authority.AclLevelRes;
import com.momo.service.cache.AdminAuthorityServiceCache;
import com.momo.service.cache.CommonAuthorityServiceCache;
import com.momo.service.service.BaseService;
import com.momo.service.service.SuperAdminsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.service.service.authority
 * @Description: 动态权限菜单
 * @Author: Jie Li
 * @CreateDate: 2019/9/3 0003 16:43
 * @UpdateDate: 2019/9/3 0003 16:43
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Slf4j
@Service
public class DynamicMenuTreeService extends BaseService {
    @Autowired
    private CommonAuthorityService commonAuthorityService;
    @Autowired
    private AdminAuthorityService adminAuthorityService;
    @Autowired
    private SuperAdminsService superAdminsService;
    @Autowired
    private AdminAuthorityServiceCache adminAuthorityServiceCache;
    @Autowired
    private CommonAuthorityServiceCache commonAuthorityServiceCache;

    public List<AclLevelRes> dynamicMenuTree(DynamicMenuAuthorReq loginAuthReq) {
        RedisUser redisUser = this.redisUser();
        //总部权限菜单
        if (redisUser.getTenantId().equals(superAdminsService.getTeantId())) {
            //走redis缓存
            List<AclLevelRes> dynamicMenuTree = adminAuthorityServiceCache.dynamicMenuTree(loginAuthReq, redisUser);
            if (CollectionUtils.isNotEmpty(dynamicMenuTree)) {
                return dynamicMenuTree;
            }
            return adminAuthorityService.dynamicMenuTree(loginAuthReq, redisUser);
        } else {//第三方权限菜单
            //走redis缓存
            List<AclLevelRes> dynamicMenuTree = commonAuthorityServiceCache.dynamicMenuTree(loginAuthReq, redisUser);
            if (CollectionUtils.isNotEmpty(dynamicMenuTree)) {
                return dynamicMenuTree;
            }
            return commonAuthorityService.dynamicMenuTree(loginAuthReq, redisUser);
        }
    }
}
