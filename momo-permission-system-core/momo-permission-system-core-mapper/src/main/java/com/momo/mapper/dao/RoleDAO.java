package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.RoleDO;
import java.util.List;
import com.momo.mapper.mapper.RoleDOMapper;

/**
* The Table sys_role.
* 角色
*/
@Repository
public class RoleDAO{

    @Autowired
    private RoleDOMapper roleDOMapper;

    /**
     * desc:插入表:sys_role.<br/>
     * @param entity entity
     * @return int
     */
    public int insert(RoleDO entity){
        return roleDOMapper.insert(entity);
    }
    /**
     * desc:批量插入表:sys_role.<br/>
     * @param list list
     * @return int
     */
    public int insertBatch(List<RoleDO> list){
        return roleDOMapper.insertBatch(list);
    }
    /**
     * desc:根据主键删除数据:sys_role.<br/>
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return roleDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:sys_role.<br/>
     * @param id id
     * @return RoleDO
     */
    public RoleDO getById(Long id){
        return roleDOMapper.getById(id);
    }
    /**
     * desc:根据普通索引SysRoleUuid获取数据:sys_role.<br/>
     * @param uuid uuid
     * @return List<RoleDO>
     */
    public List<RoleDO> queryBySysRoleUuid(String uuid){
        return roleDOMapper.queryBySysRoleUuid(uuid);
    }
}
