package com.invsol.getfoody.utils;

import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.controllers.AppEventsController;

import android.os.Bundle;
import android.util.Log;

public class CommonUtils {
	public static final String createPostdata( Bundle params ){
		Set<String> keySet = params.keySet();
		Iterator<String> keyIterator = keySet.iterator();
		String keyVal = null;
		String requestData = new String();
		while (keyIterator.hasNext()) {
			keyVal = keyIterator.next();
			requestData += keyVal;
			requestData += "=";
			requestData += params.getString(keyVal);
			if( keyIterator.hasNext() )
				requestData += "&";
		}
		return requestData;
	}
	
	public static JSONObject convertSMSDataToJSON( String orderSMS ){
		JSONObject smsJson = new JSONObject();
		//smsJson.put(Constants.JSON_ORDER_ID, value);
		String[] orderDetails = orderSMS.split(",");
		for( int i = 0; i < orderDetails.length; i++ ){
			Log.d("orderdetails", orderDetails[i]);
		}
		try {
			smsJson.put(Constants.JSON_ORDER_ID, orderDetails[0]);
			smsJson.put(Constants.JSON_ADDRESS, orderDetails[1]);
			JSONArray itemsArray = new JSONArray();
			JSONObject tempJson = new JSONObject();
			for(int i = 2; i < orderDetails.length; i++ ){
				String[] item = orderDetails[i].split(":");
				tempJson.put(Constants.JSON_ITEMID, item[0]);
				tempJson.put(Constants.JSON_ITEMQTY, item[1]);
				
				String itemName = AppEventsController.getInstance().getModelFacade().getResModel().getMenuItemNames().get(Integer.parseInt(item[0]));
				tempJson.put(Constants.JSON_ITEMNAME, itemName);
				itemsArray.put(tempJson);
			}
			smsJson.put(Constants.JSON_ORDER_ITEMS, itemsArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d("sms json", smsJson.toString());
		return smsJson;
	}
}
