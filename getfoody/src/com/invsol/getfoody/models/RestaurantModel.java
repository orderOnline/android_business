package com.invsol.getfoody.models;

import android.util.Log;

public class RestaurantModel {
	private String gcm_registration_key;
	private int restaurant_id;
	private long phonenumber;
	
	public RestaurantModel(){
		
	}

	public String getGcm_registration_key() {
		return gcm_registration_key;
	}

	public void setGcm_registration_key(String gcm_registration_key) {
		Log.d("Restaurant Model", "key revd=="+gcm_registration_key);
		this.gcm_registration_key = gcm_registration_key;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public long getPhonenumber() {
		return phonenumber;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	
}
