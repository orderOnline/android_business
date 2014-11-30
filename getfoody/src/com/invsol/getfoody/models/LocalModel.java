package com.invsol.getfoody.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LocalModel {
	
	private StateModel[] states;
	private String[] statesNames;
	private String[] citiesNames;

	public LocalModel() {
		 
	}
	
	public void setStatesData( JSONArray statesArray ){
		states = new StateModel[statesArray.length()];
		statesNames = new String[statesArray.length()];
		JSONObject tempState = null;
		for( int i = 0; i < statesArray.length(); i++ ){
			try {
				tempState = statesArray.getJSONObject(i);
				states[i] = new StateModel(tempState.getString("ID"), tempState.getString("Name"), tempState.getString("Type"));
				statesNames[i] = tempState.getString("Name");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setCitiesData( JSONArray citiesArray ){
		citiesNames = new String[citiesArray.length()];
		JSONObject tempCity = null;
		for( int i = 0; i < citiesArray.length(); i++ ){
			try {
				tempCity = citiesArray.getJSONObject(i);
				citiesNames[i] = tempCity.getString("city");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String[] getStatesNames() {
		return statesNames;
	}

	public StateModel[] getStates() {
		return states;
	}



	public String[] getCitiesNames() {
		return citiesNames;
	}



	private class StateModel {
		private String id, name, type;

		public StateModel(String id, String name, String type) {
			super();
			this.id = id;
			this.name = name;
			this.type = type;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getType() {
			return type;
		}
		
	}
}
