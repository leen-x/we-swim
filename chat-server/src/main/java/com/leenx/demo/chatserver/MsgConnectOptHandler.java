package com.leenx.demo.chatserver;

import com.alibaba.fastjson.JSONObject;
import com.leenx.demo.chatrpc.message.Message;
import com.leenx.demo.chatrpc.message.MessageType;
import com.leenx.demo.chatrpc.message.UserMsgModel;
import com.leenx.demo.chatserver.user.UserInfo;
import com.leenx.demo.chatserver.user.UserStorage;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author linsongxiong
 * @Description:
 * @date 2021/07/16 11:27 上午
 **/
public class MsgConnectOptHandler {
    public void handle(Message<?> message, ChannelHandlerContext ctx) {
        Message<JSONObject> userInfoMessage = (Message<JSONObject>) message;
        UserInfo userInfo = JSONObject.toJavaObject(userInfoMessage.getBody(), UserInfo.class);
        if (checkUser(userInfoMessage)) {
            String userName = (String) userInfoMessage.getBody().get("name");
            ChannelStorage.addChannel(userName, ctx.channel());
//            ctx.writeAndFlush(Message.builder()
//                    .messageType(MessageType.USER_MSG.code)
//                    .from("System")
//                    .body(UserMsgModel.builder()
//                            .form("System")
//                            .to(message.getFrom())
//                            .content("👏欢迎 " + userName)
//                            .build())
//                    .build());
        } else {
            ctx.writeAndFlush(Message.builder()
                    .messageType(MessageType.USER_MSG.code)
                    .from("System")
                    .body(UserMsgModel.builder()
                            .form("System")
                            .to(message.getFrom())
                            .content("❌链接服务器异常。。。")
                            .build())
                    .build());
        }
    }

    private boolean checkUser(Message<JSONObject> message) {
        String userName = (String) message.getBody().get("name");
        String pw = (String) message.getBody().get("pw");
        UserInfo exitUserInfo = UserStorage.getUser(userName);
        if (exitUserInfo != null && exitUserInfo.getPw().equals(pw)) {
            return true;
        }
        return false;
    }
}
