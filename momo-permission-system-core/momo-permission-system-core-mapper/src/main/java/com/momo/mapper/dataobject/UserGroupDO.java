package com.momo.mapper.dataobject;

import java.util.Date;

/**
 * The table 第三方权限组
 */
public class UserGroupDO{

    /**
     * id ID.
     */
    private Long id;
    /**
     * uuid uuid.
     */
    private String uuid;
    /**
     * nameTop 顶部名称.
     */
    private String nameTop;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * nameBottom 版权.
     */
    private String nameBottom;
    /**
     * userGroupName 用户组名称/第三方公司名称.
     */
    private String userGroupName;
    /**
     * flag 状态 0启用  1禁用.
     */
    private Integer flag;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private Integer delFlag;
    /**
     * sysOpenDay 开通的天数 -1 不限次数 自己公司公司所有.
     */
    private Integer sysOpenDay;
    /**
     * sysOpenAccountNum 已开通账号个数.
     */
    private Integer sysOpenAccountNum;
    /**
     * sysCreateAccountNum 可以开通子账户的个数 -1不限制次数  id为1 为自己公司，不限次数.
     */
    private Integer sysCreateAccountNum;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;
    /**
     * sysAccountEndTime 账号结束时间.
     */
    private Date sysAccountEndTime;
    /**
     * sysAccountStartTime 账号开通时间 (不传值，默认系统时间).
     */
    private Date sysAccountStartTime;

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
     * Set nameTop 顶部名称.
     */
    public void setNameTop(String nameTop){
        this.nameTop = nameTop;
    }

    /**
     * Get nameTop 顶部名称.
     *
     * @return the string
     */
    public String getNameTop(){
        return nameTop;
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
     * Set nameBottom 版权.
     */
    public void setNameBottom(String nameBottom){
        this.nameBottom = nameBottom;
    }

    /**
     * Get nameBottom 版权.
     *
     * @return the string
     */
    public String getNameBottom(){
        return nameBottom;
    }

    /**
     * Set userGroupName 用户组名称/第三方公司名称.
     */
    public void setUserGroupName(String userGroupName){
        this.userGroupName = userGroupName;
    }

    /**
     * Get userGroupName 用户组名称/第三方公司名称.
     *
     * @return the string
     */
    public String getUserGroupName(){
        return userGroupName;
    }

    /**
     * Set flag 状态 0启用  1禁用.
     */
    public void setFlag(Integer flag){
        this.flag = flag;
    }

    /**
     * Get flag 状态 0启用  1禁用.
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
     * Set sysOpenDay 开通的天数 -1 不限次数 自己公司公司所有.
     */
    public void setSysOpenDay(Integer sysOpenDay){
        this.sysOpenDay = sysOpenDay;
    }

    /**
     * Get sysOpenDay 开通的天数 -1 不限次数 自己公司公司所有.
     *
     * @return the string
     */
    public Integer getSysOpenDay(){
        return sysOpenDay;
    }

    /**
     * Set sysOpenAccountNum 已开通账号个数.
     */
    public void setSysOpenAccountNum(Integer sysOpenAccountNum){
        this.sysOpenAccountNum = sysOpenAccountNum;
    }

    /**
     * Get sysOpenAccountNum 已开通账号个数.
     *
     * @return the string
     */
    public Integer getSysOpenAccountNum(){
        return sysOpenAccountNum;
    }

    /**
     * Set sysCreateAccountNum 可以开通子账户的个数 -1不限制次数  id为1 为自己公司，不限次数.
     */
    public void setSysCreateAccountNum(Integer sysCreateAccountNum){
        this.sysCreateAccountNum = sysCreateAccountNum;
    }

    /**
     * Get sysCreateAccountNum 可以开通子账户的个数 -1不限制次数  id为1 为自己公司，不限次数.
     *
     * @return the string
     */
    public Integer getSysCreateAccountNum(){
        return sysCreateAccountNum;
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

    /**
     * Set sysAccountEndTime 账号结束时间.
     */
    public void setSysAccountEndTime(Date sysAccountEndTime){
        this.sysAccountEndTime = sysAccountEndTime;
    }

    /**
     * Get sysAccountEndTime 账号结束时间.
     *
     * @return the string
     */
    public Date getSysAccountEndTime(){
        return sysAccountEndTime;
    }

    /**
     * Set sysAccountStartTime 账号开通时间 (不传值，默认系统时间).
     */
    public void setSysAccountStartTime(Date sysAccountStartTime){
        this.sysAccountStartTime = sysAccountStartTime;
    }

    /**
     * Get sysAccountStartTime 账号开通时间 (不传值，默认系统时间).
     *
     * @return the string
     */
    public Date getSysAccountStartTime(){
        return sysAccountStartTime;
    }
}
