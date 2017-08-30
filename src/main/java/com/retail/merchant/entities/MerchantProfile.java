package com.retail.merchant.entities;

import com.retail.oauth.entities.User;

import javax.persistence.*;

@Entity
@Table(name = "merchant_profile")
public class MerchantProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "merchant_profile_id")
	private Integer id;

	@OneToOne(targetEntity = User.class)
	private User user;

	@JoinColumn(name = "bussiness_profile_id")
	@OneToOne(fetch=FetchType.LAZY,targetEntity = BussinessProfile.class,optional = true)
	private BussinessProfile bussinessProfile;

	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String email;
	private String dateOfBirth;
	private String status;


	public Integer getUser() {
		return user.getId();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public BussinessProfile getBussinessProfile() {
		return bussinessProfile;
	}

	public void setBussinessProfile(BussinessProfile bussinessProfile) {
		this.bussinessProfile = bussinessProfile;
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


}