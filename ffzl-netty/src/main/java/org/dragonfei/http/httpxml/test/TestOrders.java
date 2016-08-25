package org.dragonfei.http.httpxml.test;

import com.thoughtworks.xstream.XStream;
import org.dragonfei.http.httpxml.pojo.Customer;
import org.dragonfei.http.httpxml.pojo.Order;

/**
 * Created by longfei on 16-7-7.
 */
public class TestOrders {
    public static void main(String[] args) {
        Order order = new Order();
        order.setOrderNumber(1232);
        Customer customer = new Customer();
        customer.setFirstName("test");
        order.setCustomer(customer);

        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        String xml = xStream.toXML(order);
        System.out.println(xml);

        Order order1 = (Order) xStream.fromXML(xml);
        System.out.println(order1);
    }
}
