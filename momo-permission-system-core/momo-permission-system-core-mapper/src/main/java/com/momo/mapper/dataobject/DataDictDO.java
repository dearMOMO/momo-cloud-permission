package com.momo.mapper.dataobject;

import java.util.Date;

/**
 * The table 数据字典表
 */
public class DataDictDO{

    /**
     * id ID.
     */
    private Long id;
    /**
     * sysDictCodeParentId SYS_DICT_CODE_PARENT_ID.
     */
    private Long sysDictCodeParentId;
    /**
     * remark 注释.
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
     * sysDictCodeName 参数名称.
     */
    private String sysDictCodeName;
    /**
     * sysDictCodeLevel 字典层级.
     */
    private String sysDictCodeLevel;
    /**
     * sysDictCodeValue 参数值.
     */
    private String sysDictCodeValue;
    /**
     * sysDictCodeHtmlLeft html左边值.
     */
    private String sysDictCodeHtmlLeft;
    /**
     * sysDictCodeHtmlRight html右边值.
     */
    private String sysDictCodeHtmlRight;
    /**
     * sysDictCodeParentValue 上级参数值.
     */
    private String sysDictCodeParentValue;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private Integer delFlag;
    /**
     * disabledFlag 状态 0启用  1禁用.
     */
    private Integer disabledFlag;
    /**
     * sysDictCodeSeq 排序.
     */
    private Integer sysDictCodeSeq;
    /**
     * sysDictCodeHtmlApply 是否应用html值 0是 1否.
     */
    private Integer sysDictCodeHtmlApply;
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
     * Set sysDictCodeParentId SYS_DICT_CODE_PARENT_ID.
     */
    public void setSysDictCodeParentId(Long sysDictCodeParentId){
        this.sysDictCodeParentId = sysDictCodeParentId;
    }

    /**
     * Get sysDictCodeParentId SYS_DICT_CODE_PARENT_ID.
     *
     * @return the string
     */
    public Long getSysDictCodeParentId(){
        return sysDictCodeParentId;
    }

    /**
     * Set remark 注释.
     */
    public void setRemark(String remark){
        this.remark = remark;
    }

    /**
     * Get remark 注释.
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
     * Set sysDictCodeName 参数名称.
     */
    public void setSysDictCodeName(String sysDictCodeName){
        this.sysDictCodeName = sysDictCodeName;
    }

    /**
     * Get sysDictCodeName 参数名称.
     *
     * @return the string
     */
    public String getSysDictCodeName(){
        return sysDictCodeName;
    }

    /**
     * Set sysDictCodeLevel 字典层级.
     */
    public void setSysDictCodeLevel(String sysDictCodeLevel){
        this.sysDictCodeLevel = sysDictCodeLevel;
    }

    /**
     * Get sysDictCodeLevel 字典层级.
     *
     * @return the string
     */
    public String getSysDictCodeLevel(){
        return sysDictCodeLevel;
    }

    /**
     * Set sysDictCodeValue 参数值.
     */
    public void setSysDictCodeValue(String sysDictCodeValue){
        this.sysDictCodeValue = sysDictCodeValue;
    }

    /**
     * Get sysDictCodeValue 参数值.
     *
     * @return the string
     */
    public String getSysDictCodeValue(){
        return sysDictCodeValue;
    }

    /**
     * Set sysDictCodeHtmlLeft html左边值.
     */
    public void setSysDictCodeHtmlLeft(String sysDictCodeHtmlLeft){
        this.sysDictCodeHtmlLeft = sysDictCodeHtmlLeft;
    }

    /**
     * Get sysDictCodeHtmlLeft html左边值.
     *
     * @return the string
     */
    public String getSysDictCodeHtmlLeft(){
        return sysDictCodeHtmlLeft;
    }

    /**
     * Set sysDictCodeHtmlRight html右边值.
     */
    public void setSysDictCodeHtmlRight(String sysDictCodeHtmlRight){
        this.sysDictCodeHtmlRight = sysDictCodeHtmlRight;
    }

    /**
     * Get sysDictCodeHtmlRight html右边值.
     *
     * @return the string
     */
    public String getSysDictCodeHtmlRight(){
        return sysDictCodeHtmlRight;
    }

    /**
     * Set sysDictCodeParentValue 上级参数值.
     */
    public void setSysDictCodeParentValue(String sysDictCodeParentValue){
        this.sysDictCodeParentValue = sysDictCodeParentValue;
    }

    /**
     * Get sysDictCodeParentValue 上级参数值.
     *
     * @return the string
     */
    public String getSysDictCodeParentValue(){
        return sysDictCodeParentValue;
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
     * Set sysDictCodeSeq 排序.
     */
    public void setSysDictCodeSeq(Integer sysDictCodeSeq){
        this.sysDictCodeSeq = sysDictCodeSeq;
    }

    /**
     * Get sysDictCodeSeq 排序.
     *
     * @return the string
     */
    public Integer getSysDictCodeSeq(){
        return sysDictCodeSeq;
    }

    /**
     * Set sysDictCodeHtmlApply 是否应用html值 0是 1否.
     */
    public void setSysDictCodeHtmlApply(Integer sysDictCodeHtmlApply){
        this.sysDictCodeHtmlApply = sysDictCodeHtmlApply;
    }

    /**
     * Get sysDictCodeHtmlApply 是否应用html值 0是 1否.
     *
     * @return the string
     */
    public Integer getSysDictCodeHtmlApply(){
        return sysDictCodeHtmlApply;
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
