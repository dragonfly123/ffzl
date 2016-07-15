package org.dragonfei.http.pp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.dragonfei.http.pp.deco.NettyMessageDecoder;
import org.dragonfei.http.pp.deco.NettyMessageEncoder;
import org.dragonfei.http.pp.handle.LoginAuthReqHandler;
import org.dragonfei.http.pp.handle.LoginAuthRespHandler;
import org.dragonfei.http.pp.heart.HeartBeatRespHandler;

/**
 * Created by longfei on 16-7-9.
 */
public class NettyServer {

    public void bind() throws Exception{
        EventLoopGroup bossgroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossgroup,workerGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,100).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new NettyMessageDecoder(1024*1024,4,4));
                ch.pipeline().addLast(new NettyMessageEncoder());
                ch.pipeline().addLast("readtimeouthandler",new ReadTimeoutHandler(50));
                ch.pipeline().addLast(new LoginAuthRespHandler());
                ch.pipeline().addLast("heartBeatHandler",new HeartBeatRespHandler());
            }
        });
        ChannelFuture future = serverBootstrap.bind(8080).sync();
        System.out.println("Netty server start ok");
        future.channel().closeFuture().sync();
    }

    public static void main(String[] args) throws Exception{
        new NettyServer().bind();
    }
}
