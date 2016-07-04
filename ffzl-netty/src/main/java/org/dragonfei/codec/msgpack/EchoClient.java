package org.dragonfei.codec.msgpack;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by longfei on 16-7-3.
 */
public class EchoClient {

    public static void main(String[] args) throws Exception{
        int port = 8080;
        new EchoClient().connect(port,"127.0.0.1");
    }
    public void connect(int port,String host) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY,true).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast("frameDecoder",new LengthFieldBasedFrameDecoder(65535,0,2,0,2));
                    socketChannel.pipeline().addLast("msgpack decoder",new MsgpackDecoder());
                    socketChannel.pipeline().addLast("frameEbcoder",new LengthFieldPrepender(2));
                    socketChannel.pipeline().addLast("msgpack encoder",new MsgpackEncoder());
                    socketChannel.pipeline().addLast(new EchoClientHandler(100));
                }
            });
            ChannelFuture f = b.connect(host,port).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
