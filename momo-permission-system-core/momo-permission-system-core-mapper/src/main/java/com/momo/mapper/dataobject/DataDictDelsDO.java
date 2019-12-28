package com.momo.mapper.dataobject;

import java.util.Date;

/**
 * The table 数据字典详情表
 */
public class DataDictDelsDO{

    /**
     * id ID.
     */
    private Long id;
    /**
     * sysDictCodeId 字段值(用于关联字典表里的 id ).
     */
    private Long sysDictCodeId;
    /**
     * remark REMARK.
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
     * sysDictDelsName 参数名称.
     */
    private String sysDictDelsName;
    /**
     * sysDictCodeValue 字段值(用于关联字典表里的 参数值 ).
     */
    private String sysDictCodeValue;
    /**
     * sysDictDelsValue 参数值.
     */
    private String sysDictDelsValue;
    /**
     * sysDictCodeHtmlLeft html左边值.
     */
    private String sysDictCodeHtmlLeft;
    /**
     * sysDictCodeHtmlRight html右边值.
     */
    private String sysDictCodeHtmlRight;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private Integer delFlag;
    /**
     * disabledFlag 状态 0启用  1禁用.
     */
    private Integer disabledFlag;
    /**
     * sysDictDelsSeq 排序.
     */
    private Integer sysDictDelsSeq;
    /**
     * sysDictCodeHtmlApply 是否应用html值 0是 1否.
     */
    private Integer sysDictCodeHtmlApply;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime datetime.
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
     * Set sysDictCodeId 字段值(用于关联字典表里的 id ).
     */
    public void setSysDictCodeId(Long sysDictCodeId){
        this.sysDictCodeId = sysDictCodeId;
    }

    /**
     * Get sysDictCodeId 字段值(用于关联字典表里的 id ).
     *
     * @return the string
     */
    public Long getSysDictCodeId(){
        return sysDictCodeId;
    }

    /**
     * Set remark REMARK.
     */
    public void setRemark(String remark){
        this.remark = remark;
    }

    /**
     * Get remark REMARK.
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
     * Set sysDictDelsName 参数名称.
     */
    public void setSysDictDelsName(String sysDictDelsName){
        this.sysDictDelsName = sysDictDelsName;
    }

    /**
     * Get sysDictDelsName 参数名称.
     *
     * @return the string
     */
    public String getSysDictDelsName(){
        return sysDictDelsName;
    }

    /**
     * Set sysDictCodeValue 字段值(用于关联字典表里的 参数值 ).
     */
    public void setSysDictCodeValue(String sysDictCodeValue){
        this.sysDictCodeValue = sysDictCodeValue;
    }

    /**
     * Get sysDictCodeValue 字段值(用于关联字典表里的 参数值 ).
     *
     * @return the string
     */
    public String getSysDictCodeValue(){
        return sysDictCodeValue;
    }

    /**
     * Set sysDictDelsValue 参数值.
     */
    public void setSysDictDelsValue(String sysDictDelsValue){
        this.sysDictDelsValue = sysDictDelsValue;
    }

    /**
     * Get sysDictDelsValue 参数值.
     *
     * @return the string
     */
    public String getSysDictDelsValue(){
        return sysDictDelsValue;
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
     * Set sysDictDelsSeq 排序.
     */
    public void setSysDictDelsSeq(Integer sysDictDelsSeq){
        this.sysDictDelsSeq = sysDictDelsSeq;
    }

    /**
     * Get sysDictDelsSeq 排序.
     *
     * @return the string
     */
    public Integer getSysDictDelsSeq(){
        return sysDictDelsSeq;
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
     * Set updateTime datetime.
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    /**
     * Get updateTime datetime.
     *
     * @return the string
     */
    public Date getUpdateTime(){
        return updateTime;
    }
}
