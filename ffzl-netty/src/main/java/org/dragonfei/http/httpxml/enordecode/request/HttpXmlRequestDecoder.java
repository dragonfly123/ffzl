package org.dragonfei.http.httpxml.enordecode.request;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.dragonfei.http.httpxml.enordecode.AbstractHttpXmlDecoder;

import java.util.List;

/**
 * Created by longfei on 16-7-7.
 */
public class HttpXmlRequestDecoder extends AbstractHttpXmlDecoder<FullHttpRequest> {

    public HttpXmlRequestDecoder(Class<?> clazz){
        this(clazz,false);
    }

    public HttpXmlRequestDecoder(Class<?> clazz,boolean isPrint){
        super(clazz,isPrint);
    }
    @Override
    protected void decode(ChannelHandlerContext ctx, FullHttpRequest request, List<Object> out) throws Exception {
        if(!request.decoderResult().isSuccess()){
            sendError(ctx,HttpResponseStatus.BAD_REQUEST);
            return;
        }
        HttpXmlRequest request1 = new HttpXmlRequest(request,decode0(ctx,request.content()));
        out.add(request1);
    }

    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus stasus){
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,stasus,
                Unpooled.copiedBuffer("Failure:"+stasus.toString()+"\r\n", CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
