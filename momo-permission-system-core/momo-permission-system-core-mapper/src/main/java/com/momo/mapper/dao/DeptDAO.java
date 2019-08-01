package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.DeptDO;
import java.util.List;
import com.momo.mapper.mapper.DeptDOMapper;

/**
* The Table sys_dept.
* 部门表
*/
@Repository
public class DeptDAO{

    @Autowired
    private DeptDOMapper deptDOMapper;

    /**
     * desc:插入表:sys_dept.<br/>
     * @param entity entity
     * @return int
     */
    public int insert(DeptDO entity){
        return deptDOMapper.insert(entity);
    }
    /**
     * desc:批量插入表:sys_dept.<br/>
     * @param list list
     * @return int
     */
    public int insertBatch(List<DeptDO> list){
        return deptDOMapper.insertBatch(list);
    }
    /**
     * desc:根据主键删除数据:sys_dept.<br/>
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return deptDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:sys_dept.<br/>
     * @param id id
     * @return DeptDO
     */
    public DeptDO getById(Long id){
        return deptDOMapper.getById(id);
    }
    /**
     * desc:根据普通索引SysDeptUuid获取数据:sys_dept.<br/>
     * @param uuid uuid
     * @return List<DeptDO>
     */
    public List<DeptDO> queryBySysDeptUuid(String uuid){
        return deptDOMapper.queryBySysDeptUuid(uuid);
    }
}
