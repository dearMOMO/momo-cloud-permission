package com.momo.service.async;

import com.momo.common.core.error.RedisKeyEnum;
import com.momo.netty.utils.ChannelManager;
import com.momo.netty.utils.IMMessage;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Map;
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


    public Future<String> onlineCount() {
        Future<String> future = new AsyncResult<>("更新首页用户在线数量");
        Map<String, Channel> channelMapAll = ChannelManager.getAllChannel();
        if (channelMapAll != null && !channelMapAll.isEmpty()) {
            IMMessage imMessage = new IMMessage(RedisKeyEnum.NETTY_ONLINE_COUNT.getExpireTime(), ChannelManager.sizeChannel(), null);
            channelMapAll.forEach((s, channel) -> ChannelManager.ctxWrite(channel, imMessage));
        }
        return future;
    }
}
