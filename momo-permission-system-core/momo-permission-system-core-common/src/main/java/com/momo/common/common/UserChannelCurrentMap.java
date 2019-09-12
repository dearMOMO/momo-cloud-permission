package com.momo.common.common;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 用户id和channel的关联关系处理
 */
public class UserChannelCurrentMap {

    private static Map<String, Channel> manager = new ConcurrentHashMap<>();

    public static Map<String, Channel> getAll() {
        return manager;
    }

    public static void put(String senderId, Channel channel) {
        manager.put(senderId, channel);
    }

    public static void remove(String senderId) {
        manager.remove("channel:" + senderId);
    }

    public static Channel get(String senderId) {
        return manager.get(senderId);
    }

    public static void output() {
        for (HashMap.Entry<String, Channel> entry : manager.entrySet()) {
            System.out.println("UserId: " + entry.getKey()
                    + ", ChannelId: " + entry.getValue().id().asLongText());
        }
    }
}
