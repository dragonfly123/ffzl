package org.dragonfei.http.httpxml.enordecode;

import com.thoughtworks.xstream.XStream;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.Charset;

/**
 * Created by longfei on 16-7-7.
 */
public abstract class AbstractHttpXmlDecoder<T> extends MessageToMessageDecoder<T> {

    private Class<?> clazz;
    private boolean isPrint;

    private final static String CHARSET_NAME = "UTF-8";
    private final static Charset UTF_8 = Charset.forName(CHARSET_NAME);

    public AbstractHttpXmlDecoder( Class<?> clazz, boolean isPrint) {
        this.clazz = clazz;
        this.isPrint = isPrint;
    }
    public AbstractHttpXmlDecoder( Class<?> clazz) {
        this(clazz,false);
    }

    protected Object decode0(ChannelHandlerContext ctx, ByteBuf body){
        XStream xStream = new XStream();
        String content = body.toString(UTF_8);
        if(isPrint){
            System.out.println("the body is :" +content);
        }
        return xStream.fromXML(content);
    }
}
