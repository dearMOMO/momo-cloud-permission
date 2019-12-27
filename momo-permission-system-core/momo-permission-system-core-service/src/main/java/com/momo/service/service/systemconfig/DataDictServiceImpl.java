package com.momo.service.service.systemconfig;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.momo.common.core.entity.RedisUser;
import com.momo.common.core.error.BizException;
import com.momo.common.core.util.LevelUtil;
import com.momo.mapper.dataobject.DataDictDO;
import com.momo.mapper.mapper.manual.DataDictMapper;
import com.momo.mapper.req.systemconfig.DataDictTreeReq;
import com.momo.mapper.res.systemconfig.DataDictLevelRes;
import com.momo.mapper.res.systemconfig.DataDictTreeRes;
import com.momo.service.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;

/**
 * @ClassName: DataDictServiceImpl
 * @Author: Jie Li
 * @Date 2019-12-25 16:13
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Service
@Slf4j
public class DataDictServiceImpl extends BaseService implements DataDictService {
    @Autowired
    private DataDictMapper dataDictMapper;

    @Override
    public DataDictLevelRes dataDictTree(DataDictTreeReq dataDictTreeReq) {
        RedisUser redisUser = this.redisUser();
        if (!redisUser.getTenantId().equals(1L)) {
            throw new BizException("您不是MOMO企业下的VIP,无权操作");
        }
        DataDictLevelRes dataDictLevelRes = new DataDictLevelRes();
        List<DataDictDO> dataDiceGetAll = dataDictMapper.dataDiceGetAll(0, dataDictTreeReq.getSysDictCodeParentValue(), dataDictTreeReq.getSysDictCodeParentValue());
        if (CollectionUtils.isEmpty(dataDiceGetAll)) {
            return dataDictLevelRes;
        }
        List<DataDictTreeRes> dictTreeResList = Lists.newArrayList();
        dataDiceGetAll.forEach(dataDictDO -> {
            DataDictTreeRes dictTreeRes = DataDictTreeRes.dictTreeRes(dataDictDO);
            //状态 0启用  1禁用
            if (dictTreeRes.getDisabledFlag().equals(1)) {
                dictTreeRes.setDisabled(false);
            }
            dictTreeResList.add(dictTreeRes);
        });
        List<DataDictTreeRes> dictListToTree = dictListToTree(dictTreeResList);
        dataDictLevelRes.setDataDictTreeRes(dictListToTree);
        return dataDictLevelRes;
    }

    private List<DataDictTreeRes> dictListToTree(List<DataDictTreeRes> dictTreeList) {
        Multimap<String, DataDictTreeRes> levelDeptMap = ArrayListMultimap.create();
        List<DataDictTreeRes> rootList = Lists.newArrayList();
        for (DataDictTreeRes dataDictTreeRes : dictTreeList) {
            levelDeptMap.put(dataDictTreeRes.getSysDictCodeLevel(), dataDictTreeRes);
            if (LevelUtil.ROOT.equals(dataDictTreeRes.getSysDictCodeLevel())) {
                rootList.add(dataDictTreeRes);
            }
        }
        // 按照seq从大到小排序
        rootList.sort((o1, o2) -> o2.getSysDictCodeSeq() - o1.getSysDictCodeSeq());
        // 递归生成树
        transformDictTree(rootList, LevelUtil.ROOT, levelDeptMap);
        return rootList;
    }

    private void transformDictTree(List<DataDictTreeRes> deptLevelList, String level, Multimap<String, DataDictTreeRes> levelDeptMap) {
        for (DataDictTreeRes deptLevelDto : deptLevelList) {
            // 遍历该层的每个元素
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDto.getId());
            // 处理下一层
            List<DataDictTreeRes> tempDeptList = (List<DataDictTreeRes>) levelDeptMap.get(nextLevel);
            if (!CollectionUtils.isEmpty(tempDeptList)) {
                // 排序
                tempDeptList.sort(deptSeqComparator);
                // 设置下一层部门
                deptLevelDto.setChildren(tempDeptList);
                // 进入到下一层处理
                transformDictTree(tempDeptList, nextLevel, levelDeptMap);
            }
        }
    }

    private Comparator<DataDictTreeRes> deptSeqComparator = (o1, o2) -> o2.getSysDictCodeSeq() - o1.getSysDictCodeSeq();
}