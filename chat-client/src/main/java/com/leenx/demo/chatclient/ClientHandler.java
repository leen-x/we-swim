package com.leenx.demo.chatclient;

import com.leenx.demo.chatrpc.message.Message;
import com.leenx.demo.chatrpc.message.MessageType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.TimeUnit;

public class ClientHandler extends SimpleChannelInboundHandler<Message>{

    private ChannelHandlerContext ctx;

    private ChannelPromise promise;

    private Message response;

    private MsgUserMessageHandler msgUserMessageHandler = new MsgUserMessageHandler();


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("接受到server响应数据: " + msg.toString());
        Message<?> message = (Message<?>) msg;
        MessageType messageType = MessageType.byCode(message.getMessageType());
        msgUserMessageHandler.handle(message, ctx);
    }

    //处理服务端返回的数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message response) throws Exception {
        System.out.println("接受到server响应数据: " + response.toString());
//        this.response = response;
//        promise.setSuccess();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.ctx = ctx;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    public synchronized ChannelPromise sendMessage(Object message) {
        while (ctx == null) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
                //logger.error("等待ChannelHandlerContext实例化");
            } catch (InterruptedException e) {

            }
        }
        promise = ctx.newPromise();
        ctx.writeAndFlush(message);
        return promise;
    }

    public Message getResponse() {
        return response;
    }
}