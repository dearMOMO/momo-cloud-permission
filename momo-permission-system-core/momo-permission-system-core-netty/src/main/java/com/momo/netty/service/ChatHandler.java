package com.momo.netty.service;

import com.momo.common.util.RedisUtil;
import com.momo.common.util.SpringContextUtil;
import com.momo.netty.common.UserChannelRel;
import com.momo.netty.utils.NettyChannelRedisKey;
import com.momo.netty.utils.RandomName;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.SocketAddress;

/**
 * @Description: 处理消息的handler
 * TextWebSocketFrame： 在netty中，是用于为websocket专门处理文本的对象，frame是消息的载体
 */
@Component
@Slf4j
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    private RedisUtil redisUtil = SpringContextUtil.getBean(RedisUtil.class);


    // 用于记录和管理所有客户端的channle
    static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        // 获取客户端传输过来的消息
        String content = msg.text();
        //当前用户的 Channel
        Channel currentChannel = ctx.channel();
        //用户的唯一标志
        // 	2.1  当websocket 第一次open的时候，初始化channel，把用的channel和userid关联起来
        String senderId = "";
        UserChannelRel.put(senderId, currentChannel);
        log.info(" 获取客户端传输过来的消息content=== " + content);
        log.info(" 获取客户端传输过来的消息currentChannel=== " + currentChannel);

        if (content.trim().equals("心跳包")) {
            //心跳类型的消息
            log.info("收到来自channel为[" + currentChannel + "]的心跳包...");
            return;
        }
        //群聊
        String redisKey = NettyChannelRedisKey.channelLongTextRedisKey(currentChannel);
        String uName = (String) redisUtil.get(redisKey);
        log.info("currentChannel.id()===" + currentChannel.id());
        log.info("currentChannel.id().asLongText()===" + currentChannel.id().asLongText());
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
        for (Channel chann : users) {
            chann.writeAndFlush(new TextWebSocketFrame("[新用户] - " + uName + " 加入"));
        }
        String redisKey = NettyChannelRedisKey.channelLongTextRedisKey(channel);
        log.info("channel.id()===" + channel.id());
        log.info("channel.id().asLongText()===" + redisKey);
        log.info("通过长id:==========================={}", redisKey);
        UserChannelRel.put("channel:" + redisKey, channel);
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
        UserChannelRel.remove(redisKey);
        // 当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel
        users.remove(ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        String redisKey = NettyChannelRedisKey.channelLongTextRedisKey(incoming);
        log.info("用户:" + redisUtil.get(redisKey) + "在线");
        log.info("通过长id:==========================={}", redisKey);
        UserChannelRel.put("channel:" + redisKey, incoming);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        String redisKey = NettyChannelRedisKey.channelLongTextRedisKey(incoming);
        log.info("用户:" + redisUtil.get(redisKey) + "掉线");
        UserChannelRel.remove(redisKey);
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
        UserChannelRel.remove(redisKey);
    }
}
