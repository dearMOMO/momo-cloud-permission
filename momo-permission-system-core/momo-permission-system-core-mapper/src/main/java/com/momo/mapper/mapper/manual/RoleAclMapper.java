package com.momo.mapper.mapper.manual;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.mapper.mapper.manual
 * @Description: TODO
 * @Author: Jie Li
 * @CreateDate: 2019/9/3 0003 16:16
 * @UpdateDate: 2019/9/3 0003 16:16
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public interface RoleAclMapper {
    List<Long> aclIdsByTeantId(@Param("teantId")Long teantId,@Param("aclPermissionCode") String aclPermissionCode);
}
