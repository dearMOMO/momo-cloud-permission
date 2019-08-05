package com.momo.momopermissionsystemcoreweb.controller.authority;

import com.momo.common.common.JSONResult;
import com.momo.mapper.req.authority.AclReq;
import com.momo.service.service.authority.AclService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: momo-cloud-permission
 * @description: 权限设置
 * @author: Jie Li
 * @create: 2019-07-31 13:52
 **/
@RestController
@RequestMapping(value = "/platform/acl", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@Slf4j
public class AclController {
    @Autowired
    private AclService aclService;

    @RequestMapping("/aclTree/v1")
    public JSONResult aclTree() {
        return JSONResult.ok(aclService.aclTree());
    }

    @RequestMapping("/save/v1")
    public JSONResult save(@Validated(AclReq.save.class) @RequestBody AclReq aclReq) {
        return JSONResult.ok(aclService.insertSelective(aclReq));
    }

    @RequestMapping("/modify/v1")
    public JSONResult modify(@Validated(AclReq.Modify.class) @RequestBody AclReq aclReq) {
        return JSONResult.ok(aclService.updateByPrimaryKeySelective(aclReq));
    }

    @RequestMapping("/status/v1")
    public JSONResult status(@Validated(AclReq.Status.class) @RequestBody AclReq aclReq) {
        return JSONResult.ok(aclService.updateStatus(aclReq));
    }
}
