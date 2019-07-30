package com.momo.mapper.dataobject;

import java.util.Date;
import com.momo.mapper.dataobject.UserDeptDO;

/**
 * The table 用户和部门
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class UserDeptDO{

    /**
     * id ID.
     */
    private Long id;
    /**
     * sysDeptId 部门id.
     */
    private Long sysDeptId;
    /**
     * sysUserId 用户id.
     */
    private Long sysUserId;
    /**
     * uuid UUID.
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
     * state 是否被禁用  0否 1禁用.
     */
    private Integer state;
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
     * Set sysDeptId 部门id.
     */
    public void setSysDeptId(Long sysDeptId){
        this.sysDeptId = sysDeptId;
    }

    /**
     * Get sysDeptId 部门id.
     *
     * @return the string
     */
    public Long getSysDeptId(){
        return sysDeptId;
    }

    /**
     * Set sysUserId 用户id.
     */
    public void setSysUserId(Long sysUserId){
        this.sysUserId = sysUserId;
    }

    /**
     * Get sysUserId 用户id.
     *
     * @return the string
     */
    public Long getSysUserId(){
        return sysUserId;
    }

    /**
     * Set uuid UUID.
     */
    public void setUuid(String uuid){
        this.uuid = uuid;
    }

    /**
     * Get uuid UUID.
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
     * Set state 是否被禁用  0否 1禁用.
     */
    public void setState(Integer state){
        this.state = state;
    }

    /**
     * Get state 是否被禁用  0否 1禁用.
     *
     * @return the string
     */
    public Integer getState(){
        return state;
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
