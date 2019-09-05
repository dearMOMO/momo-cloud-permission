package com.momo.service.async;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.momo.common.error.RedisKeyEnum;
import com.momo.common.util.RedisUtil;
import com.momo.mapper.dataobject.AclDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

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

    public Future<String> aclSaveToRedis(AclDO aclDO) {
        Future<String> future = new AsyncResult<>("redis:变更角色状态成功");
        String redisKey = RedisKeyEnum.REDIS_ACL_MAP.getKey() + aclDO.getSysAclPermissionCode();
        String aclsStr = JSONObject.toJSONString(aclDO, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
        redisUtil.set(redisKey, aclsStr);
        return future;
    }
}
