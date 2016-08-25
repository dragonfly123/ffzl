package org.dragonfei.http.pp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.local.LocalAddress;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.dragonfei.http.pp.deco.NettyMessageDecoder;
import org.dragonfei.http.pp.deco.NettyMessageEncoder;
import org.dragonfei.http.pp.handle.LoginAuthReqHandler;
import org.dragonfei.http.pp.heart.HeartBeatReqHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by longfei on 16-7-9.
 */
public class NettyClient {
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    EventLoopGroup group = new NioEventLoopGroup();
    public void connect(int port,String host) throws Exception {
        try{
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY,true).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new NettyMessageDecoder(1024*1024,4,4));
                    ch.pipeline().addLast("messageEncoder",new NettyMessageEncoder());
                    ch.pipeline().addLast("readTimeoutHandler",new ReadTimeoutHandler(50));
                    ch.pipeline().addLast("loginAuthHandler",new LoginAuthReqHandler());
                    ch.pipeline().addLast("heartbeatHandler",new HeartBeatReqHandler());
                }
            });

            ChannelFuture future = b.connect(new InetSocketAddress(host,port)).sync();
            future.channel().closeFuture().sync();
        } finally {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        TimeUnit.SECONDS.sleep(5);
                        connect(port,host);
                    } catch (Exception e){

                    }
                }
            });
        }
    }

    public static void main(String[] args) throws Exception{
        new NettyClient().connect(8080,"127.0.0.1");
    }

}
