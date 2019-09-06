package com.momo.service.async;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Maps;
import com.momo.common.error.RedisKeyEnum;
import com.momo.common.util.RedisUtil;
import com.momo.mapper.dataobject.AclDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.service.async
 * @Description: TODO
 * @Author: Jie Li
 * @CreateDate: 2019/9/4 0004 23:16
 * @UpdateDate: 2019/9/4 0004 23:16
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Service
@Slf4j
@Async("threadPoolTaskExecutor")
public class AclRedisCacheServiceAsync {
    @Autowired
    private RedisUtil redisUtil;


    public void aclSaveToRedis(AclDO aclDO) {
        String redisKey = RedisKeyEnum.REDIS_ACL_MAP.getKey() + aclDO.getSysAclPermissionCode();
        String aclsStr = JSONObject.toJSONString(aclDO, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
        redisUtil.hset(redisKey, String.valueOf(aclDO.getId()), aclsStr);
    }

    public void modifyAclToRedis(AclDO aclSelf, List<AclDO> aclsChild) {
        String redisKey = RedisKeyEnum.REDIS_ACL_MAP.getKey() + aclSelf.getSysAclPermissionCode();
        String aclsStr = JSONObject.toJSONString(aclSelf, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
        Map<String, String> aclMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(aclsChild)) {
            aclsChild.forEach(aclDO -> {
                String aclMapStr = JSONObject.toJSONString(aclDO, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
                aclMap.put(String.valueOf(aclDO.getId()), aclMapStr);
            });
        }
        aclMap.put(String.valueOf(aclSelf.getId()), aclsStr);
        redisUtil.hsetputAll(redisKey, aclMap);
    }
}
