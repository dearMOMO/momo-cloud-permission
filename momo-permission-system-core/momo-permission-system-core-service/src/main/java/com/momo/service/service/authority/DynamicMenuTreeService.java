package com.momo.service.service.authority;

import com.momo.mapper.req.sysmain.LoginAuthReq;
import com.momo.mapper.req.sysmain.RedisUser;
import com.momo.mapper.res.authority.AclLevelRes;
import com.momo.service.service.BaseService;
import lombok.extern.slf4j.Slf4j;
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

    public List<AclLevelRes> dynamicMenuTree(LoginAuthReq loginAuthReq) {
        RedisUser redisUser = this.redisUser();
        if (redisUser.getTenantId().equals(1L)) {
            return adminAuthorityService.dynamicMenuTree(loginAuthReq, redisUser);
        } else {
            return commonAuthorityService.dynamicMenuTree(loginAuthReq, redisUser);
        }
    }
}
