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
package com.momo.service.service.aclmanager.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.momo.common.core.entity.RedisUser;
import com.momo.common.core.util.LevelUtil;
import com.momo.mapper.dataobject.DeptDO;
import com.momo.mapper.mapper.manual.DeptMapper;
import com.momo.mapper.res.aclmanager.DepTreeRes;
import com.momo.service.service.BaseService;
import com.momo.service.service.aclmanager.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;

/**
 * @ClassName: SysDeptServiceImpl
 * @Author: Jie Li
 * @Date 2019-11-15 14:47
 * @Description: 部门管理
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Service
@Slf4j
public class SysDeptServiceImpl extends BaseService implements SysDeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<DepTreeRes> deptTree() {
        RedisUser redisUser = this.redisUser();
        List<DeptDO> deptGetAll = deptMapper.deptGetAll(redisUser.getTenantId());
        if (CollectionUtils.isEmpty(deptGetAll)) {
            return Lists.newArrayList();
        }
        List<DepTreeRes> depTreeResList = Lists.newArrayList();
        deptGetAll.forEach(deptDO -> {
            DepTreeRes depTreeRes = DepTreeRes.depTreeRes(deptDO);
            depTreeResList.add(depTreeRes);

        });
        return deptListToTree(depTreeResList);
    }

    private List<DepTreeRes> deptListToTree(List<DepTreeRes> deptLevelList) {
        if (CollectionUtils.isEmpty(deptLevelList)) {
            return Lists.newArrayList();
        }
        Multimap<String, DepTreeRes> levelDeptMap = ArrayListMultimap.create();
        List<DepTreeRes> rootList = Lists.newArrayList();

        for (DepTreeRes dto : deptLevelList) {
            levelDeptMap.put(dto.getSysDeptLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getSysDeptLevel())) {
                rootList.add(dto);
            }
        }
        // 按照seq从大到小排序
        rootList.sort((o1, o2) -> o2.getSysDeptSeq() - o1.getSysDeptSeq());
        // 递归生成树
        transformDeptTree(rootList, LevelUtil.ROOT, levelDeptMap);
        return rootList;
    }


    private void transformDeptTree(List<DepTreeRes> deptLevelList, String level, Multimap<String, DepTreeRes> levelDeptMap) {
        for (DepTreeRes deptLevelDto : deptLevelList) {
            // 遍历该层的每个元素
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDto.getId());
            // 处理下一层
            List<DepTreeRes> tempDeptList = (List<DepTreeRes>) levelDeptMap.get(nextLevel);
            if (!CollectionUtils.isEmpty(tempDeptList)) {
                // 排序
                tempDeptList.sort(deptSeqComparator);
                // 设置下一层部门
                deptLevelDto.setDepTreeResList(tempDeptList);
                // 进入到下一层处理
                transformDeptTree(tempDeptList, nextLevel, levelDeptMap);
            }
        }
    }

    private Comparator<DepTreeRes> deptSeqComparator = (o1, o2) -> o2.getSysDeptSeq() - o1.getSysDeptSeq();
}
