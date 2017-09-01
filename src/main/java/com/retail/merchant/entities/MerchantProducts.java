package com.retail.merchant.entities;


import javax.persistence.*;
import java.util.List;

@Entity
public class MerchantProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer userId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductBucket> productBuckets;

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

    public List<ProductBucket> getProductBuckets() {
        return productBuckets;
    }

    public void setProductBuckets(List<ProductBucket> productBuckets) {
        this.productBuckets = productBuckets;
    }
}
