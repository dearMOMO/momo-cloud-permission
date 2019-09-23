package com.momo.common.common;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 用户id和channel的关联关系处理
 */
@Slf4j
public class UserChannelCurrentMap {

    private static Map<String, Channel> managerChannel = new ConcurrentHashMap<>();
    private static Map<String, Channel> managerUserId = new ConcurrentHashMap<>();

    public static Map<String, Channel> getAllChannel() {
        return managerChannel;
    }

    public static int managerChannelSize() {
        return managerChannel.size();
    }

    public static void putChannel(String channelLongText, Channel channel) {
        managerChannel.put(channelLongText, channel);
    }

    public static void remove(String channelLongText) {
        managerChannel.remove(channelLongText);
    }

    public static Channel getChannel(String channelLongText) {
        return managerChannel.get(channelLongText);
    }

    public static void output() {
        managerChannel.forEach((s, channel) -> {
            log.info("longText{}; ChannelId:{}", s, channel);
        });

    }
}
