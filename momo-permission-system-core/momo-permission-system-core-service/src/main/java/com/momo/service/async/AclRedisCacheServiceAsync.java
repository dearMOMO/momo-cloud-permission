/**
 * Copyright (c) 2018-2019, Jie Li 李杰 (mqgnsds@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.momo.service.async;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Maps;
import com.momo.common.core.error.RedisKeyEnum;
import com.momo.common.core.util.RedisUtil;
import com.momo.mapper.dataobject.AclDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: momo-cloud-permission
 * @Description: 权限数据异步同步到Redis
 * @Author: Jie Li
 * @CreateDate: 2019-09-04 23:16
 * @UpdateDate: 2019-09-04 23:16
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Service
@Slf4j
public class AclRedisCacheServiceAsync {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 新增redis里的权限
     *
     * @param aclDO
     */
    @Async("threadPoolTaskExecutor")
    public void aclSaveToRedis(AclDO aclDO) {
        String redisKey = RedisKeyEnum.REDIS_ACL_MAP.getKey() + aclDO.getSysAclPermissionCode();
        String aclsStr = JSONObject.toJSONString(aclDO, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
        redisUtil.hset(redisKey, String.valueOf(aclDO.getId()), aclsStr);
    }

    /**
     * 更新redis里的权限
     *
     * @param aclSelf
     * @param aclsChild
     */
    @Async("threadPoolTaskExecutor")
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
