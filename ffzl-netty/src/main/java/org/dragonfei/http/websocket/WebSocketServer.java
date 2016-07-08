package org.dragonfei.http.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by longfei on 16/7/8.
 */
public class WebSocketServer {
    public void run(int port) throws Exception{
        EventLoopGroup bossgroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossgroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("http-codec",new HttpServerCodec());
                            pipeline.addLast("aggregator",new HttpObjectAggregator(65536));
                            pipeline.addLast("http-chunked",new ChunkedWriteHandler());
                            pipeline.addLast(new WebSocketHandler());
                        }
                    });
            Channel ch = b.bind(port).sync().channel();
            System.out.println("Web socket server start");
            ch.closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossgroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new WebSocketServer().run(8080);
    }
}
