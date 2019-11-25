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
package com.momo.netty.utils;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: ChannelManager
 * @Author: Jie Li
 * @Date 2019-11-25 09:27
 * @Description: netty channel 管理
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
public class ChannelManager {
    private final static Map<String, Channel> channelCache = new ConcurrentHashMap<>();

    public static void put(String key, Channel value) {
        channelCache.put(key, value);
    }

    public static Channel get(String key) {
        return channelCache.get(key);
    }

    public static void remove(String key) {
        channelCache.remove(key);
    }

    public static int size() {
        return channelCache.size();
    }


    public static void ctxWrite(String key, Object obj) {
        Channel channel = get(key);
        channel.writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(obj)));
    }

    public static void ctxWrite(ChannelHandlerContext ctx, Object obj) {
        Channel channel = ctx.channel();
        channel.writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(obj)));
    }

    public static String channelLongText(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        return channel.id().asLongText();
    }
}
