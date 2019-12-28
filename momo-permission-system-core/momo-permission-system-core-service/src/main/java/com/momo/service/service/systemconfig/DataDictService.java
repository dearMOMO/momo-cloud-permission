package com.momo.service.service.systemconfig;

import com.momo.mapper.req.systemconfig.DataDictTreeReq;
import com.momo.mapper.res.systemconfig.DataDictLevelRes;


/**
 * @ClassName: DataDictService
 * @Author: Jie Li
 * @Date 2019-12-25 16:13
 * @Description: 数据字典相关
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
public interface DataDictService {

    DataDictLevelRes dataDictTree(DataDictTreeReq dataDictTreeReq);
}
