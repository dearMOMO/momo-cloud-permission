//package com.momo.netty.service;
//
//import com.momo.netty.config.NettyConfig;
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelFuture;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import java.net.InetSocketAddress;
//
///**
// * tcp服务的配置
// *
// * @version 1.0.0
// */
//@Component
//@Slf4j
//public class TCPServer {
//    @Autowired
//    @Qualifier("serverBootstrap")
//    private ServerBootstrap b;
//
//    @Autowired
//    @Qualifier("tcpSocketAddress")
//    private InetSocketAddress tcpPort;
//
//    private ChannelFuture serverChannelFuture;
//
//    @PostConstruct
//    public void start() {
//        try {
//            log.info("Starting server at " + tcpPort);
//            // 服务器异步创建绑定
//            ChannelFuture cf = serverChannelFuture = b.bind(tcpPort).sync();
//            log.info(TCPServer.class + " 启动正在监听： {}", cf.channel().localAddress());
//
//        } catch (Exception e) {
//            log.error("服务器异步创建绑定 异常 {},{}", e.getMessage(), e);
//            log.info("bossGroup");
//            NettyConfig.bossGroup().shutdownGracefully();
//            log.info("workerGroup");
//            NettyConfig.workerGroup().shutdownGracefully();
//            log.info("netty stop...");
//
//            serverChannelFuture.channel().close();
//            log.info("netty stop end");
//        }
//
//    }
//
//    @PreDestroy
//    public void stop() {
//        try {
//            log.info("serverChannelFuture.channel().close()  netty stop...");
//            serverChannelFuture.channel().close();
//        } finally {
//            log.info("NettyConfig.bossGroup().shutdownGracefully();    bossGroup");
//            NettyConfig.bossGroup().shutdownGracefully();
//            log.info("NettyConfig.workerGroup().shutdownGracefully();  workerGroup");
//            NettyConfig.workerGroup().shutdownGracefully();
//        }
//    }
//
//    public ServerBootstrap getB() {
//        return b;
//    }
//
//    public void setB(ServerBootstrap b) {
//        this.b = b;
//    }
//
//    public InetSocketAddress getTcpPort() {
//        return tcpPort;
//    }
//
//    public void setTcpPort(InetSocketAddress tcpPort) {
//        this.tcpPort = tcpPort;
//    }
//}
//
