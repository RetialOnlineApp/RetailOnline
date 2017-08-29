package com.retail.merchant.entities;

import javax.persistence.*;

@Entity
public class MerchantProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer merchantId;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String email;
	private String dateOfBirth;
	private String status;
	@OneToOne(optional = false, cascade = CascadeType.ALL)
	private BussinessProfile bussinessProfile;


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BussinessProfile getBussinessProfile() {
		return bussinessProfile;
	}

	public void setBussinessProfile(BussinessProfile bussinessProfile) {
		this.bussinessProfile = bussinessProfile;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

}