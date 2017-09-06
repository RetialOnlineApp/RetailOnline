package com.retail.merchant.entities;


import javax.persistence.*;
import java.util.List;

public class MerchantOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer userId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrdersBucket> ordersBuckets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<OrdersBucket> getOrdersBuckets() {
        return ordersBuckets;
    }

    public void setOrdersBuckets(List<OrdersBucket> ordersBuckets) {
        this.ordersBuckets = ordersBuckets;
    }
}
