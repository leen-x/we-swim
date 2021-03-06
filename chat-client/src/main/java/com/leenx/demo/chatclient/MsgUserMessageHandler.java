package com.leenx.demo.chatclient;

import com.alibaba.fastjson.JSONObject;
import com.leenx.demo.chatrpc.message.Message;
import com.leenx.demo.chatrpc.message.MessageType;
import com.leenx.demo.chatrpc.message.UserMsgModel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.text.MessageFormat;

/**
 * @author linsongxiong
 * @Description:
 * @date 2021/07/16 11:28 δΈε
 **/
public class MsgUserMessageHandler {
    private static final String terminalOutput = "γ{0}γ:{1}";
    public void handle(Message<?> message, ChannelHandlerContext ctx) {
        UserMsgModel userMsgModel = JSONObject.toJavaObject((JSONObject) message.getBody(), UserMsgModel.class);
        String print = MessageFormat.format(terminalOutput, userMsgModel.getForm(), userMsgModel.getContent());
        System.out.println(print);
    }
}
