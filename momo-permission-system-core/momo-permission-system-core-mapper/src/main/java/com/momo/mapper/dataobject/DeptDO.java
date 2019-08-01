package com.momo.mapper.dataobject;

import java.util.Date;

/**
 * The table 部门表
 */
public class DeptDO{

    /**
     * id 部门id.
     */
    private Long id;
    /**
     * groupId 第三方组 kagome-momo-open-source默认为1.
     */
    private Long groupId;
    /**
     * sysDeptParentId 上级部门id.
     */
    private Long sysDeptParentId;
    /**
     * uuid 唯一，32位字符串，查询用.
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
     * sysDeptName 部门名称.
     */
    private String sysDeptName;
    /**
     * sysDeptLevel 部门层级.
     */
    private String sysDeptLevel;
    /**
     * flag 状态 0启用  1禁用.
     */
    private Integer flag;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private Integer delFlag;
    /**
     * sysIsLeaf 是否是叶子节点 0 是   1不是.
     */
    private Integer sysIsLeaf;
    /**
     * sysDeptSeq 部门在当前层级下的顺序，由小到大.
     */
    private Integer sysDeptSeq;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;

    /**
     * Set id 部门id.
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Get id 部门id.
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
     * Set sysDeptParentId 上级部门id.
     */
    public void setSysDeptParentId(Long sysDeptParentId){
        this.sysDeptParentId = sysDeptParentId;
    }

    /**
     * Get sysDeptParentId 上级部门id.
     *
     * @return the string
     */
    public Long getSysDeptParentId(){
        return sysDeptParentId;
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
     * Set sysDeptName 部门名称.
     */
    public void setSysDeptName(String sysDeptName){
        this.sysDeptName = sysDeptName;
    }

    /**
     * Get sysDeptName 部门名称.
     *
     * @return the string
     */
    public String getSysDeptName(){
        return sysDeptName;
    }

    /**
     * Set sysDeptLevel 部门层级.
     */
    public void setSysDeptLevel(String sysDeptLevel){
        this.sysDeptLevel = sysDeptLevel;
    }

    /**
     * Get sysDeptLevel 部门层级.
     *
     * @return the string
     */
    public String getSysDeptLevel(){
        return sysDeptLevel;
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
     * Set sysIsLeaf 是否是叶子节点 0 是   1不是.
     */
    public void setSysIsLeaf(Integer sysIsLeaf){
        this.sysIsLeaf = sysIsLeaf;
    }

    /**
     * Get sysIsLeaf 是否是叶子节点 0 是   1不是.
     *
     * @return the string
     */
    public Integer getSysIsLeaf(){
        return sysIsLeaf;
    }

    /**
     * Set sysDeptSeq 部门在当前层级下的顺序，由小到大.
     */
    public void setSysDeptSeq(Integer sysDeptSeq){
        this.sysDeptSeq = sysDeptSeq;
    }

    /**
     * Get sysDeptSeq 部门在当前层级下的顺序，由小到大.
     *
     * @return the string
     */
    public Integer getSysDeptSeq(){
        return sysDeptSeq;
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
