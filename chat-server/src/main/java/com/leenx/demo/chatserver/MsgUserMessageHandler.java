package com.leenx.demo.chatserver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leenx.demo.chatrpc.message.Message;
import com.leenx.demo.chatrpc.message.MessageType;
import com.leenx.demo.chatrpc.message.UserMsgModel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author linsongxiong
 * @Description:
 * @date 2021/07/16 11:28 上午
 **/
public class MsgUserMessageHandler {
    private ChannelStorage channelStorage = new ChannelStorage();
    public void handle(Message<?> message, ChannelHandlerContext ctx) {
        UserMsgModel userMsgModel = JSONObject.toJavaObject((JSONObject) message.getBody(), UserMsgModel.class);
        Channel toChannel = ChannelStorage.getChannel(userMsgModel.getTo());
        if (toChannel == null) {
            ctx.writeAndFlush(Message.builder()
                    .messageType(MessageType.USER_MSG.code)
                    .msg("对方已下线")
                    .build());
        } else {
            toChannel.writeAndFlush(Message.builder()
                    .messageType(MessageType.USER_MSG.code)
                    .msg(userMsgModel.getContent())
                    .body(UserMsgModel.builder()
                            .form(userMsgModel.getForm())
                            .to(userMsgModel.getTo())
                            .content(userMsgModel.getContent())
                            .build())
                    .build());
        }
    }
}
