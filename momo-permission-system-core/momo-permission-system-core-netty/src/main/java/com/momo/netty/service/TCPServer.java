package com.momo.netty.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @ClassName: TCPServerTwo
 * @Author: Jie Li
 * @Date 2019-11-24 11:25
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Component("tCPServerTwo")
@Slf4j
public class TCPServer {
    //读取yml中配置
    //bossGroup的线程数
    @Value("${netty.boss.thread.count}")
    private static int bossCount;

    @Value("${netty.worker.thread.count}")
    //# worker的线程数
    private static int workerCount;

    @Value("${netty.tcp.port}")
    //#tcp监听的端口
    private int tcpPort;

    @Value("${netty.so.keepalive}")
    //Socket参数，连接保活，默认值为False。启用该功能时，TCP会主动探测空闲连接的有效性。可以将此功能视为TCP的心跳机制，
    private boolean keepAlive;

    @Value("${netty.so.backlog}")
    //Socket参数，服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝
    private int backlog;

    //NioEventLoopGroup extends MultithreadEventLoopGroup
    // Math.max(1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));

    //配置服务端NIO线程组
    private final EventLoopGroup bossGroup = new NioEventLoopGroup(bossCount);
    private final EventLoopGroup workerGroup = new NioEventLoopGroup(workerCount);
    private Channel channel;

    ChannelFuture bing() {
        ChannelFuture channelFuture = null;
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)    //非阻塞模式
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.SO_KEEPALIVE, keepAlive)
                    .option(ChannelOption.SO_BACKLOG, backlog)
                    .childHandler(new WSServerInitialzer());

            channelFuture = b.bind(new InetSocketAddress(tcpPort)).syncUninterruptibly();
            channel = channelFuture.channel();
        } catch (Exception e) {
            log.error("netty start error {}  {}", e.getMessage(), e);
        } finally {
            if (null != channelFuture && channelFuture.isSuccess()) {
                log.info("tCPServerTwo start ok");
            } else {
                log.error("tCPServerTwo start error ");
            }
        }
        return channelFuture;
    }

    void destroy() {
        if (null == channel) {
            return;
        }
        channel.close();
        log.info("channel.close();  netty stop.......");
        bossGroup.shutdownGracefully();
        log.info("bossGroup.shutdownGracefully()");
        workerGroup.shutdownGracefully();
        log.info("workerGroup.shutdownGracefully()");
    }

    public Channel getChannel() {
        return channel;
    }
}
