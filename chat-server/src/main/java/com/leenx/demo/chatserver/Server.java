package com.leenx.demo.chatserver;

import com.leenx.demo.chatserver.timer.HeartbeatTask;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * @author linsongxiong
 * @Description:
 * @date 2021/07/13 21:23
 **/
public class Server {
    public static void main(String[] args) throws Exception {
        // timerInit();
        // 启动server服务
        new NettyServer().bind(10001);
    }

    private static void timerInit() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new HeartbeatTask(), 0, 1000 * 3);
    }
}
