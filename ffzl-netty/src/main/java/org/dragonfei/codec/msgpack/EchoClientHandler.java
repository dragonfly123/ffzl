package org.dragonfei.codec.msgpack;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.dragonfei.sertest.UserInfo;

/**
 * Created by longfei on 16-7-3.
 */
public class EchoClientHandler extends ChannelHandlerAdapter {
    private final int sendNumber;

    public EchoClientHandler(int sendNumber){
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserInfo[] infos = Userinfo();
        for (UserInfo infoE :infos){
            ctx.write(infoE);
        }
        ctx.flush();
    }

    public UserInfo[] Userinfo(){
        UserInfo[] userInfos = new UserInfo[sendNumber];
        UserInfo userInfo = null;
        for(int i = 0; i < sendNumber;i++){
            userInfo = new UserInfo();
            userInfo.setUserID(i);
            userInfo.setUserName("dragon---->"+i);
            userInfos[i] = userInfo;
        }
        return userInfos;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client receive the msgpack message :"+msg);
        ctx.flush();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
