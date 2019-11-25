package com.momo.netty.service;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 用于检测channel的心跳handler
 * 继承ChannelInboundHandlerAdapter，从而不需要实现channelRead0方法
 */
@Slf4j
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {


    // 定义客户端没有收到服务端的pong消息的最大次数
    private static final int MAX_UN_REC_PING_TIMES = 3;

    // 失败计数器：未收到client端发送的ping请求
    private int unRecPingTimes = 0;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        // 判断evt是否是IdleStateEvent（用于触发用户事件，包含 读空闲/写空闲/读写空闲 ）
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;        // 强制类型转换
            String type = "";
            if (event.state() == IdleState.READER_IDLE) {
                log.info("进入读空闲...");
                type = "read idle";
            } else if (event.state() == IdleState.WRITER_IDLE) {
                log.info("进入写空闲...");
                type = "write idle";
            } else if (event.state() == IdleState.ALL_IDLE) {
                type = "all idle";
            }

            if (unRecPingTimes >= MAX_UN_REC_PING_TIMES) {
                // 连续超过N次未收到client的ping消息，那么关闭该通道，等待client重连
                ctx.channel().close();
            } else {
                // 失败计数器加1

                unRecPingTimes++;
            }
            log.debug(ctx.channel().remoteAddress() + "超时类型：" + type);
        } else {
            super.userEventTriggered(ctx, evt);
        }

    }

}
