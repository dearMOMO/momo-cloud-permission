package com.momo.mapper.dataobject;

import java.util.Date;

/**
 * The table 用户
 */
public class UserDO{

    /**
     * id 主键.
     */
    private Long id;
    /**
     * groupId 第三方组 kagome-momo-open-source默认为1.
     */
    private Long groupId;
    /**
     * sysUserArea 区.
     */
    private Long sysUserArea;
    /**
     * sysUserCity 市.
     */
    private Long sysUserCity;
    /**
     * sysUserProvince 省.
     */
    private Long sysUserProvince;
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
     * sysUserName 姓名.
     */
    private String sysUserName;
    /**
     * sysUserEmail 邮箱.
     */
    private String sysUserEmail;
    /**
     * sysUserPhone 手机号.
     */
    private String sysUserPhone;
    /**
     * sysUserAreaName 区名字.
     */
    private String sysUserAreaName;
    /**
     * sysUserCityName 市名字.
     */
    private String sysUserCityName;
    /**
     * sysUserProvinceName 省 名字.
     */
    private String sysUserProvinceName;
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
     * Set id 主键.
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Get id 主键.
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
     * Set sysUserArea 区.
     */
    public void setSysUserArea(Long sysUserArea){
        this.sysUserArea = sysUserArea;
    }

    /**
     * Get sysUserArea 区.
     *
     * @return the string
     */
    public Long getSysUserArea(){
        return sysUserArea;
    }

    /**
     * Set sysUserCity 市.
     */
    public void setSysUserCity(Long sysUserCity){
        this.sysUserCity = sysUserCity;
    }

    /**
     * Get sysUserCity 市.
     *
     * @return the string
     */
    public Long getSysUserCity(){
        return sysUserCity;
    }

    /**
     * Set sysUserProvince 省.
     */
    public void setSysUserProvince(Long sysUserProvince){
        this.sysUserProvince = sysUserProvince;
    }

    /**
     * Get sysUserProvince 省.
     *
     * @return the string
     */
    public Long getSysUserProvince(){
        return sysUserProvince;
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
     * Set sysUserName 姓名.
     */
    public void setSysUserName(String sysUserName){
        this.sysUserName = sysUserName;
    }

    /**
     * Get sysUserName 姓名.
     *
     * @return the string
     */
    public String getSysUserName(){
        return sysUserName;
    }

    /**
     * Set sysUserEmail 邮箱.
     */
    public void setSysUserEmail(String sysUserEmail){
        this.sysUserEmail = sysUserEmail;
    }

    /**
     * Get sysUserEmail 邮箱.
     *
     * @return the string
     */
    public String getSysUserEmail(){
        return sysUserEmail;
    }

    /**
     * Set sysUserPhone 手机号.
     */
    public void setSysUserPhone(String sysUserPhone){
        this.sysUserPhone = sysUserPhone;
    }

    /**
     * Get sysUserPhone 手机号.
     *
     * @return the string
     */
    public String getSysUserPhone(){
        return sysUserPhone;
    }

    /**
     * Set sysUserAreaName 区名字.
     */
    public void setSysUserAreaName(String sysUserAreaName){
        this.sysUserAreaName = sysUserAreaName;
    }

    /**
     * Get sysUserAreaName 区名字.
     *
     * @return the string
     */
    public String getSysUserAreaName(){
        return sysUserAreaName;
    }

    /**
     * Set sysUserCityName 市名字.
     */
    public void setSysUserCityName(String sysUserCityName){
        this.sysUserCityName = sysUserCityName;
    }

    /**
     * Get sysUserCityName 市名字.
     *
     * @return the string
     */
    public String getSysUserCityName(){
        return sysUserCityName;
    }

    /**
     * Set sysUserProvinceName 省 名字.
     */
    public void setSysUserProvinceName(String sysUserProvinceName){
        this.sysUserProvinceName = sysUserProvinceName;
    }

    /**
     * Get sysUserProvinceName 省 名字.
     *
     * @return the string
     */
    public String getSysUserProvinceName(){
        return sysUserProvinceName;
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
