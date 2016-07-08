package org.dragonfei.http.httpxml.enordecode.response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;
import org.dragonfei.http.httpxml.enordecode.AbstractHttpXmlDecoder;

import java.util.List;

/**
 * Created by longfei on 16-7-7.
 */
public class HttpXmlResponseDecoder extends AbstractHttpXmlDecoder<FullHttpResponse> {
    @Override
    protected void decode(ChannelHandlerContext ctx, FullHttpResponse msg, List<Object> out) throws Exception {
        HttpXmlResponse response = new HttpXmlResponse(msg,decode0(ctx,msg.content()));
        out.add(response);
    }

    public HttpXmlResponseDecoder(Class<?> clazz){
        this(clazz,false);
    }

    public HttpXmlResponseDecoder(Class<?> clazz,boolean isPrinting)
    {
        super(clazz,isPrinting);
    }
}
