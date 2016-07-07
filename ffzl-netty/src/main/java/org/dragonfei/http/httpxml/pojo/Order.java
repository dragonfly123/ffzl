package org.dragonfei.http.httpxml.pojo;

/**
 * Created by longfei on 16-7-6.
 */
public class Order {
    private long orderNumber;
    private Customer customer;

    private Address address;

    private Shipping shipping;

    private Address shipT0;

    private Float total;

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public Address getShipT0() {
        return shipT0;
    }

    public void setShipT0(Address shipT0) {
        this.shipT0 = shipT0;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
