package org.dragonfei.http.pp.handle;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.dragonfei.http.pp.MessageType;
import org.dragonfei.http.pp.pojo.Header;
import org.dragonfei.http.pp.pojo.NettyMessage;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by longfei on 16-7-9.
 */
public class LoginAuthRespHandler extends ChannelHandlerAdapter {

    private Map<String,Boolean> nodeCheck = new ConcurrentHashMap<>();

    private String[] whiteklist = {"127.0.0.1","192.168.1.102"};

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        NettyMessage message = (NettyMessage) msg;
        if(message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_REQ.value()){
            String nodeIndex = ctx.channel().remoteAddress().toString();
            NettyMessage loginResp = null;
            if(nodeCheck.containsKey(nodeIndex)){
                loginResp = buildResponse((byte)-1);
            } else {
                InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
                String ip = address.getAddress().getHostAddress();
                boolean isOk = false;
                for(String WIP :whiteklist){
                    if(WIP.equals(ip)){
                        isOk = true;
                        break;
                    }
                }
                loginResp = isOk?buildResponse((byte) 0):buildResponse((byte)-1);
                if(isOk){
                    nodeCheck.put(nodeIndex,true);
                }
            }
            System.out.println("the login response is :"+loginResp+"body["+loginResp.getBody()+"]");
            ctx.writeAndFlush(loginResp);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildResponse(byte result){
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_RESP.value());
        message.setHeader(header);
        message.setBody(result);
        return message;
    }
}
