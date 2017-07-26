package com.retail.entities;

<<<<<<< HEAD

import javax.persistence.Column;
import javax.persistence.Embeddable;
=======
>>>>>>> 8359bf19de08db6809d1049527326c6efacd7aa2
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.Table;

@Table(name = "Address")
@Embeddable

@Entity

=======

@Entity
>>>>>>> 8359bf19de08db6809d1049527326c6efacd7aa2
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
<<<<<<< HEAD

	private String city;
	private String area;
	private String pinCode;	
	

    public Address() {
		
	}

	public Address(String city, String area, String pinCode) {
		this.city = city;
		this.area = area;
		this.pinCode = pinCode;
		
	}
=======
	private String city;
	private String area;
	private String pinCode;
>>>>>>> 8359bf19de08db6809d1049527326c6efacd7aa2
	
	public Integer getId() {
		return id;
	}
<<<<<<< HEAD

	public void setId(Integer id) {
		this.id = id;
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

	@Column(name = "pinCode", nullable = false)
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
>>>>>>> 8359bf19de08db6809d1049527326c6efacd7aa2
	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
<<<<<<< HEAD

}

=======
		
}
>>>>>>> 8359bf19de08db6809d1049527326c6efacd7aa2
