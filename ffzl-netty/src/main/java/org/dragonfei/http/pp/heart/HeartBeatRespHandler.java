package org.dragonfei.http.pp.heart;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.dragonfei.http.pp.MessageType;
import org.dragonfei.http.pp.pojo.Header;
import org.dragonfei.http.pp.pojo.NettyMessage;

/**
 * Created by longfei on 16-7-9.
 */
public class HeartBeatRespHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if(message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()){
            System.out.println("recive client heart beat message: ------>"+message);
            NettyMessage heartBeat = buildHeartBeat();
            System.out.println("Send heart beat response message to client :----->"+heartBeat);
            ctx.writeAndFlush(heartBeat);
        } else{
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildHeartBeat(){
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        message.setHeader(header);
        return message;
    }
}
