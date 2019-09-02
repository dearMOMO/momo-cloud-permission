package com.momo.mapper.dataobject;

import java.util.Date;

/**
 * The table SYS_ROUTES
 */
public class RoutesDO{

    /**
     * uuid UUID.
     */
    private String uuid;
    /**
     * sysId 这个路由的唯一id，不定义的话为一个uuid.
     */
    private String sysId;
    /**
     * remark 备注.
     */
    private String remark;
    /**
     * sysUri http请求为lb://前缀 + 服务id；ws请求为lb:ws://前缀 + 服务id；表示将请求负载到哪一个服务上.
     */
    private String sysUri;
    /**
     * sysName 路由名称.
     */
    private String sysName;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * sysFilters 请求转发前的filter，为一个数组.
     */
    private String sysFilters;
    /**
     * sysPredicates 表示这个路由的请求匹配规则，只有符合这个规则的请求才会走这个路由。为一个数组，每个规则为并且的关系.
     */
    private String sysPredicates;
    /**
     * pId P_ID.
     */
    private Integer pId;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private Integer delFlag;
    /**
     * sysOrder 这个路由的执行order.
     */
    private Integer sysOrder;
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
     * Set sysId 这个路由的唯一id，不定义的话为一个uuid.
     */
    public void setSysId(String sysId){
        this.sysId = sysId;
    }

    /**
     * Get sysId 这个路由的唯一id，不定义的话为一个uuid.
     *
     * @return the string
     */
    public String getSysId(){
        return sysId;
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
     * Set sysUri http请求为lb://前缀 + 服务id；ws请求为lb:ws://前缀 + 服务id；表示将请求负载到哪一个服务上.
     */
    public void setSysUri(String sysUri){
        this.sysUri = sysUri;
    }

    /**
     * Get sysUri http请求为lb://前缀 + 服务id；ws请求为lb:ws://前缀 + 服务id；表示将请求负载到哪一个服务上.
     *
     * @return the string
     */
    public String getSysUri(){
        return sysUri;
    }

    /**
     * Set sysName 路由名称.
     */
    public void setSysName(String sysName){
        this.sysName = sysName;
    }

    /**
     * Get sysName 路由名称.
     *
     * @return the string
     */
    public String getSysName(){
        return sysName;
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
     * Set sysFilters 请求转发前的filter，为一个数组.
     */
    public void setSysFilters(String sysFilters){
        this.sysFilters = sysFilters;
    }

    /**
     * Get sysFilters 请求转发前的filter，为一个数组.
     *
     * @return the string
     */
    public String getSysFilters(){
        return sysFilters;
    }

    /**
     * Set sysPredicates 表示这个路由的请求匹配规则，只有符合这个规则的请求才会走这个路由。为一个数组，每个规则为并且的关系.
     */
    public void setSysPredicates(String sysPredicates){
        this.sysPredicates = sysPredicates;
    }

    /**
     * Get sysPredicates 表示这个路由的请求匹配规则，只有符合这个规则的请求才会走这个路由。为一个数组，每个规则为并且的关系.
     *
     * @return the string
     */
    public String getSysPredicates(){
        return sysPredicates;
    }

    /**
     * Set pId P_ID.
     */
    public void setPId(Integer pId){
        this.pId = pId;
    }

    /**
     * Get pId P_ID.
     *
     * @return the string
     */
    public Integer getPId(){
        return pId;
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
     * Set sysOrder 这个路由的执行order.
     */
    public void setSysOrder(Integer sysOrder){
        this.sysOrder = sysOrder;
    }

    /**
     * Get sysOrder 这个路由的执行order.
     *
     * @return the string
     */
    public Integer getSysOrder(){
        return sysOrder;
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
