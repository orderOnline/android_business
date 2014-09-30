package com.invsol.getfoody.models;

import org.json.JSONArray;
import org.json.JSONObject;

import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.net.ConnectivityHandler;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;


public class RemoteModel {
	public static final String TAG = "Remote Model";
	
	public void getBrands(Bundle params, Handler listener, View view)
			throws Exception {
		ConnectivityHandler connHandler = new ConnectivityHandler(
				view.getContext());
		if (connHandler.isOnline()) {
			JSONObject obj = new JSONObject();
			JSONArray resArr = new JSONArray();
			
			obj.put("id", 1);
			obj.put("name", "Puma");
			obj.put("username", "testpuma@puma.com");
			obj.put("password_digest", "$2a$10$pacM7QwDLR/4juKchgZBGusZnpvxYi.eayoUMwzYCnExfQGIrYJVm");
			obj.put("email", "testpuma@puma.com");
			obj.put("created_at", "2014-07-15T06:05:29.456Z");
			obj.put("updated_at", "2014-07-15T06:05:29.456Z");
			obj.put("rating", 0);
			obj.put("location_id", 1);
			obj.put("store_id", 1);
			resArr.put(obj);
			
			obj = null;
			obj = new JSONObject();
			obj.put("id", 1);
			obj.put("name", "Adidas");
			obj.put("username", "testadidas@adidas.com");
			obj.put("password_digest", "$2a$10$pacM7QwDLR/4juKchgZBGusZnpvxYi.eayoUMwzYCnExfQGIrYJVm");
			obj.put("email", "testadidas@adidas.com");
			obj.put("created_at", "2014-07-15T06:05:29.456Z");
			obj.put("updated_at", "2014-07-15T06:05:29.456Z");
			obj.put("rating", 0);
			obj.put("location_id", 2);
			obj.put("store_id", 2);
			resArr.put(obj);
			
			listener.sendMessage(listener.obtainMessage(
					Constants.SUCCESSFUL_RESPONSE, resArr));
			/*HttpParams httpParams = new HttpParams();
			httpParams.setRequestURL(Constants.BASE_URL
					+ Constants.URL_GET_BRANDS_REQUEST);
			httpParams.setRequestMethod(HttpParams.HTTP_GET);

			NetworkAsyncTask asyncTask = new NetworkAsyncTask(
					view.getContext(), "Connecting...", listener);
			asyncTask.execute(httpParams);*/
		} else {
			listener.sendMessage(listener.obtainMessage(Constants.EXCEPTION,
					Constants.ERROR_NETWORK_PROBLEM));
		}
	}
	// --------------------------------------------------------------------------------------------------------
	
	public void getCategories(Bundle params, Handler listener, View view)
			throws Exception {
		ConnectivityHandler connHandler = new ConnectivityHandler(
				view.getContext());
		if (connHandler.isOnline()) {
			JSONObject obj = new JSONObject();
			JSONArray resArr = new JSONArray();
			
			obj.put("id", 1);
			obj.put("name", "Clothes");
			obj.put("created_at", "2014-07-15T06:05:29.456Z");
			obj.put("updated_at", "2014-07-15T06:05:29.456Z");
			resArr.put(obj);
			
			listener.sendMessage(listener.obtainMessage(
					Constants.SUCCESSFUL_RESPONSE, resArr));
			/*HttpParams httpParams = new HttpParams();
			httpParams.setRequestURL(Constants.BASE_URL
					+ Constants.URL_GET_CATEGORIES_REQUEST);
			httpParams.setRequestMethod(HttpParams.HTTP_GET);

			NetworkAsyncTask asyncTask = new NetworkAsyncTask(
					view.getContext(), "Connecting...", listener);
			asyncTask.execute(httpParams);*/
		} else {
			listener.sendMessage(listener.obtainMessage(Constants.EXCEPTION,
					Constants.ERROR_NETWORK_PROBLEM));
		}
	}
	// --------------------------------------------------------------------------------------------------------
	
	public void authenticateStore(Bundle params, Handler listener, View view)
			throws Exception {
		ConnectivityHandler connHandler = new ConnectivityHandler(
				view.getContext());
		if (connHandler.isOnline()) {
			JSONObject obj = new JSONObject();
			obj.put("access_token", "f764835fd2c4cf90a8ddd7a10a78a71babe4c2263446641cd809f1c3a4d6dd32");
			obj.put("token_type", "bearer");
			obj.put("expires_in", 604800);
			
			listener.sendMessage(listener.obtainMessage(
					Constants.SUCCESSFUL_RESPONSE, obj));
			/*HttpParams httpParams = new HttpParams();
			httpParams.setRequestURL(Constants.BASE_URL
					+ Constants.URL_GET_ACCESSTOKEN_REQUEST);
			httpParams.setRequestMethod(HttpParams.HTTP_POST);

			String requestData = CommonUtils.createPostdata(params);
			httpParams.setRequestData(requestData);
			Log.v(TAG, "Request Data=====>" + requestData);

			NetworkAsyncTask asyncTask = new NetworkAsyncTask(
					view.getContext(), "Connecting...", listener);
			asyncTask.execute(httpParams);*/
		} else {
			listener.sendMessage(listener.obtainMessage(Constants.EXCEPTION,
					Constants.ERROR_NETWORK_PROBLEM));
		}
	}
	// --------------------------------------------------------------------------------------------------------
	
	public void registerStore(Bundle params,Handler listener,View view) throws Exception
	{
		ConnectivityHandler connHandler = new ConnectivityHandler(view.getContext());
		if(connHandler.isOnline())
		{
			JSONObject obj = new JSONObject();
			obj.put("id", 1);
			obj.put("name", "Rachna");
			obj.put("username", "rachna@khokhar.com");
			obj.put("password_digest", "$2a$10$pacM7QwDLR/4juKchgZBGusZnpvxYi.eayoUMwzYCnExfQGIrYJVm");
			obj.put("email", "rachna@khokhar.com");
			obj.put("credits", 100);
			obj.put("current_location_longitude", 37.68455);
			obj.put("current_location_latitude", 97.34110);
			obj.put("created_at", "2014-07-15T06:05:29.456Z");
			obj.put("updated_at", "2014-07-15T06:05:29.456Z");
			
			listener.sendMessage(listener.obtainMessage(
					Constants.SUCCESSFUL_RESPONSE, obj));
			/*HttpParams httpParams = new HttpParams();
			httpParams.setRequestURL(Constants.BASE_URL + Constants.URL_REGISTERUSER_REQUEST);
			httpParams.setRequestMethod(HttpParams.HTTP_POST);
			
			String requestData = CommonUtils.createPostdata(params);
			httpParams.setRequestData(requestData);
			Log.v(TAG, "Request Data=====>" + requestData);
			
			NetworkAsyncTask asyncTask = new NetworkAsyncTask(view.getContext(), "Connecting", listener);
			asyncTask.execute(httpParams);*/
		}
		else
		{
			listener.sendMessage(listener.obtainMessage(Constants.EXCEPTION, Constants.ERROR_NETWORK_PROBLEM));
		}		
	}
}