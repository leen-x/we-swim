package com.leenx.demo.chatrpc.handler;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author linsongxiong
 * @Description:
 * @date 2021/07/13 8:31 下午
 **/
public class MsgEncode extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf buf)
            throws Exception {
        byte[] write = JSON.toJSONBytes(msg);
        buf.writeBytes(write);
    }
}
