package com.invsol.getfoody.models;

import org.json.JSONException;
import org.json.JSONObject;

import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.exceptions.ApplicationException;


public class UserModel {

	private String accessToken, token_type, expires_in;
	
	public UserModel() {

	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getToken_type() {
		return token_type;
	}

	public String getExpires_in() {
		return expires_in;
	}
	
	public void setAuthenticationDetails(JSONObject loginData) throws ApplicationException {
		
	}
}
