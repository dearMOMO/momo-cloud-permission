package com.momo.mapper.dataobject;

import java.util.Date;
import com.momo.mapper.dataobject.BugDO;

/**
 * The table 产品提优
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class BugDO{

    /**
     * id ID.
     */
    private Long id;
    /**
     * uuid UUID.
     */
    private String uuid;
    /**
     * createBy CREATE_BY.
     */
    private String createBy;
    /**
     * updateBy UPDATE_BY.
     */
    private String updateBy;
    /**
     * sysProductImg 图片.
     */
    private String sysProductImg;
    /**
     * sysProductName 登记人.
     */
    private String sysProductName;
    /**
     * sysProductReply 回复.
     */
    private String sysProductReply;
    /**
     * sysProductDetail BUG详细说明.
     */
    private String sysProductDetail;
    /**
     * flag 处理状态 0 已处理 1未处理.
     */
    private Integer flag;
    /**
     * createTime CREATE_TIME.
     */
    private Date createTime;
    /**
     * updateTime UPDATE_TIME.
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
     * Set createBy CREATE_BY.
     */
    public void setCreateBy(String createBy){
        this.createBy = createBy;
    }

    /**
     * Get createBy CREATE_BY.
     *
     * @return the string
     */
    public String getCreateBy(){
        return createBy;
    }

    /**
     * Set updateBy UPDATE_BY.
     */
    public void setUpdateBy(String updateBy){
        this.updateBy = updateBy;
    }

    /**
     * Get updateBy UPDATE_BY.
     *
     * @return the string
     */
    public String getUpdateBy(){
        return updateBy;
    }

    /**
     * Set sysProductImg 图片.
     */
    public void setSysProductImg(String sysProductImg){
        this.sysProductImg = sysProductImg;
    }

    /**
     * Get sysProductImg 图片.
     *
     * @return the string
     */
    public String getSysProductImg(){
        return sysProductImg;
    }

    /**
     * Set sysProductName 登记人.
     */
    public void setSysProductName(String sysProductName){
        this.sysProductName = sysProductName;
    }

    /**
     * Get sysProductName 登记人.
     *
     * @return the string
     */
    public String getSysProductName(){
        return sysProductName;
    }

    /**
     * Set sysProductReply 回复.
     */
    public void setSysProductReply(String sysProductReply){
        this.sysProductReply = sysProductReply;
    }

    /**
     * Get sysProductReply 回复.
     *
     * @return the string
     */
    public String getSysProductReply(){
        return sysProductReply;
    }

    /**
     * Set sysProductDetail BUG详细说明.
     */
    public void setSysProductDetail(String sysProductDetail){
        this.sysProductDetail = sysProductDetail;
    }

    /**
     * Get sysProductDetail BUG详细说明.
     *
     * @return the string
     */
    public String getSysProductDetail(){
        return sysProductDetail;
    }

    /**
     * Set flag 处理状态 0 已处理 1未处理.
     */
    public void setFlag(Integer flag){
        this.flag = flag;
    }

    /**
     * Get flag 处理状态 0 已处理 1未处理.
     *
     * @return the string
     */
    public Integer getFlag(){
        return flag;
    }

    /**
     * Set createTime CREATE_TIME.
     */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
     * Get createTime CREATE_TIME.
     *
     * @return the string
     */
    public Date getCreateTime(){
        return createTime;
    }

    /**
     * Set updateTime UPDATE_TIME.
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    /**
     * Get updateTime UPDATE_TIME.
     *
     * @return the string
     */
    public Date getUpdateTime(){
        return updateTime;
    }
}
