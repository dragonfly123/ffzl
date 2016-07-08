package org.dragonfei.http.httpxml.enordecode;

import com.thoughtworks.xstream.XStream;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * Created by longfei on 16-7-7.
 */
public abstract class  AbstractHttpXmlEncoder<T> extends MessageToMessageEncoder<T> {

    XStream xStream;
    protected ByteBuf encode0(ChannelHandlerContext ctx,Object body){
        xStream = new XStream();
        String xml = xStream.toXML(body);
        ByteBuf encodeBuf = Unpooled.copiedBuffer(xml, CharsetUtil.UTF_8);
        return encodeBuf;
    }

}
