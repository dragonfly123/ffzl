package org.dragonfei.http.pp.deco;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;
import org.dragonfei.http.pp.pojo.NettyMessage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-7-9.
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {

    MarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder() throws IOException{
        this.marshallingEncoder = new MarshallingEncoder();
    }
    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {
        if(msg == null || msg.getHeader() == null){
            throw new Exception("the encode message is null");
        }
        ByteBuf sendBuf = Unpooled.buffer();
        sendBuf.writeInt(msg.getHeader().getCrcCode());
        sendBuf.writeInt(msg.getHeader().getLength());
        sendBuf.writeLong(msg.getHeader().getSessionID());
        sendBuf.writeByte(msg.getHeader().getType());
        sendBuf.writeByte(msg.getHeader().getPriority());
        sendBuf.writeInt(msg.getHeader().getAttachement().size());

        String key = null;
        byte[] keyArray = null;
        Object value = null;

        for(Map.Entry<String,Object> param :msg.getHeader().getAttachement().entrySet()){
            key = param.getKey();
            keyArray = key.getBytes(CharsetUtil.UTF_8);
            sendBuf.writeInt(keyArray.length);
            sendBuf.writeBytes(keyArray);
            value = param.getValue();
            marshallingEncoder.encode(value,sendBuf);
        }

        key = null;
        keyArray = null;
        value = null;

        if(msg.getBody() != null){
            marshallingEncoder.encode(msg.getBody(),sendBuf);
        } else {
            sendBuf.writeInt(0);
            sendBuf.setIndex(4,sendBuf.readableBytes());
        }
        out.add(sendBuf);
    }
}
