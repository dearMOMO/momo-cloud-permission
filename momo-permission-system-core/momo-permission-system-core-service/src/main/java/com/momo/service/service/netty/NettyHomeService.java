package com.momo.service.service.netty;

import com.momo.netty.utils.ChannelManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @ClassName: NettyHomeService
 * @Author: Jie Li
 * @Date 2019-11-26 14:05
 * @Description: 首页netty推送数据初始化
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Service
@Slf4j
public class NettyHomeService {

    /**
     * 实时在线人数
     *
     * @return
     */
    public int onlineCount() {
        return ChannelManager.sizeChannel();
    }
}
