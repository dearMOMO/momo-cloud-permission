//package com.momo.netty.config;
//
//import com.momo.netty.service.WSServerInitialzer;
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.codec.bytes.ByteArrayDecoder;
//import io.netty.handler.codec.bytes.ByteArrayEncoder;
//import io.netty.handler.codec.string.StringDecoder;
//import io.netty.handler.codec.string.StringEncoder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//
//import java.net.InetSocketAddress;
//
//
///**
// * @ProjectName: momo-cloud-permission
// * @Package: com.momo.netty
// * @Description: 编写NettyConfig netty的配置
// * @Author: Jie Li
// * @CreateDate: 2019/9/6 0006 17:41
// * @UpdateDate: 2019/9/6 0006 17:41
// * @Version: 1.0
// * <p>Copyright: Copyright (c) 2019</p>
// */
//@Configuration
//@Slf4j
//public class NettyConfig {
//
//    //读取yml中配置
//    //bossGroup的线程数
//    @Value("${netty.boss.thread.count}")
//    private static int bossCount;
//
//    @Value("${netty.worker.thread.count}")
//    //# worker的线程数
//    private static int workerCount;
//
//    @Value("${netty.tcp.port}")
//    //#tcp监听的端口
//    private int tcpPort;
//
//    @Value("${netty.so.keepalive}")
//    //Socket参数，连接保活，默认值为False。启用该功能时，TCP会主动探测空闲连接的有效性。可以将此功能视为TCP的心跳机制，
//    private boolean keepAlive;
//
//    @Value("${netty.so.backlog}")
//    //Socket参数，服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝
//    private int backlog;
//
//    @Autowired
//    @Qualifier("wsServerInitialzer")
//    private WSServerInitialzer wsServerInitialzer;
//
//    private static EventLoopGroup bossGroup = bossGroup();
//    private static EventLoopGroup workerGroup = workerGroup();
//
//    //bootstrap配置
//    @SuppressWarnings("unchecked")
//    @Bean(name = "serverBootstrap")
//    public ServerBootstrap bootstrap() throws InterruptedException {
//        ServerBootstrap b = new ServerBootstrap();
//
//        try {
//            b.group(bossGroup, workerGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .childHandler(wsServerInitialzer)
//                    .option(ChannelOption.SO_REUSEADDR, true)
//                    .option(ChannelOption.SO_KEEPALIVE, keepAlive)
//                    .option(ChannelOption.SO_BACKLOG, backlog)
//            ;
//            return b;
//        } catch (Exception e) {
//            log.error("netty 启动异常{};{}", e.getMessage(), e);
//            log.info("netty shutdown");
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
//        }
//        return b;
//    }
//
//    public static NioEventLoopGroup bossGroup() {
//        return new NioEventLoopGroup(bossCount);
//    }
//
//    public static NioEventLoopGroup workerGroup() {
//        return new NioEventLoopGroup(workerCount);
//    }
//
//    @Bean(name = "tcpSocketAddress")
//    public InetSocketAddress tcpPort() {
//        return new InetSocketAddress(tcpPort);
//    }
//
//    @Bean(name = "stringEncoder")
//    public StringEncoder stringEncoder() {
//        return new StringEncoder();
//    }
//
//    @Bean(name = "stringDecoder")
//    public StringDecoder stringDecoder() {
//        return new StringDecoder();
//    }
//
//    @Bean(name = "byteArrayDecoder")
//    public ByteArrayDecoder byteArrayDecoder() {
//        return new ByteArrayDecoder();
//    }
//
//    @Bean(name = "byteArrayEncoder")
//    public ByteArrayEncoder byteArrayEncoder() {
//        return new ByteArrayEncoder();
//    }
//
//    /**
//     * Necessary to make the Value annotations work.
//     *
//     * @return
//     */
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
//}
//
