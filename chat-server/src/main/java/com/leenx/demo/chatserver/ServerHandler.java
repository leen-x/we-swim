package com.leenx.demo.chatserver;

import com.alibaba.fastjson.JSONObject;
import com.leenx.demo.chatrpc.message.Message;
import com.leenx.demo.chatrpc.message.MessageType;
import com.leenx.demo.chatserver.user.UserInfo;
import com.leenx.demo.chatserver.user.UserStorage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    private MsgConnectOptHandler connectOptHandler = new MsgConnectOptHandler();
    private MsgUserMessageHandler userMessageHandler = new MsgUserMessageHandler();

    //接受client发送的消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message<?> request = (Message) msg;
//        System.out.println("接收到客户端信息:" + request.toString());
        //返回的数据结构
        MessageType type = MessageType.byCode(request.getMessageType());
        switch (type) {
            case CONNECT_OPT: {
                connectOptHandler.handle(request, ctx);
                break;
            }
            case USER_MSG: {
                userMessageHandler.handle(request, ctx);
            }
        }
    }

    //通知处理器最后的channelRead()是当前批处理中的最后一条消息时调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("服务端接收数据完毕..");
        ctx.flush();
    }

    //读操作时捕获到异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    //客户端去和服务端连接成功时触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //  ctx.writeAndFlush("hello client");
    }


}