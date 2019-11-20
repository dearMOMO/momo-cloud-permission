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
package com.momo.mapper.req.sysmain;


import com.momo.common.core.error.BaseReq;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * @program: momo-cloud-permission
 * @description: TODO
 * @author: Jie Li
 * @create: 2019-07-30 15:12
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = {"id"})
public class SysUserLoginReq extends BaseReq {
    /**
     * sysUserPwd 密码.
     */
    @NotBlank(message = "密码必填", groups = {Query.class})
    private String sysUserPwd;

    /**
     * sysUserLoginName 登录名.
     */
    @NotBlank(message = "登录名必填", groups = {Query.class})
    private String sysUserLoginName;
    @NotBlank(message = "验证码不能为空", groups = {Query.class})
    private String verificationCode;//验证码
    @NotBlank(message = "验证码唯一标识必填", groups = {Query.class})
    private String verUUidCode;//该验证码对应唯一一个用户
}
