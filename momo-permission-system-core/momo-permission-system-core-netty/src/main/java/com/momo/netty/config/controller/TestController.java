package com.momo.netty.config.controller;

import com.alibaba.fastjson.JSONObject;
import com.momo.common.core.common.JSONResult;
import com.momo.common.core.common.UserChannelCurrentMap;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.collections4.MapUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.momopermissionsystemcoreweb.controller
 * @Description: TODO
 * @Author: Jie Li
 * @CreateDate: 2019/9/9 0009 13:53
 * @UpdateDate: 2019/9/9 0009 13:53
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@RestController
public class TestController {
    @RequestMapping("/sendWebsocket")
    public JSONResult sendWebsocket() {
        Map<String, Channel> channelHashMap = UserChannelCurrentMap.getAllChannel();
        if (MapUtils.isNotEmpty(channelHashMap)) {
            channelHashMap.forEach((s, channel) -> channel.writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(JSONResult.ok(s + "我是服务器端发送消息/n")))));
            return JSONResult.ok(channelHashMap);
        }
        return JSONResult.errorException("没有通道");
    }
}
