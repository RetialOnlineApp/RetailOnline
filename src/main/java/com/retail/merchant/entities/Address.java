package com.retail.merchant.entities;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id")
	private Integer id;

    @OneToOne(targetEntity =BussinessProfile.class)
    private BussinessProfile bussinessProfile;

    private String city;
	private String area;
	private String pinCode;
	private String landMark;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getLandMark() {
		return landMark;
	}
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}


    public BussinessProfile getBussinessProfile() {
        return bussinessProfile;
    }

    public void setBussinessProfile(BussinessProfile bussinessProfile) {
        this.bussinessProfile = bussinessProfile;
    }
		
}
