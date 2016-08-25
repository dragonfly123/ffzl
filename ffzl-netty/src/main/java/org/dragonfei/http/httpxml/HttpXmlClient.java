package org.dragonfei.http.httpxml;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import org.dragonfei.http.httpxml.enordecode.request.HttpXmlRequestEncoder;
import org.dragonfei.http.httpxml.enordecode.response.HttpXmlResponseDecoder;
import org.dragonfei.http.httpxml.pojo.Order;

/**
 * Created by longfei on 16-7-7.
 */
public class HttpXmlClient {
    public void connect(int port) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY,true).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast("http-decoder",new HttpResponseDecoder());
                    ch.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65536));
                    ch.pipeline().addLast("xml-decoder",new HttpXmlResponseDecoder(Order.class,true));
                    ch.pipeline().addLast("http-encoder",new HttpRequestEncoder());
                    ch.pipeline().addLast("xml-encoder",new HttpXmlRequestEncoder());
                    ch.pipeline().addLast("xmlclienthandler",new HttpXmlClientHandle());
                }
            });
            ChannelFuture f = b.connect("localhost",8080).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws Exception {
        new HttpXmlClient().connect(8080);
    }
}
