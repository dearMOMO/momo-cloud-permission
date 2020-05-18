package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.AclDO;
import java.util.List;
import com.momo.mapper.mapper.AclDOMapper;

/**
* The Table sys_acl.
* 操作权限-权限点表
*/
@Repository
public class AclDAO{

    @Autowired
    private AclDOMapper aclDOMapper;

    /**
     * desc:插入表:sys_acl.<br/>
     * @param entity entity
     * @return int
     */
    public int insertSelect(AclDO entity){
        return aclDOMapper.insertSelect(entity);
    }
    /**
     * desc:插入表:sys_acl.<br/>
     * @param entity entity
     * @return int
     */
    public int insertSelectReturnId(AclDO entity){
        return aclDOMapper.insertSelectReturnId(entity);
    }
    /**
     * desc:批量插入表:sys_acl.<br/>
     * @param list list
     * @return int
     */
    public int insertBatchSelect(List<AclDO> list){
        return aclDOMapper.insertBatchSelect(list);
    }
    /**
     * desc:插入表:sys_acl.<br/>
     * @param list list
     * @return int
     */
    public int updateBatchByPrimaryKeySelective(List<AclDO> list){
        return aclDOMapper.updateBatchByPrimaryKeySelective(list);
    }
    /**
     * desc:插入表:sys_acl.<br/>
     * @param entity entity
     * @return int
     */
    public int updateByPrimaryKeySelective(AclDO entity){
        return aclDOMapper.updateByPrimaryKeySelective(entity);
    }
    /**
     * desc:根据主键删除数据:sys_acl.<br/>
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return aclDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:sys_acl.<br/>
     * @param id id
     * @return AclDO
     */
    public AclDO getById(Long id){
        return aclDOMapper.getById(id);
    }
    /**
     * desc:根据普通索引SysAclLevel获取数据:sys_acl.<br/>
     * @param sysAclLevel sysAclLevel
     * @return List<AclDO>
     */
    public List<AclDO> queryBySysAclLevel(String sysAclLevel){
        return aclDOMapper.queryBySysAclLevel(sysAclLevel);
    }
    /**
     * desc:根据普通索引SysAclModuleId获取数据:sys_acl.<br/>
     * @param sysAclParentId sysAclParentId
     * @return List<AclDO>
     */
    public List<AclDO> queryBySysAclModuleId(Long sysAclParentId){
        return aclDOMapper.queryBySysAclModuleId(sysAclParentId);
    }
    /**
     * desc:根据普通索引SysAclUuid获取数据:sys_acl.<br/>
     * @param uuid uuid
     * @return List<AclDO>
     */
    public List<AclDO> queryBySysAclUuid(String uuid){
        return aclDOMapper.queryBySysAclUuid(uuid);
    }
}
