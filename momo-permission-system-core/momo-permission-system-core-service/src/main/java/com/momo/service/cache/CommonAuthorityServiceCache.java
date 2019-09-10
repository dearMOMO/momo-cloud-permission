package com.momo.service.cache;

import com.momo.common.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.service.cache
 * @Description: TODO
 * @Author: Jie Li
 * @CreateDate: 2019/9/10 0010 11:22
 * @UpdateDate: 2019/9/10 0010 11:22
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Slf4j
@Service
public class CommonAuthorityServiceCache {
    @Autowired
    private RedisUtil redisUtil;
}
