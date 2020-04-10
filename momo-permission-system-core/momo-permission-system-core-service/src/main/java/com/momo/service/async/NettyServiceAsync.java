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
 * @Description: Netty相关
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Service
@Slf4j
public class NettyServiceAsync {

    @Autowired
    private NettyHandlerService nettyHandlerService;

    /**
     * 实时在线人数统计
     *
     * @param symbol
     * @return
     */
    public Future<String> onlineCount(String symbol) {
        return nettyHandlerService.onlineCount(symbol);
    }
}
