package com.momo.mapper.mapper.manual;

import com.momo.mapper.dataobject.DeptDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: DeptMapper
 * @Author: Jie Li
 * @Date 2019-11-15 14:37
 * @Description: 部门
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
public interface DeptMapper {

    List<DeptDO> deptGetAll(@Param("tenantId") Long tenantId);

    int deleteByPrimaryKey(Long id);

    int insertSelective(DeptDO deptDO);

    DeptDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeptDO deptDO);

}
