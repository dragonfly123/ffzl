package org.dragonfei.http.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Text;

/**
 * Created by longfei on 16/7/8.
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    private WebSocketServerHandshaker handshaker;
    @Override
    protected void messageReceived(ChannelHandlerContext ctx,Object msg) throws Exception {
        if(msg instanceof FullHttpRequest){
            handleHttpRequest(ctx,(FullHttpRequest) msg);
        }
        else if(msg instanceof WebSocketFrame){
            handleWebSocketFrame(ctx,(WebSocketFrame)msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private void handleHttpRequest(ChannelHandlerContext ctx,FullHttpRequest request)
            throws Exception{
        if(!request.decoderResult().isSuccess() || (!"websocket".equals(request.headers().get("Upgrade")))){
            sendHttpResponse(ctx,request,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.BAD_REQUEST));
            return;
        }

        WebSocketServerHandshakerFactory wsfactory = new WebSocketServerHandshakerFactory("ws://localhost:8080/websocket",null,false);
        handshaker = wsfactory.newHandshaker(request);
        if(handshaker == null){
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(),request);
        }
    }

    private static  void sendHttpResponse(ChannelHandlerContext ctx,FullHttpRequest request,FullHttpResponse response){
        if(response.status().code() != 200){
            ByteBuf buf = Unpooled.copiedBuffer(response.status().toString(),CharsetUtil.UTF_8);
            response.content().writeBytes(buf);
            buf.release();
            response.headers().setLong(HttpHeaderNames.CONTENT_LENGTH,response.content().readableBytes());

        }
        ChannelFuture f = ctx.channel().writeAndFlush(response);
        if(request.headers().get(HttpHeaderNames.CONNECTION).equals(HttpHeaderValues.KEEP_ALIVE)
                || response.status().code() != 200){
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx,WebSocketFrame frame){

        //关闭链路
        if(frame instanceof CloseWebSocketFrame){
            handshaker.close(ctx.channel(),(CloseWebSocketFrame) frame.retain());
            return;
        }

        //ping
        if(frame instanceof PingWebSocketFrame){
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        if(!(frame instanceof TextWebSocketFrame)){
            throw new UnsupportedOperationException(String.format("%s frame type not supperted",frame.getClass().getName()));
        }

        String request = ((TextWebSocketFrame)frame).text();
        ctx.channel().write(new TextWebSocketFrame(request+",欢迎使用netty WebSocket 服务,现在时刻:"+new java.util.Date().toString()));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
