package com.momo.momopermissionsystemcoreweb.controller.authority;

import com.google.common.collect.Maps;
import com.momo.common.common.JSONResult;
import com.momo.mapper.res.authority.AclDto;
import com.momo.mapper.res.authority.LoginAuthReq;
import com.momo.service.service.authority.AdminAuthorityService;
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
    private AdminAuthorityService adminAuthorityService;


    /**
     * 动态权限菜单 (总部和第三方通用)
     *
     * @return
     */
    @RequestMapping("/dynamicMenu/v1")
    public JSONResult dynamicMenu(@Validated(LoginAuthReq.Permission.class) @RequestBody LoginAuthReq loginAuthReq) {
        Map<String, Object> map = Maps.newHashMap();
        List<AclDto> aclModuleLevelDtos_DB = adminAuthorityService.dynamicMenuTree(loginAuthReq);
        map.put("acls", aclModuleLevelDtos_DB);
        return JSONResult.ok(map);

    }
}
