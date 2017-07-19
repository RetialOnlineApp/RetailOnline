package com.retail.entities;

<<<<<<< HEAD
import javax.persistence.Column;
import javax.persistence.Embeddable;
=======
>>>>>>> df0e044d9b0ed58f42e059bebe34dd1d51a9787d
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
@Embeddable
=======


@Entity
>>>>>>> df0e044d9b0ed58f42e059bebe34dd1d51a9787d
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
<<<<<<< HEAD
=======
	private String city;
	private String area;
	private String pinCode;
>>>>>>> df0e044d9b0ed58f42e059bebe34dd1d51a9787d
	
	public Integer getId() {
		return id;
	}
<<<<<<< HEAD

	public void setId(Integer id) {
		this.id = id;
	}

	private String city;
	private String area;
	private String pincode;
	private UserProfile profile;

    public Address() {
		
	}

	public Address(String city, String area, String pincode, UserProfile profile) {
		this.city = city;
		this.area = area;
		this.pincode = pincode;
		this.profile = profile;
	}

	@Column(name = "City", nullable = false, length = 100)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "Area", nullable = false)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "Pincode", nullable = false)
    public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@OneToOne(mappedBy = "UserProfile")
	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}
}
=======
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
	
	
	
	
}
>>>>>>> df0e044d9b0ed58f42e059bebe34dd1d51a9787d
