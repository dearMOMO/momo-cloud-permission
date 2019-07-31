package com.momo.service.service.authority;

import com.google.common.collect.Lists;
import com.momo.mapper.dataobject.AclDO;
import com.momo.mapper.mapper.manual.AuthorityMapper;
import com.momo.mapper.req.sysmain.LoginAuthReq;
import com.momo.mapper.req.sysmain.RedisUser;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MOMO on 2019/4/9.
 */
@Service
public class CommonSysCoreService {

    @Autowired
    private AuthorityMapper authorityMapper;

    public List<AclDO> getRoleAclList(Long roleId, Long aclPermissionType) {
        List<Long> aclIdList = authorityMapper.aclsByRoleId(Lists.<Long>newArrayList(roleId), aclPermissionType);
        if (CollectionUtils.isEmpty(aclIdList)) {
            return Lists.newArrayList();
        }
        return authorityMapper.getAllAcl(null, aclIdList);
    }

    public List<AclDO> getUserHavingAclList(LoginAuthReq loginAuthReq, RedisUser redisUser) {

        List<Long> userRoleIdList = authorityMapper.rolesByUserId(redisUser.getBaseId());
        if (CollectionUtils.isEmpty(userRoleIdList)) {
            return Lists.newArrayList();
        }
        List<Long> userAclIdList = authorityMapper.aclsByRoleId(userRoleIdList, loginAuthReq.getAclPermissionType());
        if (CollectionUtils.isEmpty(userAclIdList)) {
            return Lists.newArrayList();
        }
        return authorityMapper.getAllAcl(loginAuthReq.getAclPermissionType(), userAclIdList);
    }
}
