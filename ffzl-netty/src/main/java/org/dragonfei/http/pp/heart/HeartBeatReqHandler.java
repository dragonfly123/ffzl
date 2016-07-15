package org.dragonfei.http.pp.heart;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.ScheduledFuture;
import org.dragonfei.http.pp.MessageType;
import org.dragonfei.http.pp.pojo.Header;
import org.dragonfei.http.pp.pojo.NettyMessage;

import java.util.concurrent.TimeUnit;

/**
 * Created by longfei on 16-7-9.
 */
public class HeartBeatReqHandler extends ChannelHandlerAdapter {
    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if(message.getHeader()!= null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()){
            heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatReqHandler.HeartBeatTask(ctx),0,5000, TimeUnit.MILLISECONDS);
        } else if(message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_RESP.value()){
            System.out.println("client receive server heart beat message :--->"+message);
        } else{
            ctx.fireChannelRead(msg);
        }
    }

    private class HeartBeatTask implements Runnable{
        private final ChannelHandlerContext ctx;
        public HeartBeatTask(final ChannelHandlerContext ctx){
            this.ctx = ctx;
        }

        @Override
        public void run() {
            NettyMessage heatBeat = buildHeartBeat();
            System.out.println("client and heart beat message to server:----->"+heatBeat);
            ctx.writeAndFlush(heatBeat);
        }

        private NettyMessage buildHeartBeat(){
            NettyMessage message = new NettyMessage();
            Header header = new Header();
            header.setType(MessageType.HEARTBEAT_REQ.value());
            message.setHeader(header);
            return message;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if(heartBeat != null){
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }
}
