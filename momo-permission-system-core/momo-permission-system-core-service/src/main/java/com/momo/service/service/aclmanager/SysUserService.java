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
package com.momo.service.service.aclmanager;

import com.github.pagehelper.PageInfo;
import com.momo.mapper.dataobject.UserDO;
import com.momo.mapper.req.aclmanager.SysUserAddReq;
import com.momo.mapper.req.aclmanager.SysUserListReq;
import com.momo.mapper.res.aclmanager.SysUserListRes;

/**
 * @program: momo-cloud-permission
 * @description: 用户管理
 * @author: Jie Li
 * @create: 2019-08-02 17:20
 **/
public interface SysUserService {

    /**
     * 用户列表分页
     *
     * @param sysUserListReq
     * @return
     */
    PageInfo<SysUserListRes> sysUserList(SysUserListReq sysUserListReq);

    /**
     * 用户新增--账号密码
     *
     * @param sysUserAddReq
     * @return
     */
    String sysUserAdd(SysUserAddReq sysUserAddReq);

    /**
     * 查询用户详情
     *
     * @param sysUserAddReq
     * @return
     */
    UserDO sysUserDetail(SysUserAddReq sysUserAddReq);

    /**
     * 编辑
     *
     * @param sysUserAddReq
     * @return
     */
    String sysUserModify(SysUserAddReq sysUserAddReq);

    /**
     * 用户状态
     *
     * @param sysUserAddReq
     * @return
     */
    String sysUserStatus(SysUserAddReq sysUserAddReq);

    /**
     * 用户密码
     *
     * @param sysUserAddReq
     * @return
     */
    String sysUserPwd(SysUserAddReq sysUserAddReq);
}
