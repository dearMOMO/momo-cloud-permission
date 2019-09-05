package com.momo.mapper.cache;

import lombok.*;

import java.util.Date;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.mapper.cache
 * @Description: TODO
 * @Author: Jie Li
 * @CreateDate: 2019/9/4 0004 14:39
 * @UpdateDate: 2019/9/4 0004 14:39
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true, of = {"id"})
public class RoleDORedisCache {
    /**
     * id 角色id.
     */
    private Long id;
    /**
     * tenantId 租户id.
     */
    private Long tenantId;
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
     * updateBy update_by.
     */
    private String updateBy;
    /**
     * sysRoleName 角色名称.
     */
    private String sysRoleName;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private Integer delFlag;
    /**
     * sysRoleType 角色的类型，0：管理员(老板)，1：管理员(员工)  2:普通员工 3:其他.
     */
    private Integer sysRoleType;
    /**
     * disabledFlag 是否被禁用  0否 1禁用.
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
}
