package com.momo.service.service.systemconfig;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.momo.common.core.entity.RedisUser;
import com.momo.common.core.error.BizException;
import com.momo.common.core.util.DateUtils;
import com.momo.common.core.util.LevelUtil;
import com.momo.common.core.util.snowFlake.SnowFlake;
import com.momo.mapper.dataobject.AclDO;
import com.momo.mapper.dataobject.DataDictDO;
import com.momo.mapper.enums.DisabledFlagEnum;
import com.momo.mapper.mapper.manual.DataDictMapper;
import com.momo.mapper.req.systemconfig.DataDictTreeReq;
import com.momo.mapper.res.systemconfig.DataDictLevelRes;
import com.momo.mapper.res.systemconfig.DataDictTreeRes;
import com.momo.service.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
    private static final SnowFlake snowFlake = new SnowFlake(1, 2);

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
            if (dictTreeRes.getDisabledFlag().equals(DisabledFlagEnum.start.type)) {
                dictTreeRes.setDisabled(false);
            }
            dictTreeResList.add(dictTreeRes);
        });
        List<DataDictTreeRes> dictListToTree = dictListToTree(dictTreeResList);
        dataDictLevelRes.setDataDictTreeRes(dictListToTree);
        return dataDictLevelRes;
    }

    @Override
    public String dictSave(DataDictTreeReq dataDictTreeReq) {
        if (checkCodeValue(dataDictTreeReq.getSysDictCodeValue(), null)) {
            throw BizException.fail("参数值重复");
        }
        if (checkSameLevelName(null, null, dataDictTreeReq.getSysDictCodeName(), dataDictTreeReq.getId())) {
            throw BizException.fail("参数名称重复");
        }
        RedisUser redisUser = this.redisUser();
        DataDictDO dataDictDOInsert = new DataDictDO();
        BeanUtils.copyProperties(dataDictTreeReq, dataDictDOInsert);
        dataDictDOInsert.setCreateBy(redisUser.getSysUserName());
        dataDictDOInsert.setUpdateBy(redisUser.getSysUserName());
        dataDictDOInsert.setCreateTime(DateUtils.getCurrentTime());
        dataDictDOInsert.setUpdateTime(DateUtils.getCurrentTime());
        if (!dataDictTreeReq.getSysDictCodeParentId().equals(0L)) {
            //得到父级信息
            DataDictDO dataDictDO = dataDictMapper.selectByPrimaryKey(dataDictTreeReq.getSysDictCodeParentId());
            if (dataDictDO == null) {
                throw BizException.fail("查询父级信息不存在");
            }
            dataDictDOInsert.setSysDictCodeLevel(LevelUtil.calculateLevel(dataDictDO.getSysDictCodeLevel(), dataDictDO.getId()));
            dataDictDOInsert.setSysDictCodeParentId(dataDictDO.getSysDictCodeParentId());
            dataDictDOInsert.setSysDictCodeParentValue(dataDictDO.getSysDictCodeParentValue());
        } else {
            dataDictDOInsert.setSysDictCodeParentId(0L);
            dataDictDOInsert.setSysDictCodeParentValue("0");
            dataDictDOInsert.setSysDictCodeLevel("0");
        }
        dataDictDOInsert.setSysDictCodeSeq(Integer.valueOf(dataDictTreeReq.getSysDictCodeSeq()));
        dataDictMapper.insertSelective(dataDictDOInsert);

        return "新增数据字典成功";
    }

    @Override
    public String dictModify(DataDictTreeReq dataDictTreeReq) {
        DataDictDO before = dataDictMapper.selectByPrimaryKey(dataDictTreeReq.getId());
        if (before == null) {
            throw BizException.fail("待编辑的数据字典不存在");
        }
        if (before.getId().equals(dataDictTreeReq.getSysDictCodeParentId())) {
            throw BizException.fail("您无法将自己挂在自己模块下");
        }

        if (checkCodeValue(dataDictTreeReq.getSysDictCodeValue(), dataDictTreeReq.getId())) {
            throw BizException.fail("参数值重复");
        }
        if (checkSameLevelName(null, null, dataDictTreeReq.getSysDictCodeName(), dataDictTreeReq.getId())) {
            throw BizException.fail("参数名称重复");
        }
        RedisUser redisUser = this.redisUser();
        DataDictDO after = new DataDictDO();
        BeanUtils.copyProperties(dataDictTreeReq, after);
        if (!dataDictTreeReq.getSysDictCodeParentId().equals(0L)) {
            DataDictDO fatherDict = dataDictMapper.selectByPrimaryKey(dataDictTreeReq.getSysDictCodeParentId());
            if (null == fatherDict) {
                throw BizException.fail("父级数据字典不存在");
            }
            after.setSysDictCodeLevel(LevelUtil.calculateLevel(fatherDict.getSysDictCodeLevel(), dataDictTreeReq.getSysDictCodeParentId()));
        } else {
            after.setSysDictCodeLevel("0");
        }
        after.setUpdateBy(redisUser.getSysUserName());
        after.setUpdateTime(DateUtils.getCurrentTime());
        after.setId(before.getId());
        after.setSysDictCodeSeq(Integer.valueOf(dataDictTreeReq.getSysDictCodeSeq()));
        updateWithChild(before, after);
        return "编辑数据字典成功";
    }

    @Override
    public String dictStatus(DataDictTreeReq dataDictTreeReq) {
        DataDictDO dataDictDO = dataDictMapper.selectByPrimaryKey(dataDictTreeReq.getId());
        if (dataDictDO == null) {
            throw BizException.fail("待编辑的数据字典不存在");
        }
        DataDictDO dataDictStatus = new DataDictDO();
        dataDictStatus.setId(dataDictDO.getId());
        if (dataDictTreeReq.getDisabledFlag().equals(DisabledFlagEnum.start.type)) {
            dataDictStatus.setDisabledFlag(DisabledFlagEnum.disabled.type);
        } else if (dataDictTreeReq.getDisabledFlag().equals(DisabledFlagEnum.disabled.type)) {
            dataDictStatus.setDisabledFlag(DisabledFlagEnum.start.type);
        } else {
            dataDictStatus.setDisabledFlag(DisabledFlagEnum.disabled.type);
        }
        dataDictMapper.updateByPrimaryKeySelective(dataDictStatus);
        return "变更数据字典状态成功";
    }

    private boolean checkCodeValue(String sysDictCodeValue, Long id) {
        return dataDictMapper.checkCodeValue(sysDictCodeValue, id) > 0;
    }

    private boolean checkSameLevelName(Long sysDictCodeParentId, String sysDictCodeParentValue, String sysDictCodeName, Long id) {
        return dataDictMapper.checkSameLevelName(sysDictCodeParentId, sysDictCodeParentValue, sysDictCodeName, id) > 0;
    }

    private String getLevel(Long dictParentId) {
        DataDictDO aclModule = dataDictMapper.selectByPrimaryKey(dictParentId);
        if (aclModule == null) {
            return null;
        }
        return aclModule.getSysDictCodeLevel();
    }

    private void updateWithChild(DataDictDO before, DataDictDO after) {
        String newLevelPrefix = after.getSysDictCodeLevel();
        String oldLevelPrefix = before.getSysDictCodeLevel();
        List<DataDictDO> aclModuleList;
        if (!after.getSysDictCodeLevel().equals(before.getSysDictCodeLevel())) {
            aclModuleList = dataDictMapper.getChildDictListByLevel(LevelUtil.calculateLevel(before.getSysDictCodeLevel(), before.getId()));
            if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(aclModuleList)) {
                for (DataDictDO aclModule : aclModuleList) {
                    String level = aclModule.getSysDictCodeLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        aclModule.setSysDictCodeLevel(level);
                    }
                }
                dataDictMapper.batchUpdateLevel(aclModuleList);

            }
        }
        dataDictMapper.updateByPrimaryKeySelective(after);
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