package com.margaret.gudfud;

/**
 * Just an order. It's associated with a customer name and a string that is a list of items.
 */
public class Order {
    private String customer;
    private String order;
    private long id;

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public String getOrder() {
        return order;
    }

    public long getId() {
        return id;
    }
}
