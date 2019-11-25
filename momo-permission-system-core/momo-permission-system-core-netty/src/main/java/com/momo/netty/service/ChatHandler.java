package com.momo.netty.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.momo.common.core.common.UserChannelCurrentMap;
import com.momo.common.core.util.RedisUtil;
import com.momo.netty.utils.MOMONettyResponse;
import com.momo.netty.utils.NettyChannelRedisKey;
import com.momo.netty.utils.RandomName;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: 处理消息的handler
 * TextWebSocketFrame： 在netty中，是用于为websocket专门处理文本的对象，frame是消息的载体
 */
@Component
@Slf4j
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    //        private RedisUtil redisUtil = SpringContextUtil.getBean(RedisUtil.class);
//    @Autowired
    private RedisUtil redisUtil;

    public ChatHandler(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    // 用于记录和管理所有客户端的channle
    static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //首次连接是FullHttpRequest，处理参数
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();

            Map paramMap = getUrlParams(uri);
            log.info("接收到的参数是：{}", JSONObject.toJSONString(paramMap));
            //如果url包含参数，需要处理
            if (uri.contains("?")) {
                String newUri = uri.substring(0, uri.indexOf("?"));
                log.info("newUri:{}", newUri);
                request.setUri(newUri);
            }

        } else if (msg instanceof TextWebSocketFrame) {
            //正常的TEXT消息类型
            TextWebSocketFrame frame = (TextWebSocketFrame) msg;
            log.info("客户端收到服务器数据：{}", frame.text());
        }
        super.channelRead(ctx, msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        // 获取客户端传输过来的消息
        String content = msg.text();
        //当前用户的 Channel
        Channel currentChannel = ctx.channel();
        //用户的唯一标志
        // 	2.1  当websocket 第一次open的时候，初始化channel，把用的channel和userid关联起来
        String senderId = NettyChannelRedisKey.channelLongTextRedisKey(currentChannel);


        log.info(" 获取客户端传输过来的消息id {} content=== {}", senderId, content);
        if (content.trim().equals("心跳包")) {
            //心跳类型的消息
            log.info("senderId[ {} ]的心跳包...", senderId);
            return;
        } else if (content.trim().equals("defaultMsg")) {
            currentChannel.writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(MOMONettyResponse.momoRespnse())));
        }
        //群聊
        String redisKey = NettyChannelRedisKey.channelLongTextRedisKey(currentChannel);
        String uName = (String) redisUtil.get(redisKey);
        for (Channel channel : users) {
            if (channel != currentChannel) {
                channel.writeAndFlush(new TextWebSocketFrame("[" + uName + "]" + msg.text()));
            } else {
                channel.writeAndFlush(new TextWebSocketFrame("[you]" + msg.text()));
            }
        }

    }

    /**
     * 当客户端连接服务端之后（打开连接）
     * 获取客户端的channle，并且放到ChannelGroup中去进行管理
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        log.info("服务器 - " + channel.remoteAddress());
        String uName = new RandomName().getRandomName();
        String redisKey = NettyChannelRedisKey.channelLongTextRedisKey(channel);
        for (Channel chann : users) {
            chann.writeAndFlush(new TextWebSocketFrame("[新用户] - " + uName + " 加入"));
        }


        log.info("通过长id:{}", redisKey);
        UserChannelCurrentMap.putChannel(redisKey, channel);
        users.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        String redisKey = NettyChannelRedisKey.channelLongTextRedisKey(incoming);
        String uName = (String) redisUtil.get(redisKey);
        for (Channel channel : users) {
            channel.writeAndFlush(new TextWebSocketFrame("[用户] - " + uName + " 离开"));
        }
        String channelId = ctx.channel().id().asShortText();
        String bigChannelId = ctx.channel().id().asLongText();
        log.info("客户端被移除，短channelId为：" + channelId);
        log.info("客户端被移除，长channelId为：" + bigChannelId);
        UserChannelCurrentMap.remove(redisKey);
        // 当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel
        users.remove(ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        String redisKey = NettyChannelRedisKey.channelLongTextRedisKey(incoming);
        log.info("用户:" + redisUtil.get(redisKey) + "在线");
        log.info("通过长id:==========================={}", redisKey);
        UserChannelCurrentMap.putChannel(redisKey, incoming);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        String redisKey = NettyChannelRedisKey.channelLongTextRedisKey(incoming);
        log.info("用户:" + redisUtil.get(redisKey) + "掉线");
        UserChannelCurrentMap.remove(redisKey);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        Channel incoming = ctx.channel();
        String redisKey = NettyChannelRedisKey.channelLongTextRedisKey(incoming);
        log.error("用户:" + redisUtil.get(redisKey) + "异常");
        // 发生异常之后关闭连接（关闭channel），随后从ChannelGroup中移除
        incoming.close();
        users.remove(ctx.channel());
        UserChannelCurrentMap.remove(redisKey);
    }

    private static Map getUrlParams(String url) {
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
