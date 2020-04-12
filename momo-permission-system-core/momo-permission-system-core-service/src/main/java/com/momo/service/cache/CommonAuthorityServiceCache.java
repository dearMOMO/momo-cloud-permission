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
package com.momo.service.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.momo.common.core.entity.RedisUser;
import com.momo.common.core.error.RedisKeyEnum;
import com.momo.common.core.util.LevelUtil;
import com.momo.common.core.util.RedisUtil;
import com.momo.mapper.dataobject.AclDO;
import com.momo.mapper.dataobject.RoleDO;
import com.momo.mapper.req.sysmain.DynamicMenuAuthorReq;
import com.momo.mapper.res.authority.AclLevelRes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.service.cache
 * @Description: 动态权限菜单(第三方)-->Redis
 * @Author: Jie Li
 * @CreateDate:
 * @UpdateDate: 2019-0910 11:22
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Slf4j
@Service
public class CommonAuthorityServiceCache {

    @Autowired
    private CommonSysCoreServiceCache commonSysCoreServiceCache;
    @Autowired
    private RedisUtil redisUtil;

    //动态权限菜单(第三方)
    public List<AclLevelRes> dynamicMenuTree(DynamicMenuAuthorReq loginAuthReq, RedisUser redisUser) {
        List<AclDO> userAclList = commonSysCoreServiceCache.getUserAclList(loginAuthReq, redisUser);
        //获取第三方管理员权限id列表
        String redisAdminKey = RedisKeyEnum.REDIS_ADMIN_ROLE_STR.getKey() + redisUser.getTenantId();
        Object roleDoObj = redisUtil.get(redisAdminKey);
        if (null == roleDoObj) {
            return Lists.newArrayList();
        }
        RoleDO roleDO = JSON.parseObject(String.valueOf(roleDoObj), new TypeReference<RoleDO>() {
        });
        if (roleDO.getDisabledFlag().equals(1) || roleDO.getDelFlag().equals(1)) {
            return Lists.newArrayList();
        }
        Object aclIdsObj = redisUtil.hget(RedisKeyEnum.REDIS_ROLE_ACLS_MAP.getKey() + roleDO.getId(), loginAuthReq.getAclPermissionCode());
        if (null == aclIdsObj) {
            return Lists.newArrayList();
        }

        List<Long> adminAclIds = JSON.parseObject(String.valueOf(aclIdsObj), new TypeReference<List<Long>>() {
        });
        if (CollectionUtils.isEmpty(adminAclIds)) {
            return Lists.newArrayList();
        }
        List<AclLevelRes> aclDtoList = Lists.newArrayList();
        for (AclDO acl : userAclList) {
            //权限继承
            //企业员工权限列表不能大于该企业下管理员(老板)权限列表
            if (acl.getDisabledFlag().equals(0) && acl.getDelFlag().equals(0)) {
                if (adminAclIds.contains(acl.getId())) {
                    AclLevelRes dto = AclLevelRes.adapt(acl);
                    dto.setHasAcl(true);
                    dto.setDisabled(false);
                    dto.setChecked(true);
                    aclDtoList.add(dto);
                }
            }
        }
        return aclListToTree(aclDtoList);
    }

    private List<AclLevelRes> aclListToTree(List<AclLevelRes> aclDtoList) {
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
                Collections.sort(tempDeptList, deptSeqComparator);
                // 设置下一层部门
                deptLevelDto.setChildren(tempDeptList);
                // 进入到下一层处理
                transformDeptTree(tempDeptList, nextLevel, levelDeptMap);
            }
        }
    }

    private Comparator<AclLevelRes> deptSeqComparator = (o1, o2) -> o2.getSysAclSeq() - o1.getSysAclSeq();
}
