package org.dragonfei.http.httpxml;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import org.dragonfei.http.httpxml.enordecode.request.HttpXmlRequestDecoder;
import org.dragonfei.http.httpxml.enordecode.response.HttpXmlResponseEncoder;
import org.dragonfei.http.httpxml.pojo.Order;

/**
 * Created by longfei on 16-7-8.
 */
public class HttpXmlServer {
    public void run(final int port) throws Exception{
        EventLoopGroup bossgroup = new NioEventLoopGroup();
        EventLoopGroup workgroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossgroup,workgroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast("http-decoder",new HttpRequestDecoder());
                    ch.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65536));
                    //ch.pipeline().addLast("xml-decoder",new HttpXmlRequestDecoder(Order.class,true));
                    ch.pipeline().addLast("http-encoder",new HttpResponseEncoder());
                    //ch.pipeline().addLast("xml-encoder",new HttpXmlResponseEncoder());
                    ch.pipeline().addLast("xmlclienthandler",new HttpXmlServerHandler());
                }
            });
            ChannelFuture future = b.bind(port).sync();
            System.out.println("Http 服务启动");
            future.channel().closeFuture().sync();
        } finally {
            workgroup.shutdownGracefully();
            bossgroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws Exception {
        int port = 8080;
        new HttpXmlServer().run(port);
    }
}
