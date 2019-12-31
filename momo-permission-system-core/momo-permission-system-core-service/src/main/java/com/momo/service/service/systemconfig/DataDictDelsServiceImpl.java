package com.momo.service.service.systemconfig;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.momo.common.core.entity.RedisUser;
import com.momo.common.core.error.BizException;
import com.momo.common.core.util.DateUtils;
import com.momo.mapper.dataobject.DataDictDelsDO;
import com.momo.mapper.mapper.manual.DataDictDelsMapper;
import com.momo.mapper.req.systemconfig.DataDictDelsReq;
import com.momo.mapper.res.systemconfig.DataDictDelsRes;
import com.momo.service.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
public class DataDictDelsServiceImpl extends BaseService implements DataDictDelsService {
    @Autowired
    private DataDictDelsMapper dataDictDelsMapper;

    @Override
    public PageInfo<DataDictDelsRes> dictDelsPageList(DataDictDelsReq dataDictDelsReq) {
        PageHelper.startPage(dataDictDelsReq.getPageNum(), dataDictDelsReq.getPageSize(), "id desc");
        List<DataDictDelsDO> dictDelsPageList = dataDictDelsMapper.dictDelsPageList(dataDictDelsReq.getSysDictDelsName(), dataDictDelsReq.getSysDictDelsValue());
        PageInfo<DataDictDelsDO> pageInfo = new PageInfo<>(dictDelsPageList);
        List<DataDictDelsDO> dataDictDelsDOS = pageInfo.getList();
        if (CollectionUtils.isEmpty(dataDictDelsDOS)) {
            return new PageInfo<>();
        }
        List<DataDictDelsRes> dataDictDelsResList = Lists.newArrayList();
        dataDictDelsDOS.forEach(dataDictDelsDO -> {
            DataDictDelsRes dataDictDelsRes = new DataDictDelsRes();
            BeanUtils.copyProperties(dataDictDelsDO, dataDictDelsRes);
            dataDictDelsRes.setIdStr(String.valueOf(dataDictDelsDO.getId()));
            dataDictDelsResList.add(dataDictDelsRes);
        });
        PageInfo<DataDictDelsRes> dataDictDelsResPageInfo = new PageInfo<>(dataDictDelsResList);
        dataDictDelsResPageInfo.setTotal(pageInfo.getTotal());
        dataDictDelsResPageInfo.setPageNum(pageInfo.getPageNum());
        dataDictDelsResPageInfo.setPageSize(pageInfo.getPageSize());
        return dataDictDelsResPageInfo;
    }

    @Override
    public String dictDelsSave(DataDictDelsReq dataDictDelsReq) {
        DataDictDelsDO selectByPrimaryKey = dataDictDelsMapper.selectByPrimaryKey(dataDictDelsReq.getId());
        if (selectByPrimaryKey == null) {
            throw BizException.fail("待编辑的数据字典详情不存在");
        }
        DataDictDelsDO dataDictDelsDO = new DataDictDelsDO();
        BeanUtils.copyProperties(dataDictDelsReq, dataDictDelsDO);
        RedisUser redisUser = this.redisUser();
        dataDictDelsDO.setCreateBy(redisUser.getSysUserName());
        dataDictDelsDO.setUpdateBy(redisUser.getSysUserName());
        dataDictDelsDO.setCreateTime(DateUtils.getCurrentTime());
        dataDictDelsDO.setUpdateTime(DateUtils.getCurrentTime());
        dataDictDelsMapper.updateByPrimaryKeySelective(dataDictDelsDO);
        return null;
    }


}
