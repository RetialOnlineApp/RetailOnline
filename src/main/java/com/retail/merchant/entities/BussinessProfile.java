package com.retail.merchant.entities;

import javax.persistence.*;

@Entity
public class BussinessProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	private String shopName;

	private String serviceType;


	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getBusinessType() {
		return serviceType;
	}

	public void setBusinessType(String serviceType) {
		this.serviceType = serviceType;
	}

	
    

}
