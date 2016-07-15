package org.dragonfei.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.io.IOException;

/**
 * Created by longfei on 16-7-4.
 */
public class HttpFileServer {
    private static final String DEFAULT_URL = "/src/main/java/dragonfei/";

    public void run(final int port,final String url) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast("http-decoder",new HttpRequestDecoder());
                    ch.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65536));
                    ch.pipeline().addLast("http-encoder",new HttpResponseEncoder());
                    ch.pipeline().addLast("fileServerHandler",new HttpFileServerHandler());
                }
            });
            ChannelFuture future = b.bind(port).sync();
            System.out.println(String.format("htttp文件服务器启动，网址是：%s","http://localhost:"+port+url));
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        String url = DEFAULT_URL;
        if(args.length >1){
            url = args[1];
        }

        new HttpFileServer().run(port,url);
    }
}
