package org.dragonfei.http.httpxml;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.dragonfei.http.httpxml.enordecode.request.HttpXmlRequest;
import org.dragonfei.http.httpxml.enordecode.response.HttpXmlResponse;
import org.dragonfei.http.httpxml.pojo.Customer;
import org.dragonfei.http.httpxml.pojo.Order;

/**
 * Created by longfei on 16-7-7.
 */
public class HttpXmlClientHandle extends SimpleChannelInboundHandler<HttpXmlResponse> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, HttpXmlResponse msg) throws Exception {
        System.out.println("Then client receive response of http header is :"+msg.getHttpResponse().headers().names());
        System.out.println("Then client receive response of http body is :"+msg.getResult());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Order order = new Order();
        order.setOrderNumber(1232);
        Customer customer = new Customer();
        customer.setFirstName("test");
        order.setCustomer(customer);
        HttpXmlRequest request = new HttpXmlRequest(null,order);
        ctx.writeAndFlush(request);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
