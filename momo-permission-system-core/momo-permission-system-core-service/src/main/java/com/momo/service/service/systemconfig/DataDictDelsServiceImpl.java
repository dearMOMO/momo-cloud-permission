package com.momo.service.service.systemconfig;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.momo.mapper.dataobject.DataDictDelsDO;
import com.momo.mapper.mapper.manual.DataDictDelsMapper;
import com.momo.mapper.req.systemconfig.DataDictDelsReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: DataDictDelsServiceImpl
 * @Author: Jie Li
 * @Date 2019-12-28 17:03
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Service
@Slf4j
public class DataDictDelsServiceImpl implements DataDictDelsService {
    @Autowired
    private DataDictDelsMapper dataDictDelsMapper;

    @Override
    public PageInfo<DataDictDelsDO> dictDelsPageList(DataDictDelsReq dataDictDelsReq) {
        PageHelper.startPage(dataDictDelsReq.getPageNum(), dataDictDelsReq.getPageSize(), "id desc");
        List<DataDictDelsDO> dictDelsPageList = dataDictDelsMapper.dictDelsPageList(dataDictDelsReq.getSysDictDelsName(), dataDictDelsReq.getSysDictDelsValue());
        PageInfo<DataDictDelsDO> pageInfo = new PageInfo<>(dictDelsPageList);
        return pageInfo;
    }
}
