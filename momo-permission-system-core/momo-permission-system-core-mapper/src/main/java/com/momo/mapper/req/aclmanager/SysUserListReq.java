package com.momo.mapper.req.aclmanager;

import com.momo.common.error.BaseReq;
import lombok.*;

/**
 * @program: momo-cloud-permission
 * @description: TODO
 * @author: Jie Li
 * @create: 2019-08-02 17:28
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true, of = {"id"})
public class SysUserListReq extends BaseReq {
    /**
     * sysUserName 姓名.
     */
    private String sysUserName;
    /**
     * disabledFlag 是否被禁用  0否 1禁用.
     */
    private Integer disabledFlag;

}
