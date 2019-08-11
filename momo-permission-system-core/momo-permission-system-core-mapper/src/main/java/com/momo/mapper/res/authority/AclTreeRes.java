package com.momo.mapper.res.authority;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.momo.mapper.dataobject.RoleAclDO;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * @program: momo-cloud-permission
 * @description: TODO
 * @author: Jie Li
 * @create: 2019-08-05 14:48
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AclTreeRes {
    //权限树
    private List<AclLevelRes> aclLevelRes;
    //默认选中/展开
    private List<String> defaultexpandedKeys;

}
