package com.momo.mapper.dataobject;

import java.util.Date;

/**
 * The table 角色和用户中间表
 */
public class RoleUserDO{

    /**
     * id ID.
     */
    private Long id;
    /**
     * roleId 角色id.
     */
    private Long roleId;
    /**
     * userId 用户id.
     */
    private Long userId;
    /**
     * tenantId 租户id.
     */
    private Long tenantId;
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
     * delFlag 删除状态(0-正常，1-删除).
     */
    private Integer delFlag;
    /**
     * disabledFlag 状态 0启用  1禁用.
     */
    private Integer disabledFlag;
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
     * Set roleId 角色id.
     */
    public void setRoleId(Long roleId){
        this.roleId = roleId;
    }

    /**
     * Get roleId 角色id.
     *
     * @return the string
     */
    public Long getRoleId(){
        return roleId;
    }

    /**
     * Set userId 用户id.
     */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
     * Get userId 用户id.
     *
     * @return the string
     */
    public Long getUserId(){
        return userId;
    }

    /**
     * Set tenantId 租户id.
     */
    public void setTenantId(Long tenantId){
        this.tenantId = tenantId;
    }

    /**
     * Get tenantId 租户id.
     *
     * @return the string
     */
    public Long getTenantId(){
        return tenantId;
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
     * Set disabledFlag 状态 0启用  1禁用.
     */
    public void setDisabledFlag(Integer disabledFlag){
        this.disabledFlag = disabledFlag;
    }

    /**
     * Get disabledFlag 状态 0启用  1禁用.
     *
     * @return the string
     */
    public Integer getDisabledFlag(){
        return disabledFlag;
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
