package com.momo.service.async;

import com.momo.netty.service.NettyHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @ClassName: NettyServiceAsync
 * @Author: Jie Li
 * @Date 2019-11-25 17:07
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Service
@Slf4j
@Async("threadPoolTaskExecutor")
public class NettyServiceAsync {

    @Autowired
    private NettyHandlerService nettyHandlerService;

    public Future<String> onlineCount() {
        return nettyHandlerService.onlineCount();
    }
}
