package com.momo.service.service.authority;

import com.google.common.collect.*;
import com.momo.common.util.LevelUtil;
import com.momo.mapper.dataobject.AclDO;
import com.momo.mapper.dataobject.RoleAclDO;
import com.momo.mapper.mapper.manual.AuthorityMapper;
import com.momo.mapper.req.sysmain.LoginAuthReq;
import com.momo.mapper.req.sysmain.RedisUser;
import com.momo.mapper.res.authority.AclLevelRes;
import com.momo.mapper.res.authority.AclTreeRes;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by MOMO on 2019/4/9.
 */
@Service
public class CommonAuthorityService {
    @Autowired
    private CommonSysCoreService commonSysCoreService;
    @Autowired
    private AuthorityMapper authorityMapper;

    //为角色授权 权限 之前， 需要查看该角色拥有哪些权限点，以及当前登录用户可以操作哪些权限
    public AclTreeRes roleTree(LoginAuthReq loginAuthReq, RedisUser redisUser) {
        // 1、当前用户已分配的权限点
        List<AclDO> userAclList = commonSysCoreService.getUserHavingAclList(loginAuthReq, redisUser);
        // 2、当前角色分配的权限点
        List<AclDO> roleAclList = commonSysCoreService.getRoleAclList(Sets.newHashSet(loginAuthReq.getRoleId()), loginAuthReq.getAclPermissionType());
        // 3、当前系统所有权限点
        List<AclLevelRes> aclDtoList = Lists.newArrayList();
        Set<Long> userAclIdSet = userAclList.stream().map(sysAcl -> sysAcl.getId()).collect(Collectors.toSet());
        Set<Long> roleAclIdSet = roleAclList.stream().map(sysAcl -> sysAcl.getId()).collect(Collectors.toSet());
        //获取第三方管理员角色列表
        List<Long> adminRoles = authorityMapper.rolesAdminByGroupId(redisUser.getTenantId());
        Set<Long> adminRolesSet = adminRoles.stream().map(aLong -> aLong).collect(Collectors.toSet());
        //根据角色id获取权限ids
        List<Long> aclIds = authorityMapper.aclsByRoleId(adminRolesSet, loginAuthReq.getAclPermissionType());
        List<String> defaultexpandedKeys = Lists.newArrayList();
        //根据权限点的ids获取权限点列表
        List<AclDO> allAclList = authorityMapper.getAllAcl(null, aclIds);
        for (AclDO acl : allAclList) {
            AclLevelRes dto = AclLevelRes.adapt(acl);
            if (userAclIdSet.contains(acl.getId())) {
                dto.setHasAcl(true);
                dto.setDisabled(false);
            }
            if (roleAclIdSet.contains(acl.getId())) {
                //类型，-1系统 0:目录 1：菜单，2：按钮，3：其他
                if (acl.getSysAclType().equals(1) || acl.getSysAclType().equals(2) || acl.getSysAclType().equals(3)) {
                    dto.setChecked(true);
                    defaultexpandedKeys.add(String.valueOf(acl.getId()));
                }
            }
            aclDtoList.add(dto);
        }
        AclTreeRes aclTreeRes = new AclTreeRes();
        List<AclLevelRes> aclListToTree = deptListToTree(aclDtoList);
        aclTreeRes.setDefaultexpandedKeys(defaultexpandedKeys);
        aclTreeRes.setAclLevelRes(aclListToTree);
        return aclTreeRes;
    }

    private List<AclLevelRes> deptListToTree(List<AclLevelRes> aclDtoList) {
        if (CollectionUtils.isEmpty(aclDtoList)) {
            return Lists.newArrayList();
        }
        // level -> [dept1, dept2, ...] Map<String, List<Object>>
        Multimap<String, AclLevelRes> levelDeptMap = ArrayListMultimap.create();
        List<AclLevelRes> rootList = Lists.newArrayList();

        for (AclLevelRes dto : aclDtoList) {
            levelDeptMap.put(dto.getSysAclLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getSysAclLevel())) {
                rootList.add(dto);
            }
        }
        // 按照seq从小到大排序
        Collections.sort(rootList, new Comparator<AclLevelRes>() {
            @Override
            public int compare(AclLevelRes o1, AclLevelRes o2) {
                return o1.getSysAclSeq() - o2.getSysAclSeq();
            }
        });
        // 递归生成树
        transformDeptTree(rootList, LevelUtil.ROOT, levelDeptMap);
        return rootList;
    }

    // level:0, 0, all 0->0.1,0.2
    // level:0.1
    // level:0.2
    private void transformDeptTree(List<AclLevelRes> deptLevelList, String level, Multimap<String, AclLevelRes> levelDeptMap) {
        for (int i = 0; i < deptLevelList.size(); i++) {
            // 遍历该层的每个元素
            AclLevelRes deptLevelDto = deptLevelList.get(i);
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDto.getId());
            // 处理下一层
            List<AclLevelRes> tempDeptList = (List<AclLevelRes>) levelDeptMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempDeptList)) {
                // 排序
                Collections.sort(tempDeptList, deptSeqComparator);
                // 设置下一层部门
                deptLevelDto.setChildren(tempDeptList);
                // 进入到下一层处理
                transformDeptTree(tempDeptList, nextLevel, levelDeptMap);
            }
        }
    }

    private Comparator<AclLevelRes> deptSeqComparator = new Comparator<AclLevelRes>() {
        @Override
        public int compare(AclLevelRes o1, AclLevelRes o2) {
            return o1.getSysAclSeq() - o2.getSysAclSeq();
        }
    };
}
