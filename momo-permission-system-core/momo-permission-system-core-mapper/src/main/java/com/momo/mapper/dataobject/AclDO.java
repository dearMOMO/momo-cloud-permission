package com.momo.mapper.dataobject;

import java.util.Date;

/**
 * The table 操作权限-权限点表
 */
public class AclDO{

    /**
     * id 权限id.
     */
    private Long id;
    /**
     * sysAclParentId 父亲权限id.
     */
    private Long sysAclParentId;
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
     * sysAclUrl 请求的url, 可以填正则表达式.
     */
    private String sysAclUrl;
    /**
     * sysAclCode 权限码.
     */
    private String sysAclCode;
    /**
     * sysAclIcon 图标class.
     */
    private String sysAclIcon;
    /**
     * sysAclName 权限名称.
     */
    private String sysAclName;
    /**
     * sysAclLevel 权限层级.
     */
    private String sysAclLevel;
    /**
     * sysAclAction 按钮动作类型(交给前端处理）.
     */
    private String sysAclAction;
    /**
     * sysAclRouter 所属页面(交给前端处理).
     */
    private String sysAclRouter;
    /**
     * sysAclComponentName 前端组件名称.
     */
    private String sysAclComponentName;
    /**
     * sysAclPermissionCode 菜单系统类型 .
     */
    private String sysAclPermissionCode;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private Integer delFlag;
    /**
     * sysAclSeq 权限在当前模块下的顺序，由小到大.
     */
    private Integer sysAclSeq;
    /**
     * sysAclType 类型，-1系统 0:目录 1：菜单，2：按钮，3：其他.
     */
    private Integer sysAclType;
    /**
     * sysAclFrame 是否为外链 0否 1是.
     */
    private Integer sysAclFrame;
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
     * Set id 权限id.
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Get id 权限id.
     *
     * @return the string
     */
    public Long getId(){
        return id;
    }

    /**
     * Set sysAclParentId 父亲权限id.
     */
    public void setSysAclParentId(Long sysAclParentId){
        this.sysAclParentId = sysAclParentId;
    }

    /**
     * Get sysAclParentId 父亲权限id.
     *
     * @return the string
     */
    public Long getSysAclParentId(){
        return sysAclParentId;
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
     * Set sysAclUrl 请求的url, 可以填正则表达式.
     */
    public void setSysAclUrl(String sysAclUrl){
        this.sysAclUrl = sysAclUrl;
    }

    /**
     * Get sysAclUrl 请求的url, 可以填正则表达式.
     *
     * @return the string
     */
    public String getSysAclUrl(){
        return sysAclUrl;
    }

    /**
     * Set sysAclCode 权限码.
     */
    public void setSysAclCode(String sysAclCode){
        this.sysAclCode = sysAclCode;
    }

    /**
     * Get sysAclCode 权限码.
     *
     * @return the string
     */
    public String getSysAclCode(){
        return sysAclCode;
    }

    /**
     * Set sysAclIcon 图标class.
     */
    public void setSysAclIcon(String sysAclIcon){
        this.sysAclIcon = sysAclIcon;
    }

    /**
     * Get sysAclIcon 图标class.
     *
     * @return the string
     */
    public String getSysAclIcon(){
        return sysAclIcon;
    }

    /**
     * Set sysAclName 权限名称.
     */
    public void setSysAclName(String sysAclName){
        this.sysAclName = sysAclName;
    }

    /**
     * Get sysAclName 权限名称.
     *
     * @return the string
     */
    public String getSysAclName(){
        return sysAclName;
    }

    /**
     * Set sysAclLevel 权限层级.
     */
    public void setSysAclLevel(String sysAclLevel){
        this.sysAclLevel = sysAclLevel;
    }

    /**
     * Get sysAclLevel 权限层级.
     *
     * @return the string
     */
    public String getSysAclLevel(){
        return sysAclLevel;
    }

    /**
     * Set sysAclAction 按钮动作类型(交给前端处理）.
     */
    public void setSysAclAction(String sysAclAction){
        this.sysAclAction = sysAclAction;
    }

    /**
     * Get sysAclAction 按钮动作类型(交给前端处理）.
     *
     * @return the string
     */
    public String getSysAclAction(){
        return sysAclAction;
    }

    /**
     * Set sysAclRouter 所属页面(交给前端处理).
     */
    public void setSysAclRouter(String sysAclRouter){
        this.sysAclRouter = sysAclRouter;
    }

    /**
     * Get sysAclRouter 所属页面(交给前端处理).
     *
     * @return the string
     */
    public String getSysAclRouter(){
        return sysAclRouter;
    }

    /**
     * Set sysAclComponentName 前端组件名称.
     */
    public void setSysAclComponentName(String sysAclComponentName){
        this.sysAclComponentName = sysAclComponentName;
    }

    /**
     * Get sysAclComponentName 前端组件名称.
     *
     * @return the string
     */
    public String getSysAclComponentName(){
        return sysAclComponentName;
    }

    /**
     * Set sysAclPermissionCode 菜单系统类型 .
     */
    public void setSysAclPermissionCode(String sysAclPermissionCode){
        this.sysAclPermissionCode = sysAclPermissionCode;
    }

    /**
     * Get sysAclPermissionCode 菜单系统类型 .
     *
     * @return the string
     */
    public String getSysAclPermissionCode(){
        return sysAclPermissionCode;
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
     * Set sysAclSeq 权限在当前模块下的顺序，由小到大.
     */
    public void setSysAclSeq(Integer sysAclSeq){
        this.sysAclSeq = sysAclSeq;
    }

    /**
     * Get sysAclSeq 权限在当前模块下的顺序，由小到大.
     *
     * @return the string
     */
    public Integer getSysAclSeq(){
        return sysAclSeq;
    }

    /**
     * Set sysAclType 类型，-1系统 0:目录 1：菜单，2：按钮，3：其他.
     */
    public void setSysAclType(Integer sysAclType){
        this.sysAclType = sysAclType;
    }

    /**
     * Get sysAclType 类型，-1系统 0:目录 1：菜单，2：按钮，3：其他.
     *
     * @return the string
     */
    public Integer getSysAclType(){
        return sysAclType;
    }

    /**
     * Set sysAclFrame 是否为外链 0否 1是.
     */
    public void setSysAclFrame(Integer sysAclFrame){
        this.sysAclFrame = sysAclFrame;
    }

    /**
     * Get sysAclFrame 是否为外链 0否 1是.
     *
     * @return the string
     */
    public Integer getSysAclFrame(){
        return sysAclFrame;
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
