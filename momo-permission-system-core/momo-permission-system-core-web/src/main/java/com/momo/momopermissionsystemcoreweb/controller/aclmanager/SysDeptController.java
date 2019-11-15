package com.momo.momopermissionsystemcoreweb.controller.aclmanager;

import com.momo.common.core.common.JSONResult;
import com.momo.service.service.aclmanager.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: SysDeptController
 * @Author: Jie Li
 * @Date 2019-11-15 16:47
 * @Description: 部门控制器
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@RestController
@RequestMapping(value = "/platform/dept", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@Slf4j
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 部门树
     *
     * @return
     */
    @PostMapping("/deptTree/v1")
    public JSONResult sysUserList() {
        return JSONResult.ok(sysDeptService.deptTree());
    }
}
