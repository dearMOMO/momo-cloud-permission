package com.momo.service.service.authority;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.momo.common.util.LevelUtil;
import com.momo.mapper.dataobject.AclDO;
import com.momo.mapper.mapper.manual.AuthorityMapper;
import com.momo.mapper.req.sysmain.RedisUser;
import com.momo.mapper.res.authority.AclDto;
import com.momo.mapper.res.authority.LoginAuthReq;
import com.momo.service.service.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: momo-cloud-permission
 * @description: 本部 权限
 * @author: Jie Li
 * @create: 2019-07-30 22:44
 **/
@Service
public class AdminAuthorityService extends BaseService {

    @Autowired
    private AdminSysCoreService sysCoreService;
    @Autowired
    private AuthorityMapper authorityMapper;

    //动态权限菜单(总部和第三方通用)
    public List<AclDto> dynamicMenuTree(LoginAuthReq loginAuthReq) {
        RedisUser redisUser = this.redisUser();
        List<AclDO> userAclList = sysCoreService.getUserAclList(loginAuthReq, redisUser);
        List<AclDto> aclDtoList = Lists.newArrayList();
        for (AclDO acl : userAclList) {
            if (0 == acl.getStatus()) {
                AclDto dto = AclDto.adapt(acl);
                dto.setHasAcl(true);
                dto.setChecked(true);
                aclDtoList.add(dto);
            }
        }
        return deptListToTree(aclDtoList);
    }

    //为角色授权 权限 之前， 需要查看该角色拥有哪些权限点，以及当前登录用户可以操作哪些权限
    public List<AclDto> roleTree(LoginAuthReq loginAuthReq, RedisUser redisUser) {
        // 1、当前用户已分配的权限点
        List<AclDO> userAclList = sysCoreService.getUserHavingAclList(loginAuthReq, redisUser);
        // 2、当前角色分配的权限点
        List<AclDO> roleAclList = sysCoreService.getRoleAclList(loginAuthReq.getRoleId(), loginAuthReq.getAclPermissionType());
        // 3、当前系统所有权限点
        List<AclDto> aclDtoList = Lists.newArrayList();

        Set<Long> userAclIdSet = userAclList.stream().map(sysAcl -> sysAcl.getId()).collect(Collectors.toSet());
        Set<Long> roleAclIdSet = roleAclList.stream().map(sysAcl -> sysAcl.getId()).collect(Collectors.toSet());
        List<AclDO> allAclList = authorityMapper.getAllAcl(null, null);
        for (AclDO acl : allAclList) {
            //状态 0启用  1禁用
//            if ("0".equals(acl.getSysAclState())){
            AclDto dto = AclDto.adapt(acl);
            if (userAclIdSet.contains(acl.getId())) {
                dto.setHasAcl(true);
            }
            if (roleAclIdSet.contains(acl.getId())) {
                dto.setChecked(true);
            }
            aclDtoList.add(dto);
//            }
        }
        return deptListToTree(aclDtoList);
    }

    private List<AclDto> deptListToTree(List<AclDto> aclDtoList) {
        if (CollectionUtils.isEmpty(aclDtoList)) {
            return Lists.newArrayList();
        }
        // level -> [dept1, dept2, ...] Map<String, List<Object>>
        Multimap<String, AclDto> levelDeptMap = ArrayListMultimap.create();
        List<AclDto> rootList = Lists.newArrayList();

        for (AclDto dto : aclDtoList) {
            levelDeptMap.put(dto.getSysAclLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getSysAclLevel())) {
                rootList.add(dto);
            }
        }
        // 按照seq从小到大排序
        Collections.sort(rootList, new Comparator<AclDto>() {
            @Override
            public int compare(AclDto o1, AclDto o2) {
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
    private void transformDeptTree(List<AclDto> deptLevelList, String level, Multimap<String, AclDto> levelDeptMap) {
        for (int i = 0; i < deptLevelList.size(); i++) {
            // 遍历该层的每个元素
            AclDto deptLevelDto = deptLevelList.get(i);
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDto.getId());
            // 处理下一层
            List<AclDto> tempDeptList = (List<AclDto>) levelDeptMap.get(nextLevel);
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

    private Comparator<AclDto> deptSeqComparator = new Comparator<AclDto>() {
        @Override
        public int compare(AclDto o1, AclDto o2) {
            return o1.getSysAclSeq() - o2.getSysAclSeq();
        }
    };
}
