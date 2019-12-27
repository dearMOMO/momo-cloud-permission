package com.momo.momopermissionsystemcoreweb.controller.systemconfig;

import com.momo.common.core.common.JSONResult;
import com.momo.common.log.annotation.SystemCoreLog;
import com.momo.mapper.req.systemconfig.DataDictTreeReq;
import com.momo.service.service.systemconfig.DataDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: DataDictController
 * @Author: Jie Li
 * @Date 2019-12-27 14:28
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@RestController
@RequestMapping(value = "/platform/systemconfig", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@Slf4j
public class DataDictController {
    @Autowired
    private DataDictService dataDictService;

    @SystemCoreLog(description = "数据字典")
    @PostMapping("/dictTree/v1")
    public JSONResult dataDictTree(@RequestBody DataDictTreeReq dataDictTreeReq) {
        return JSONResult.ok(dataDictService.dataDictTree(dataDictTreeReq));
    }
}
