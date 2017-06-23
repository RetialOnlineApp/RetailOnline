package com.retail.domains;

public class AccessTokenResponse {
	private String accessToken;
	private String email;
	private String developerMSG;
	
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
