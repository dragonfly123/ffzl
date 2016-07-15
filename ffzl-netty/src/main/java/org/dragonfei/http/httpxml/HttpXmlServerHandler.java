package org.dragonfei.http.httpxml;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http2.HttpUtil;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.dragonfei.http.httpxml.enordecode.request.HttpXmlRequest;
import org.dragonfei.http.httpxml.enordecode.response.HttpXmlResponse;
import org.dragonfei.http.httpxml.pojo.Order;
import org.dragonfei.http.httpxml.pojo.Shipping;

/**
 * Created by longfei on 16-7-8.
 */
public class HttpXmlServerHandler extends SimpleChannelInboundHandler<HttpXmlRequest> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, HttpXmlRequest xmlRequest) throws Exception {
        HttpRequest request = xmlRequest.getRequest();
        Order order = (Order) xmlRequest.getBody();
        System.out.println("Http server receive request :"+order);
        dobuiseness(order);
        ChannelFuture future = ctx.writeAndFlush(new HttpXmlResponse(null,order));
        if(!request.headers().get(HttpHeaderNames.CONNECTION).equals(HttpHeaderValues.KEEP_ALIVE)){
            future.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    ctx.close();
                }
            });
        }
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        if(ctx.channel().isActive()){

        }
    }

    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,status,
                Unpooled.copiedBuffer("失败："+status.toString()+"\r\n", CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
    private void dobuiseness(Order order){
        order.getCustomer().setFirstName("test111");
        order.getCustomer().setLastName("long");
        order.setShipping(Shipping.DOMESTIC_EXPRESS);
    }
}
