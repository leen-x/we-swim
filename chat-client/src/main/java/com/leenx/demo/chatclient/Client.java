package com.leenx.demo.chatclient;

import com.alibaba.fastjson.JSON;
import com.leenx.demo.chatrpc.message.Message;
import com.leenx.demo.chatrpc.message.MessageType;
import com.leenx.demo.chatrpc.message.UserMsgModel;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author linsongxiong
 * @Description:
 * @date 2021/07/13 21:23
 **/
public class Client {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🎉欢迎使用we-swim (quit 回车 退出)");
        System.out.println("请输入用户名:");
        String name = scanner.nextLine();
        System.out.println("请输入密码:");
        String password = scanner.nextLine();
        //  123.60.21.129
        NettyClient client = new NettyClient("123.60.21.129", 10001);
//        NettyClient client = new NettyClient("127.0.0.1", 10001);
        //启动client服务
        client.start();
        Channel channel = client.getChannel();
        Message loginMsg = Message.builder()
                .messageType(MessageType.CONNECT_OPT.code)
                .body(UserInfo.builder()
                        .name(name)
                        .pw(password)
                        .build())
                .build();
        channel.writeAndFlush(loginMsg);
        System.out.println("请输入要聊天的对象:");
        String to = scanner.nextLine();
        Message<UserMsgModel> userMsg = Message.<UserMsgModel>builder()
                .from(name)
                .messageType(MessageType.USER_MSG.code)
                .build();
        UserMsgModel userMsgModel = UserMsgModel.builder()
                .form(name)
                .to(to)
                .build();
        System.out.println("开始聊天吧～～");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("quit")) {
                client.close();
                System.out.println("👋");
            } else {
                userMsgModel.setContent(input);
                userMsg.setBody(userMsgModel);
                channel.writeAndFlush(userMsg);
            }
        }
    }
}
