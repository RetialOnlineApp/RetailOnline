package com.retail.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "marchantAuth")
public class MarchantAuth {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private long uId;
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getuId() {
		return uId;
	}
	public void setuId(long uId) {
		this.uId = uId;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	private String accessToken;
    

}
