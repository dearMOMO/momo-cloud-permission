package com.momo.momopermissionsystemcoreweb.controller.netty;

import com.momo.common.core.common.JSONResult;
import com.momo.service.service.netty.NettyHomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: NettyHomeController
 * @Author: Jie Li
 * @Date 2019-11-26 14:07
 * @Description: 首页netty推送数据初始化
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@RestController
@RequestMapping(value = "/platform/netty/initialization", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@Slf4j
public class NettyHomeController {
    @Autowired
    private NettyHomeService nettyHomeService;

    /**
     * 实时在线人数
     * @return
     */
    @RequestMapping("/onlineCount/v1")
    public JSONResult onlineCount() {
        return JSONResult.ok(nettyHomeService.onlineCount());
    }
}
