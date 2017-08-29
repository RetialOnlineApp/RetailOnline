package com.retail.merchant.domains;

public class AccessTokenResponse {
	private String accessToken;
	private String email;
	private String developerMSG;
	private Integer id;
	private String role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDeveloperMSG() {
		return developerMSG;
	}

	public void setDeveloperMSG(String developerMSG) {
		this.developerMSG = developerMSG;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
