package com.invsol.getfoody.dataobjects;

public class CuisinesItems {
	
	private int cuisineID;
	private String cuisineName;

	public CuisinesItems(int cuisineID, String cuisineName) {
		super();
		this.cuisineID = cuisineID;
		this.cuisineName = cuisineName;
	}

	public String getCuisineName() {
		return cuisineName;
	}

	public int getCuisineID() {
		return cuisineID;
	}
	
	
}
