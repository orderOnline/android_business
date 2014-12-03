package com.invsol.getfoody.models;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.dataobjects.CategoryItem;
import com.invsol.getfoody.dataobjects.CuisinesItems;
import com.invsol.getfoody.dataobjects.MenuItem;

import android.util.Log;
import android.util.SparseArray;

public class RestaurantModel {
	private String gcm_registration_key;
	private int restaurant_id, zipcode;
	private long phonenumber;
	private String name, email, address, city, state, service_starttime, service_endtime;
	private CuisinesItems[] cuisines;
	private SparseArray<CategoryItem> categories;
	private HashMap<String, ArrayList<MenuItem>> menuItems;
	
	public RestaurantModel(){
		categories = new SparseArray<CategoryItem>();
		 menuItems = new HashMap<String, ArrayList<MenuItem>>();
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
	
	public int getZipcode() {
		return zipcode;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getService_starttime() {
		return service_starttime;
	}

	public String getService_endtime() {
		return service_endtime;
	}

	public CuisinesItems[] getCuisines() {
		return cuisines;
	}

	public SparseArray<CategoryItem> getCategories() {
		return categories;
	}

	public HashMap<String, ArrayList<MenuItem>> getMenuItems() {
		return menuItems;
	}

	public void setRestaurantProfileDetails(JSONObject profileDetails ){
		try {
			restaurant_id = profileDetails.getInt(Constants.JSON_RESTAURANT_ID);
			phonenumber = profileDetails.getLong(Constants.JSON_PHONENUMBER);
			name = profileDetails.getString(Constants.JSON_NAME);
			address = profileDetails.getString(Constants.JSON_ADDRESS);
			email = profileDetails.getString(Constants.JSON_EMAIL);
			city = profileDetails.getString(Constants.JSON_CITY);
			state = profileDetails.getString(Constants.JSON_STATE);
			service_starttime = profileDetails.getString(Constants.JSON_STARTTIME);
			service_endtime = profileDetails.getString(Constants.JSON_ENDTIME);
			zipcode = profileDetails.getInt(Constants.JSON_ZIPCODE);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setCuisinesData( JSONArray cuisinesArray ){
		cuisines = new CuisinesItems[cuisinesArray.length()];
		JSONObject tempCuisine = null;
		for( int i = 0; i < cuisinesArray.length(); i++ ){
			try {
				tempCuisine = cuisinesArray.getJSONObject(i);
				cuisines[i] = new CuisinesItems(tempCuisine.getInt("cuisine_id"), tempCuisine.getString("cuisine_name"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setMenuData( JSONArray menuDataArray ){
		try {
			for(int i = 0; i < menuDataArray.length(); i++ ){
				addCategory(menuDataArray.getJSONObject(i));	
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addCategory(JSONObject categoryData ){
		CategoryItem item=null;
		try {
			item = new CategoryItem(categoryData.getInt(Constants.JSON_CATEGORYID), categoryData.getString(Constants.JSON_CATEGORYNAME));
			categories.put(categoryData.getInt(Constants.JSON_CATEGORYID), item);
			JSONArray menuItemsArray = categoryData.getJSONArray(Constants.JSON_CATEGORY_MENUITEMS);
			for(int i = 0; i < menuItemsArray.length(); i++ ){
				addMenuItem(menuItemsArray.getJSONObject(i));
			}
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
}
