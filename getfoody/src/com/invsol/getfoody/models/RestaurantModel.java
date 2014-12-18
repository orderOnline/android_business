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
import com.invsol.getfoody.dataobjects.NewOrderItems;
import com.invsol.getfoody.dataobjects.OrderItems;

import android.util.Log;
import android.util.SparseArray;

public class RestaurantModel {
	private boolean isRestaurantLoggedIn;
	private String gcm_registration_key;
	private int restaurant_id, zipcode;
	private long phonenumber;
	private String name, email, address, city, state, service_starttime, service_endtime;
	private CuisinesItems[] cuisines;
	private SparseArray<CategoryItem> categories;
	private SparseArray<NewOrderItems> pendingorderItems;
	private SparseArray<NewOrderItems> orderItems;
	private SparseArray<NewOrderItems> recentorderItems;
	private HashMap<String, ArrayList<MenuItem>> menuItems;
	private SparseArray<String> menuItemNames;
	
	public RestaurantModel(){
		categories = new SparseArray<CategoryItem>();
		 menuItems = new HashMap<String, ArrayList<MenuItem>>();
		 pendingorderItems = new SparseArray<NewOrderItems>();
		 orderItems = new SparseArray<NewOrderItems>();
		 recentorderItems = new SparseArray<NewOrderItems>();
		 menuItemNames = new SparseArray<String>();
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

	public SparseArray<NewOrderItems> getPendingOrderItems() {
		return pendingorderItems;
	}

	public SparseArray<NewOrderItems> getOrderItems() {
		return orderItems;
	}

	public SparseArray<NewOrderItems> getRecentorderItems() {
		return recentorderItems;
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
			menuItemNames.put(menuitemData.getInt(Constants.JSON_ITEMID), menuitemData.getString(Constants.JSON_ITEMNAME));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addPendingOrderItem(JSONObject orderData ){
		NewOrderItems item=null;
		try {
			item = new NewOrderItems();
			item.setOrderJson(orderData.toString());
			item.setOrder_id(orderData.getInt(Constants.JSON_ORDER_ID));
			item.setCustomer_name(orderData.getString(Constants.JSON_ITEMNAME));
			item.setCustomer_address(orderData.getString(Constants.JSON_ADDRESS));
			item.setCustomer_extrainfo(orderData.getString(Constants.JSON_INSTRUCTIONS));
			item.setCustomer_phoneNumber(orderData.getLong(Constants.JSON_PHONENUMBER));
			item.setOrderBillAmount(orderData.getInt(Constants.JSON_ORDERTOTAL));
			item.setTimestamp(orderData.getString(Constants.JSON_TIMESTAMP));
			item.setOrder_status(Constants.JSON_ORDER_STATUS_PENDING);
			
			//Add Order Items
			JSONArray itemsArray = orderData.getJSONArray(Constants.JSON_ORDER_ITEMS);
			OrderItems[] orderIt = new OrderItems[itemsArray.length()];
			JSONObject tempOrder = null;
			for( int i = 0; i < itemsArray.length(); i++ ){
				tempOrder = itemsArray.getJSONObject(i);
				orderIt[i] = new OrderItems(tempOrder.getInt(Constants.JSON_ITEMID), tempOrder.getInt(Constants.JSON_ITEMQTY));
				orderIt[i].setOrder_item_name(tempOrder.getString(Constants.JSON_ITEMNAME));
			}
			item.setOrderItems(orderIt);
			pendingorderItems.put(orderData.getInt(Constants.JSON_ORDER_ID), item);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addOrders(NewOrderItems order){
		orderItems.put(order.getOrder_id(), order);
		recentorderItems.put(order.getOrder_id(), order);
	}

	public boolean isRestaurantLoggedIn() {
		return isRestaurantLoggedIn;
	}

	public void setRestaurantLoggedIn(boolean isRestaurantLoggedIn) {
		this.isRestaurantLoggedIn = isRestaurantLoggedIn;
	}

	public SparseArray<String> getMenuItemNames() {
		return menuItemNames;
	}
	
	
}
