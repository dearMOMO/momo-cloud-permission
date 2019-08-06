package com.momo.mapper.req.aclmanager;

import com.momo.common.error.BaseReq;
import lombok.*;

/**
 * @program: momo-cloud-permission
 * @description: TODO
 * @author: Jie Li
 * @create: 2019-08-06 13:14
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true, of = {"id"})
public class UserGroupPageReq extends BaseReq {

    //企业名称
    private String userGroupName;
    //flag 状态 0启用  1禁用.
    private Integer flag;
}
