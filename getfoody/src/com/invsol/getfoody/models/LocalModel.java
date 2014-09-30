package com.invsol.getfoody.models;


public class LocalModel {

	private boolean retrievedMastedFilterData = false;
	private String[] locations, brands, categories;
	private Double currentlocation_latitude, currentlocation_longitude;
	private String currentCity;

	public LocalModel() {
		 
	}
	
	public boolean isRetrievedMastedFilterData() {
		return retrievedMastedFilterData;
	}

	public String[] getLocations() {
		return locations;
	}

	public String[] getBrands() {
		return brands;
	}

	public String[] getCategories() {
		return categories;
	}

	public Double getCurrentlocation_latitude() {
		return currentlocation_latitude;
	}

	public void setCurrentlocation_latitude(Double currentlocation_latitude) {
		this.currentlocation_latitude = currentlocation_latitude;
	}

	public Double getCurrentlocation_longitude() {
		return currentlocation_longitude;
	}

	public void setCurrentlocation_longitude(Double currentlocation_longitude) {
		this.currentlocation_longitude = currentlocation_longitude;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

}
