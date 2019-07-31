package com.momo.mapper.res.sysmain;

import lombok.*;

/**
 * @program: momo-cloud-permission
 * @description: 用户信息
 * @author: Jie Li
 * @create: 2019-07-31 10:55
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = {"id"})
public class UserInfoRes {

    /**
     * 用户基础表 姓名.
     */
    private String sysUserName;


}
