package com.momo.momopermissionsystemcoreweb.controller.intercept;

import com.momo.common.core.common.JSONResult;
import com.momo.mapper.req.sysmain.HasAclReq;
import com.momo.service.service.authority.AdminSysCoreService;
import com.momo.service.service.authority.CommonSysCoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: InterceptController
 * @Author: Jie Li
 * @Date 2019-10-15 17:53
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@RestController
@RequestMapping(value = "/platform/intercept", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@Slf4j
public class InterceptController {
    @Autowired
    private AdminSysCoreService adminSysCoreService;
    @Autowired
    private CommonSysCoreService commonSysCoreService;

    @RequestMapping("/hasAcl/v1")
    public JSONResult hasAcl(@RequestBody HasAclReq hasAclReq) {
        if (hasAclReq.getTenantId().equals(1L)) {
            boolean hasUrlAcl = adminSysCoreService.hasUrlAcl(hasAclReq);
            return JSONResult.ok(hasUrlAcl);
        } else {
            boolean hasUrlAcl = commonSysCoreService.hasUrlAcl(hasAclReq);
            return JSONResult.ok(hasUrlAcl);
        }
    }
}
