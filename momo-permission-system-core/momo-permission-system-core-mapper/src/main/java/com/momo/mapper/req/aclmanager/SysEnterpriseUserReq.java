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
package com.momo.mapper.req.aclmanager;

import com.google.common.collect.Lists;
import com.momo.common.core.error.BaseReq;
import com.momo.mapper.dataobject.AclDO;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.mapper.req.aclmanager
 * @Description: TODO
 * @Author: Jie Li
 * @CreateDate: 2019/8/23 0023 13:24
 * @UpdateDate: 2019/8/23 0023 13:24
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
public class SysEnterpriseUserReq extends BaseReq {
    @NotBlank(message = "enterpriseUuid必填", groups = {Modify.class, Status.class, Detail.class, Permission.class, Add.class, Query.class, Author.class})
    private String enterpriseUuid;

    @NotBlank(message = "uuid必填", groups = {Modify.class, Status.class, Detail.class, Permission.class, Add.class, Author.class})
    private String uuid;

    private List<AclDO> acls = Lists.newArrayList();
    private List<Long> roleIds = Lists.newArrayList();
    /**
     * sysUserName 姓名.
     */
    @NotBlank(message = "姓名必填", groups = {Add.class, Modify.class})
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
     * disabledFlag 是否被禁用  0否 1禁用.
     */
    @NotNull(message = "状态必填", groups = {Add.class, Modify.class, Status.class})
    private Integer disabledFlag;
    /**
     * sysUserLoginName 登录名.
     */
    @NotBlank(message = "登录名必填", groups = {Add.class})
    @Email(message = "邮箱格式不正确")
    private String sysUserLoginName;
    /**
     * sysUserPwd 密码.
     */
    @NotBlank(message = "密码必填", groups = {Add.class, Permission.class})
    private String sysUserPwd;
    /**
     * remark 备注.
     */
    private String remark;
}
