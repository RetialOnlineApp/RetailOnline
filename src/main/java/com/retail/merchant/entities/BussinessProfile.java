package com.retail.merchant.entities;

import javax.persistence.*;

@Entity
@Table(name = "bussiness_profile")
public class BussinessProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bussiness_profile_id")
	private Integer id;


	@OneToOne(targetEntity = Address.class, fetch=FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToOne(targetEntity = MerchantProfile.class)
	private MerchantProfile merchantProfile;
	
	private String shopName;

	private String serviceType;

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}


	public Integer getMerchantProfile() {
		return merchantProfile.getId();
	}

	public void setMerchantProfile(MerchantProfile merchantProfile) {
		this.merchantProfile = merchantProfile;
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
