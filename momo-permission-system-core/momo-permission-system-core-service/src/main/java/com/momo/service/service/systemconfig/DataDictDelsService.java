package com.momo.service.service.systemconfig;

import com.github.pagehelper.PageInfo;
import com.momo.mapper.dataobject.DataDictDelsDO;
import com.momo.mapper.req.systemconfig.DataDictDelsReq;

/**
 * @ClassName: DataDictDelsService
 * @Author: Jie Li
 * @Date 2019-12-28 17:03
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
public interface DataDictDelsService {
    PageInfo<DataDictDelsDO> dictDelsPageList(DataDictDelsReq dataDictDelsReq);
}
