package com.retail.merchant.entities;

import com.retail.global.entities.Product;

import javax.persistence.*;
import java.util.List;

@Entity
public class BusinessType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String type;
    @OneToMany(cascade = CascadeType.ALL)
	private List<Product> products;
	
	
	public String getType() {
		return type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
