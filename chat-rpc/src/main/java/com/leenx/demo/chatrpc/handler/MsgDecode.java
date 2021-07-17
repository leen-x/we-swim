package com.leenx.demo.chatrpc.handler;

import com.alibaba.fastjson.JSON;
import com.leenx.demo.chatrpc.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @author linsongxiong
 * @Description:
 * @date 2021/07/13 8:32 下午
 **/
public class MsgDecode extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg,
                          List<Object> out) throws Exception {
        final  byte[] array;
        final int length = msg.readableBytes();
        array = new byte[length];
        msg.getBytes(msg.readerIndex(), array, 0, length);
        Message message = JSON.parseObject(array, Message.class);
        out.add(message);
    }
}
