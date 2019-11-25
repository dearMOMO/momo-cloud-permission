package com.momo.netty.service;

import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TCPServerStart
 * @Author: Jie Li
 * @Date 2019-11-24 11:34
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Component
@Order(value = 2) //执行优先级是按value值从小到大顺序。
public class TCPServerStart implements CommandLineRunner {
    @Autowired
    private TCPServer tcpServer;

    @Override
    public void run(String... args) {
        ChannelFuture channelFuture = tcpServer.bing();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> tcpServer.destroy()));
        channelFuture.channel().closeFuture().syncUninterruptibly();
    }
}
