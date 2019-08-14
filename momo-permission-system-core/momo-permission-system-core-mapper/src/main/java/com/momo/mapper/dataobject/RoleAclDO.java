package com.momo.mapper.dataobject;

import java.util.Date;

/**
 * The table 角色和权限中间表
 */
public class RoleAclDO{

    /**
     * id ID.
     */
    private Long id;
    /**
     * groupId 第三方组 kagome-momo-open-source默认为1.
     */
    private Long groupId;
    /**
     * sysAclId 权限id.
     */
    private Long sysAclId;
    /**
     * sysRoleId 角色id.
     */
    private Long sysRoleId;
    /**
     * uuid 唯一，32位字符串，查询用.
     */
    private String uuid;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * sysAclPermissionCode 权限系统类型.
     */
    private String sysAclPermissionCode;
    /**
     * flag 是否被禁用  0否 1禁用.
     */
    private Integer flag;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private Integer delFlag;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;

    /**
     * Set id ID.
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Get id ID.
     *
     * @return the string
     */
    public Long getId(){
        return id;
    }

    /**
     * Set groupId 第三方组 kagome-momo-open-source默认为1.
     */
    public void setGroupId(Long groupId){
        this.groupId = groupId;
    }

    /**
     * Get groupId 第三方组 kagome-momo-open-source默认为1.
     *
     * @return the string
     */
    public Long getGroupId(){
        return groupId;
    }

    /**
     * Set sysAclId 权限id.
     */
    public void setSysAclId(Long sysAclId){
        this.sysAclId = sysAclId;
    }

    /**
     * Get sysAclId 权限id.
     *
     * @return the string
     */
    public Long getSysAclId(){
        return sysAclId;
    }

    /**
     * Set sysRoleId 角色id.
     */
    public void setSysRoleId(Long sysRoleId){
        this.sysRoleId = sysRoleId;
    }

    /**
     * Get sysRoleId 角色id.
     *
     * @return the string
     */
    public Long getSysRoleId(){
        return sysRoleId;
    }

    /**
     * Set uuid 唯一，32位字符串，查询用.
     */
    public void setUuid(String uuid){
        this.uuid = uuid;
    }

    /**
     * Get uuid 唯一，32位字符串，查询用.
     *
     * @return the string
     */
    public String getUuid(){
        return uuid;
    }

    /**
     * Set createBy 创建人.
     */
    public void setCreateBy(String createBy){
        this.createBy = createBy;
    }

    /**
     * Get createBy 创建人.
     *
     * @return the string
     */
    public String getCreateBy(){
        return createBy;
    }

    /**
     * Set updateBy 修改人.
     */
    public void setUpdateBy(String updateBy){
        this.updateBy = updateBy;
    }

    /**
     * Get updateBy 修改人.
     *
     * @return the string
     */
    public String getUpdateBy(){
        return updateBy;
    }

    /**
     * Set sysAclPermissionCode 权限系统类型.
     */
    public void setSysAclPermissionCode(String sysAclPermissionCode){
        this.sysAclPermissionCode = sysAclPermissionCode;
    }

    /**
     * Get sysAclPermissionCode 权限系统类型.
     *
     * @return the string
     */
    public String getSysAclPermissionCode(){
        return sysAclPermissionCode;
    }

    /**
     * Set flag 是否被禁用  0否 1禁用.
     */
    public void setFlag(Integer flag){
        this.flag = flag;
    }

    /**
     * Get flag 是否被禁用  0否 1禁用.
     *
     * @return the string
     */
    public Integer getFlag(){
        return flag;
    }

    /**
     * Set delFlag 删除状态(0-正常，1-删除).
     */
    public void setDelFlag(Integer delFlag){
        this.delFlag = delFlag;
    }

    /**
     * Get delFlag 删除状态(0-正常，1-删除).
     *
     * @return the string
     */
    public Integer getDelFlag(){
        return delFlag;
    }

    /**
     * Set createTime 创建时间.
     */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
     * Get createTime 创建时间.
     *
     * @return the string
     */
    public Date getCreateTime(){
        return createTime;
    }

    /**
     * Set updateTime 修改时间.
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    /**
     * Get updateTime 修改时间.
     *
     * @return the string
     */
    public Date getUpdateTime(){
        return updateTime;
    }
}
