package com.momo.momopermissionsystemcoreweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.momo.common.common.JSONResult;
import com.momo.netty.common.UserChannelRel;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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
        HashMap<String, Channel> channelHashMap = UserChannelRel.getAll();
        if (MapUtils.isNotEmpty(channelHashMap)) {
            channelHashMap.forEach((s, channel) -> {
                channel.writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(JSONResult.ok(s + "我是服务器端发送消息/n"))));
            });
            return JSONResult.ok(channelHashMap);
        }
        return JSONResult.errorException("没有通道");
    }
}
