package com.momo.momopermissionsystemcoreweb.controller.systemconfig;

import com.momo.common.core.common.JSONResult;
import com.momo.common.log.annotation.SystemCoreLog;
import com.momo.mapper.req.systemconfig.DataDictDelsReq;
import com.momo.service.service.systemconfig.DataDictDelsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: DataDictDelsController
 * @Author: Jie Li
 * @Date 2019-12-28 17:13
 * @Description: 数据字典详情
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@RestController
@RequestMapping(value = "/platform/systemconfig", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@Slf4j
public class DataDictDelsController {
    @Autowired
    private DataDictDelsService dataDictDelsService;

    @SystemCoreLog(description = "数据字典 详情分页")
    @PostMapping("/dictDelsPageList/v1")
    public JSONResult dictDelsPageList(@Validated(DataDictDelsReq.Page.class) @RequestBody DataDictDelsReq dataDictDelsReq) {
        return JSONResult.ok(dataDictDelsService.dictDelsPageList(dataDictDelsReq));
    }
}
