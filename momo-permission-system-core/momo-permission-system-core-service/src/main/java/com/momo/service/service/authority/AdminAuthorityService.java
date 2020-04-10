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

import com.google.common.collect.*;
import com.momo.common.core.entity.RedisUser;
import com.momo.common.core.util.LevelUtil;
import com.momo.mapper.dataobject.AclDO;
import com.momo.mapper.enums.AclTypeEnum;
import com.momo.mapper.enums.DisabledFlagEnum;
import com.momo.mapper.mapper.manual.AuthorityMapper;
import com.momo.mapper.req.sysmain.DynamicMenuAuthorReq;
import com.momo.mapper.res.authority.AclLevelRes;
import com.momo.mapper.res.authority.AclTreeRes;
import com.momo.service.service.BaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: momo-cloud-permission
 * @description: 动态权限菜单(总部)
 * @author: Jie Li
 * @create: 2019-07-30 22:44
 **/
@Service
public class AdminAuthorityService extends BaseService {

    @Autowired
    private AdminSysCoreService adminSysCoreService;
    @Autowired
    private AuthorityMapper authorityMapper;

    //动态权限菜单(总部)
    public List<AclLevelRes> dynamicMenuTree(DynamicMenuAuthorReq loginAuthReq, RedisUser redisUser) {
        List<AclDO> userAclList = adminSysCoreService.getUserAclList(loginAuthReq, redisUser);
        List<AclLevelRes> aclDtoList = Lists.newArrayList();
        for (AclDO acl : userAclList) {
            if (acl.getDisabledFlag().equals(DisabledFlagEnum.start.type)) {
                AclLevelRes dto = AclLevelRes.adapt(acl);
                dto.setHasAcl(true);
                dto.setDisabled(false);
                dto.setChecked(true);
                aclDtoList.add(dto);
            }
        }
        return aclListToTree(aclDtoList);
    }

    //为角色授权 权限 之前， 需要查看该角色拥有哪些权限点，以及当前登录用户可以操作哪些权限
    public AclTreeRes roleTree(DynamicMenuAuthorReq loginAuthReq, RedisUser redisUser) {
        // 1、当前用户已分配的权限点
        List<AclDO> userAclList = adminSysCoreService.getUserHavingAclList(loginAuthReq, redisUser);
        // 2、当前角色分配的权限点
        List<AclDO> roleAclList = adminSysCoreService.getRoleAclList(Sets.newHashSet(loginAuthReq.getRoleId()), loginAuthReq.getAclPermissionCode());
        // 3、当前系统所有权限点
        List<AclLevelRes> aclDtoList = Lists.newArrayList();

        Set<Long> userAclIdSet = userAclList.stream().map(AclDO::getId).collect(Collectors.toSet());
        Set<Long> roleAclIdSet = roleAclList.stream().map(AclDO::getId).collect(Collectors.toSet());
        List<AclDO> allAclList = authorityMapper.getAllAcl(null, null);
        List<String> defaultexpandedKeys = Lists.newArrayList();
        for (AclDO acl : allAclList) {
            //状态 0启用  1禁用
//            if ("0".equals(acl.getSysAclState())){
            AclLevelRes dto = AclLevelRes.adapt(acl);
            if (userAclIdSet.contains(acl.getId())) {
                dto.setHasAcl(true);
                dto.setDisabled(false);
            }
            if (roleAclIdSet.contains(acl.getId())) {
                //类型，-1系统 0:目录 1：菜单，2：按钮，3：其他
                if (acl.getSysAclType().equals(AclTypeEnum.button.type) || acl.getSysAclType().equals(AclTypeEnum.other.type)) {
                    dto.setChecked(true);
                    defaultexpandedKeys.add(String.valueOf(acl.getId()));
                }
            }
            aclDtoList.add(dto);
//            }
        }
        AclTreeRes aclTreeRes = new AclTreeRes();
        List<AclLevelRes> aclListToTree = aclListToTree(aclDtoList);
        aclTreeRes.setDefaultexpandedKeys(defaultexpandedKeys);
        aclTreeRes.setAclLevelRes(aclListToTree);
        return aclTreeRes;
    }

    public List<AclLevelRes> aclListToTree(List<AclLevelRes> aclDtoList) {
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
        // 按照seq从大到小排序
        rootList.sort((o1, o2) -> o2.getSysAclSeq() - o1.getSysAclSeq());
        // 递归生成树
        transformDeptTree(rootList, LevelUtil.ROOT, levelDeptMap);
        return rootList;
    }

    // level:0, 0, all 0->0.1,0.2
    // level:0.1
    // level:0.2
    private void transformDeptTree(List<AclLevelRes> deptLevelList, String level, Multimap<String, AclLevelRes> levelDeptMap) {
        for (AclLevelRes deptLevelDto : deptLevelList) {
            // 遍历该层的每个元素
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDto.getId());
            // 处理下一层
            List<AclLevelRes> tempDeptList = (List<AclLevelRes>) levelDeptMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempDeptList)) {
                // 排序
                tempDeptList.sort(deptSeqComparator);
                // 设置下一层部门
                deptLevelDto.setChildren(tempDeptList);
                // 进入到下一层处理
                transformDeptTree(tempDeptList, nextLevel, levelDeptMap);
            }
        }
    }

    private Comparator<AclLevelRes> deptSeqComparator = (o1, o2) -> o2.getSysAclSeq() - o1.getSysAclSeq();
}
