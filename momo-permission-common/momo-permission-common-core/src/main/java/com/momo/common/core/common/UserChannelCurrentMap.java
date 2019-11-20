package com.momo.common.core.common;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: UserChannelCurrentMap
 * @Author: Jie Li
 * @Date 2019-11-20 14:39
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
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
