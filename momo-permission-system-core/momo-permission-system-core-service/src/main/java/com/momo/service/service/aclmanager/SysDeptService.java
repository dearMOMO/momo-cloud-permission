package com.momo.service.service.aclmanager;

import com.momo.mapper.dataobject.DeptDO;
import com.momo.mapper.res.aclmanager.DepTreeRes;

import java.util.List;

/**
 * @ClassName: SysDeptService
 * @Author: Jie Li
 * @Date 2019-11-15 14:46
 * @Description: 部门
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
public interface SysDeptService {
    /**
     * 部门树
     *
     * @return
     */
    List<DepTreeRes> deptTree();
}
