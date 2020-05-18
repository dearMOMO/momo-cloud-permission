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
package com.momo.momopermissionsystemcoreweb.controller.authority;

import com.google.common.collect.Maps;
import com.momo.common.core.common.JSONResult;
import com.momo.mapper.req.sysmain.DynamicMenuAuthorReq;
import com.momo.mapper.res.authority.AclLevelRes;
import com.momo.service.service.authority.DynamicMenuTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by MOMO on 2019/4/10.
 */
@RestController
@RequestMapping(value = "/platform/authority", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@Slf4j
public class AuthorityController {

    @Autowired
    private DynamicMenuTreeService dynamicMenuTreeService;


    /**
     * 动态权限菜单 (总部和第三方通用)
     *
     * @return
     */
    @RequestMapping("/dynamicMenu/v1")
    public JSONResult dynamicMenu(@Validated(DynamicMenuAuthorReq.Permission.class) @RequestBody DynamicMenuAuthorReq loginAuthReq) {
        Map<String, Object> map = Maps.newHashMap();
        List<AclLevelRes> aclModuleLevelDtos_DB = dynamicMenuTreeService.dynamicMenuTree(loginAuthReq);
        map.put("acls", aclModuleLevelDtos_DB);
        return JSONResult.ok(map);

    }

    /**
     * 动态权限菜单 (总部和第三方通用)
     *
     * @return
     */
    @RequestMapping("/dynamicMenu/v2")
    public JSONResult dynamicMenu_V2(@Validated(DynamicMenuAuthorReq.Permission.class) @RequestBody DynamicMenuAuthorReq loginAuthReq) {
        Map<String, Object> map = Maps.newHashMap();
        List<AclLevelRes> aclModuleLevelDtos_DB = dynamicMenuTreeService.dynamicMenuTree(loginAuthReq);
        map.put("acls", aclModuleLevelDtos_DB);
        return JSONResult.ok(map);

    }
}
