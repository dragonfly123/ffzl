package org.dragonfei.codec.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.dragonfei.sertest.UserInfo;

/**
 * Created by longfei on 16-7-3.
 */
public class EchoServerHandler extends ChannelHandlerAdapter {
    int counter = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        UserInfo body = (UserInfo) msg;
        System.out.println(String.format("this is %d times recevie client :[%s]",++counter,body.toString()));
        ctx.writeAndFlush(body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
