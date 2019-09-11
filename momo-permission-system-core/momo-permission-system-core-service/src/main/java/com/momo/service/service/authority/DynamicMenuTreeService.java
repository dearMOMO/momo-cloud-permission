package com.momo.service.service.authority;

import com.momo.mapper.req.sysmain.DynamicMenuAuthorReq;
import com.momo.mapper.req.sysmain.RedisUser;
import com.momo.mapper.res.authority.AclLevelRes;
import com.momo.service.cache.AdminAuthorityServiceCache;
import com.momo.service.cache.CommonAuthorityServiceCache;
import com.momo.service.service.BaseService;
import com.momo.service.service.SuperAdminsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
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
        =    List<AclLevelRes> dynamicMenuTree = adminAuthorityServiceCache.dynamicMenuTree(loginAuthReq, redisUser);
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
