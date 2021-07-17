package com.leenx.demo.chatserver.timer;

import com.leenx.demo.chatrpc.message.Message;
import com.leenx.demo.chatserver.ChannelStorage;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.TimerTask;

/**
 * @author linsongxiong
 * @Description:
 * @date 2021/07/16 10:15 上午
 **/
public class HeartbeatTask extends TimerTask {
    @Override
    public void run() {
        Map<String, Channel> userChannelMap = ChannelStorage.getAll();
        System.out.println("HeartbeatTask " + userChannelMap.size() + "条任务");
        for (Map.Entry<String, Channel> entry : userChannelMap.entrySet()) {
            Channel channel = entry.getValue();
            channel.writeAndFlush(Message.builder().msg("HeartbeatTask").build());
            System.out.println("发送 " + entry.getValue() + " 成功");
        }
    }
}
