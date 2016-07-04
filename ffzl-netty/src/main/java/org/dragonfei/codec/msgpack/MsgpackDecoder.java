package org.dragonfei.codec.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.dragonfei.sertest.UserInfo;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.util.List;

/**
 * Created by longfei on 16-7-3.
 */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf>{
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        final byte[] array;
        final int length = byteBuf.readableBytes();
        array = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(),array,0,length);
        MessagePack messagePack = new MessagePack();
        list.add(messagePack.read(array, UserInfo.class));
    }
}
