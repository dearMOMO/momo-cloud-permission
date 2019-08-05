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
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by MOMO on 2019/4/9.
 */
@Service
public class CommonSysCoreService {

    @Autowired
    private AuthorityMapper authorityMapper;

    public List<AclDO> getRoleAclList(Set<Long> roleIds, Long aclPermissionType) {
        List<Long> aclIdList = authorityMapper.aclsByRoleId(roleIds, aclPermissionType);
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
        Set<Long> userRoleIdListSet = userRoleIdList.stream().map(aLong -> aLong).collect(Collectors.toSet());
        List<Long> userAclIdList = authorityMapper.aclsByRoleId(userRoleIdListSet, loginAuthReq.getAclPermissionType());
        if (CollectionUtils.isEmpty(userAclIdList)) {
            return Lists.newArrayList();
        }
        return authorityMapper.getAllAcl(loginAuthReq.getAclPermissionType(), userAclIdList);
    }
}
