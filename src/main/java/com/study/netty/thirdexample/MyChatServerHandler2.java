package com.study.netty.thirdexample;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class MyChatServerHandler2 extends MessageToByteEncoder<String> {

//    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, String s, ByteBuf byteBuf) throws Exception {
        ByteBuffer allocate = ByteBuffer.allocate(100);
        String temp = null;
        temp.substring(1);
        byteBuf.writeCharSequence(s, Charset.defaultCharset());

    }
}
