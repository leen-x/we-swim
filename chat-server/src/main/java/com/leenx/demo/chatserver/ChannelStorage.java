package com.leenx.demo.chatserver;

import io.netty.channel.Channel;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linsongxiong
 * @Description: 用户链接保持器
 * @date 2021/07/15 7:34 下午
 **/
public class ChannelStorage {
    static Map<String, Channel> userChannelMap = new HashMap<>();

    public static void addChannel(String userId, Channel channel) {
        userChannelMap.put(userId, channel);
    }

    public static Channel getChannel(String userId) {
        return userChannelMap.get(userId);
    }

    public static void removeChannel(String userId) {
        Channel channel = userChannelMap.get(userId);
        if (channel != null) {
            channel.disconnect();
        }
    }

    public static Map<String, Channel> getAll() {
        return userChannelMap;
    }
}
