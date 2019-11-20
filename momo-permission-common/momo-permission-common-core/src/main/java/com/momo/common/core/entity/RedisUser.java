/**
 * Copyright (c) 2018-2019, Jie Li 李杰 (mqgnsds@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.momo.common.core.entity;

import lombok.*;

/**
 * @ClassName: RedisUser
 * @Author: Jie Li
 * @Date 2019-11-20 14:42
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = {"id"})
public class RedisUser {

    //redis的随机数
    private String redisUserKey;
    //登录类型
    private Integer loginType;
    /**
     * 用户基础表id.
     */
    private Long baseId;
    //密码id
    private Long pwdId;

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 用户基础表 姓名.
     */
    private String sysUserName;

    /**
     * 用户基础表 邮箱.
     */
    private String sysUserEmail;
    /**
     * 用户基础表 手机号.
     */
    private String sysUserPhone;
    /**
     * sysLoginNumber 账号允许登录的次数 -1 不限次数 ，如需登录次数为0，请禁用该账号，减少代码复杂度.
     */
    private Integer sysLoginNumber;

    /**
     * sysUserLoginName 登录名.
     */
    private String sysUserLoginName;

}
