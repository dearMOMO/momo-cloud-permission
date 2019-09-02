package com.momo.mapper.dataobject;

import java.util.Date;

/**
 * The table 用户账号密码
 */
public class UserAccountPwdDO{

    /**
     * id ID.
     */
    private Long id;
    /**
     * tenantId 租户id.
     */
    private Long tenantId;
    /**
     * sysUserId 关联用户表id.
     */
    private Long sysUserId;
    /**
     * uuid uuid.
     */
    private String uuid;
    /**
     * remark 备注.
     */
    private String remark;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * sysUserPwd 密码.
     */
    private String sysUserPwd;
    /**
     * sysUserAuthSalt 撒盐.
     */
    private String sysUserAuthSalt;
    /**
     * sysUserLoginName 登录名.
     */
    private String sysUserLoginName;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private Integer delFlag;
    /**
     * disabledFlag 状态 0启用  1禁用.
     */
    private Integer disabledFlag;
    /**
     * sysLoginNumber 账号允许登录的次数 -1 不限次数 ，0禁止登陆.
     */
    private Integer sysLoginNumber;
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
     * Set sysUserId 关联用户表id.
     */
    public void setSysUserId(Long sysUserId){
        this.sysUserId = sysUserId;
    }

    /**
     * Get sysUserId 关联用户表id.
     *
     * @return the string
     */
    public Long getSysUserId(){
        return sysUserId;
    }

    /**
     * Set uuid uuid.
     */
    public void setUuid(String uuid){
        this.uuid = uuid;
    }

    /**
     * Get uuid uuid.
     *
     * @return the string
     */
    public String getUuid(){
        return uuid;
    }

    /**
     * Set remark 备注.
     */
    public void setRemark(String remark){
        this.remark = remark;
    }

    /**
     * Get remark 备注.
     *
     * @return the string
     */
    public String getRemark(){
        return remark;
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
     * Set sysUserPwd 密码.
     */
    public void setSysUserPwd(String sysUserPwd){
        this.sysUserPwd = sysUserPwd;
    }

    /**
     * Get sysUserPwd 密码.
     *
     * @return the string
     */
    public String getSysUserPwd(){
        return sysUserPwd;
    }

    /**
     * Set sysUserAuthSalt 撒盐.
     */
    public void setSysUserAuthSalt(String sysUserAuthSalt){
        this.sysUserAuthSalt = sysUserAuthSalt;
    }

    /**
     * Get sysUserAuthSalt 撒盐.
     *
     * @return the string
     */
    public String getSysUserAuthSalt(){
        return sysUserAuthSalt;
    }

    /**
     * Set sysUserLoginName 登录名.
     */
    public void setSysUserLoginName(String sysUserLoginName){
        this.sysUserLoginName = sysUserLoginName;
    }

    /**
     * Get sysUserLoginName 登录名.
     *
     * @return the string
     */
    public String getSysUserLoginName(){
        return sysUserLoginName;
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
     * Set sysLoginNumber 账号允许登录的次数 -1 不限次数 ，0禁止登陆.
     */
    public void setSysLoginNumber(Integer sysLoginNumber){
        this.sysLoginNumber = sysLoginNumber;
    }

    /**
     * Get sysLoginNumber 账号允许登录的次数 -1 不限次数 ，0禁止登陆.
     *
     * @return the string
     */
    public Integer getSysLoginNumber(){
        return sysLoginNumber;
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
