package com.momo.netty.utils;

import io.netty.channel.Channel;

/**
 * Created by 李杰 on 2018/10/6.
 */
public class NettyChannelRedisKey {
    public static String channelLongTextRedisKey(Channel channel) {
        return channel.id().asLongText();
    }

    public static String channelIdRedisKey(Channel channel) {
        return String.valueOf(channel.id());
    }
}
