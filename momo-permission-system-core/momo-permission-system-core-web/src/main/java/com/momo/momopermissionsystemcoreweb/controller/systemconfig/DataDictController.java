package com.momo.momopermissionsystemcoreweb.controller.systemconfig;

import com.momo.common.core.common.JSONResult;
import com.momo.common.log.annotation.SystemCoreLog;
import com.momo.mapper.req.systemconfig.DataDictTreeReq;
import com.momo.service.service.systemconfig.DataDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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

    @SystemCoreLog(description = "数据字典 树")
    @PostMapping("/dictTree/v1")
    public JSONResult dataDictTree(@RequestBody DataDictTreeReq dataDictTreeReq) {
        return JSONResult.ok(dataDictService.dataDictTree(dataDictTreeReq));
    }

    @SystemCoreLog(description = "新增 数据字典")
    @PostMapping("/dictSave/v1")
    public JSONResult dictSave(@Validated(DataDictTreeReq.Add.class) @RequestBody DataDictTreeReq dataDictTreeReq) {
        return JSONResult.ok(dataDictService.dictSave(dataDictTreeReq));
    }

    @SystemCoreLog(description = "编辑 数据字典")
    @PostMapping("/dictModify/v1")
    public JSONResult dictModify(@Validated(DataDictTreeReq.Modify.class) @RequestBody DataDictTreeReq dataDictTreeReq) {
        return JSONResult.ok(dataDictService.dictModify(dataDictTreeReq));
    }

    @SystemCoreLog(description = "编辑 数据字典 状态")
    @PostMapping("/dictStatus/v1")
    public JSONResult dictStatus(@RequestBody DataDictTreeReq dataDictTreeReq) {
        return JSONResult.ok(dataDictService.dictStatus(dataDictTreeReq));
    }


}
