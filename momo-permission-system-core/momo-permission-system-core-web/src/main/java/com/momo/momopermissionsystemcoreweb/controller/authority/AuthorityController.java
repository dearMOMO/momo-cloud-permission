package com.momo.momopermissionsystemcoreweb.controller.authority;

import com.google.common.collect.Maps;
import com.momo.common.common.JSONResult;
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
}
