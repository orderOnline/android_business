package com.invsol.getfoody.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.SparseArray;

import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.dataobjects.CategoryItem;
import com.invsol.getfoody.dataobjects.CuisinesItems;
import com.invsol.getfoody.dataobjects.MenuItem;


public class LocalModel {
	
	private StateModel[] states;
	private String[] statesNames;
	private String[] citiesNames;
	private CuisinesItems[] cuisines;
	private String[] cuisineNames;
	private SparseArray<CategoryItem> categories;
	private HashMap<String, ArrayList<MenuItem>> menuItems;
	private boolean isCuisinesDataReceived;

	public LocalModel() {
		 categories = new SparseArray<CategoryItem>();
		 menuItems = new HashMap<String, ArrayList<MenuItem>>();
		 isCuisinesDataReceived = false;
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
	
	public void addCategory(JSONObject categoryData ){
		CategoryItem item=null;
		try {
			item = new CategoryItem(categoryData.getInt(Constants.JSON_CATEGORYID), categoryData.getString(Constants.JSON_CATEGORYNAME));
			categories.put(categoryData.getInt(Constants.JSON_CATEGORYID), item);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addMenuItem( JSONObject menuitemData ){
		MenuItem item = null;
		try {
			item = new MenuItem(menuitemData.getInt(Constants.JSON_ITEMID), menuitemData.getInt(Constants.JSON_ITEMPRICE), 
					menuitemData.getInt(Constants.JSON_CATEGORYID), menuitemData.getString(Constants.JSON_ITEMNAME), menuitemData.getString(Constants.JSON_ITEMTYPE));
			String catName = categories.get(menuitemData.getInt(Constants.JSON_CATEGORYID)).getCategory_name();
			if(menuItems.containsKey(catName))
			{
				ArrayList<MenuItem>tempItemArray = (ArrayList<MenuItem>)menuItems.get(catName);
				tempItemArray.add(item);
			}else{
				ArrayList<MenuItem> itemArray = new ArrayList<MenuItem>();
				itemArray.add(item);
				menuItems.put(catName, itemArray);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String[] getStatesNames() {
		return statesNames;
	}
	
	public void setCuisinesData( JSONArray cuisinesArray ){
		cuisines = new CuisinesItems[cuisinesArray.length()];
		cuisineNames = new String[cuisinesArray.length()];
		JSONObject tempCuisine = null;
		for( int i = 0; i < cuisinesArray.length(); i++ ){
			try {
				tempCuisine = cuisinesArray.getJSONObject(i);
				cuisines[i] = new CuisinesItems(tempCuisine.getInt("cuisine_id"), tempCuisine.getString("cuisine_name"));
				cuisineNames[i] = tempCuisine.getString("cuisine_name");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		isCuisinesDataReceived = true;
	}

	public StateModel[] getStates() {
		return states;
	}



	public String[] getCitiesNames() {
		return citiesNames;
	}

	public String[] getCuisineNames() {
		return cuisineNames;
	}



	public CuisinesItems[] getCuisines() {
		return cuisines;
	}

	



	public boolean isCuisinesDataReceived() {
		return isCuisinesDataReceived;
	}

	public SparseArray<CategoryItem> getCategories() {
		return categories;
	}

	public HashMap<String, ArrayList<MenuItem>> getMenuItems() {
		return menuItems;
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
