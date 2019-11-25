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
package com.momo.netty.service;

import com.google.common.collect.Maps;
import com.momo.netty.utils.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: 处理消息的handler
 * TextWebSocketFrame： 在netty中，是用于为websocket专门处理文本的对象，frame是消息的载体
 */
@Slf4j
@Component(value = "chatHandler")
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    private NettyHandlerService nettyHandlerService;

    public ChatHandler(NettyHandlerService nettyHandlerService) {
        this.nettyHandlerService = nettyHandlerService;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //首次连接是FullHttpRequest，处理参数
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();

            Map paramMap = getUrlParams(uri);
            //如果url包含参数，需要处理
            if (uri.contains("?")) {
                String newUri = uri.substring(0, uri.indexOf("?"));
                request.setUri(newUri);
            }
            Object obj = paramMap.get("token");
            if (null == obj) {
                ctx.channel().close();
                return;
            }
            ChannelManager.put(ChannelManager.channelLongText(ctx), ctx.channel());
        } else if (msg instanceof TextWebSocketFrame) {
            //正常的TEXT消息类型
//            TextWebSocketFrame frame = (TextWebSocketFrame) msg;
        }
        super.channelRead(ctx, msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        nettyHandlerService.refreshTken(ctx, msg.text());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().close();
        ChannelManager.remove(ChannelManager.channelLongText(ctx));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelManager.put(ChannelManager.channelLongText(ctx), ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("netty channel error {} {}", cause.getMessage(), cause);
        // 发生异常之后关闭连接（关闭channel），随后从ChannelGroup中移除
        ChannelManager.remove(ChannelManager.channelLongText(ctx));
        ctx.channel().close();

    }

    private static Map<String, String> getUrlParams(String url) {
        Map<String, String> map = Maps.newHashMap();
        url = url.replace("?", ";");
        if (!url.contains(";")) {
            return map;
        }
        if (url.split(";").length > 0) {
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr) {
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key, value);
            }
            return map;

        } else {
            return map;
        }
    }
}
