package com.invsol.getfoody.models;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.invsol.getfoody.R;
import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.net.ConnectivityHandler;
import com.invsol.getfoody.net.HttpParams;
import com.invsol.getfoody.net.NetworkAsyncTask;
import com.invsol.getfoody.utils.CommonUtils;


public class RemoteModel {
	public static final String TAG = "Remote Model";
	
	public void loginUser(Bundle params, Handler listener, View view)
			throws Exception {
		/*JSONObject obj = new JSONObject();
		obj.put("phonenumber", "9876543210");
		
		listener.sendMessage(listener.obtainMessage(
				Constants.SUCCESSFUL_RESPONSE, obj));*/
		ConnectivityHandler connHandler = new ConnectivityHandler(
				view.getContext());
		if (connHandler.isOnline()) {
			HttpParams httpParams = new HttpParams();
			httpParams.setRequestURL(Constants.BASE_URL
					+ Constants.URL_POST_LOGIN_REQUEST);
			httpParams.setRequestMethod(HttpParams.HTTP_POST);

			//String requestData = CommonUtils.createPostdata(params);
			String requestData = params.getString(Constants.JSON_POST_DATA);
			httpParams.setRequestData(requestData);
			Log.v(TAG, "Request Data=====>" + requestData);

			NetworkAsyncTask asyncTask = new NetworkAsyncTask(
					view.getContext(), "Connecting...", listener, true);
			asyncTask.execute(httpParams);
		} else {
			listener.sendMessage(listener.obtainMessage(Constants.EXCEPTION,
					view.getResources().getString(
							R.string.error_no_network_connection)));
		}
	}
	// --------------------------------------------------------------------------------------------------------
	
	public void registerUser(Bundle params,Handler listener,View view) throws Exception
	{
		ConnectivityHandler connHandler = new ConnectivityHandler(view.getContext());
		if(connHandler.isOnline())
		{
			HttpParams httpParams = new HttpParams();
			httpParams.setRequestURL(Constants.BASE_URL + Constants.URL_POST_REGISTER_REQUEST);
			httpParams.setRequestMethod(HttpParams.HTTP_POST);
			
			//String requestData = CommonUtils.createPostdata(params);
			String requestData = params.getString(Constants.JSON_POST_DATA);
			httpParams.setRequestData(requestData);
			Log.v(TAG, "Request Data=====>" + requestData);
			
			NetworkAsyncTask asyncTask = new NetworkAsyncTask(view.getContext(), "Connecting", listener, true);
			asyncTask.execute(httpParams);
		}
		else
		{
			listener.sendMessage(listener.obtainMessage(Constants.EXCEPTION, view.getResources().getString(
					R.string.error_no_network_connection)));
		}		
	}
	
	public void registerUserValidateOTP(Bundle params,Handler listener,View view) throws Exception
	{
		ConnectivityHandler connHandler = new ConnectivityHandler(view.getContext());
		if(connHandler.isOnline())
		{
			HttpParams httpParams = new HttpParams();
			httpParams.setRequestURL(Constants.BASE_URL + Constants.URL_POST_VALIDATEOTP_REQUEST);
			httpParams.setRequestMethod(HttpParams.HTTP_POST);
			
			//String requestData = CommonUtils.createPostdata(params);
			String requestData = params.getString(Constants.JSON_POST_DATA);
			httpParams.setRequestData(requestData);
			Log.v(TAG, "Request Data=====>" + requestData);
			
			NetworkAsyncTask asyncTask = new NetworkAsyncTask(view.getContext(), "Connecting", listener, true);
			asyncTask.execute(httpParams);
		}
		else
		{
			listener.sendMessage(listener.obtainMessage(Constants.EXCEPTION, view.getResources().getString(
					R.string.error_no_network_connection)));
		}		
	}
	
	public void editProfile(Bundle params,Handler listener,View view) throws Exception
	{
		ConnectivityHandler connHandler = new ConnectivityHandler(view.getContext());
		if(connHandler.isOnline())
		{
			HttpParams httpParams = new HttpParams();
			httpParams.setRequestURL(Constants.BASE_URL + Constants.URL_POST_PROFILE_REQUEST + params.getString(Constants.JSON_RESTAURANT_ID) + ".json");
			httpParams.setRequestMethod(HttpParams.HTTP_PUT);
			
			//String requestData = CommonUtils.createPostdata(params);
			String requestData = params.getString(Constants.JSON_POST_DATA);
			httpParams.setRequestData(requestData);
			Log.v(TAG, "Request Data=====>" + requestData);
			
			NetworkAsyncTask asyncTask = new NetworkAsyncTask(view.getContext(), "Connecting", listener, true);
			asyncTask.execute(httpParams);
		}
		else
		{
			listener.sendMessage(listener.obtainMessage(Constants.EXCEPTION, view.getResources().getString(
					R.string.error_no_network_connection)));
		}		
	}
	
	public void getStates(Bundle params,Handler listener,View view) throws Exception
	{
		ConnectivityHandler connHandler = new ConnectivityHandler(view.getContext());
		if(connHandler.isOnline())
		{
			HttpParams httpParams = new HttpParams();
			httpParams.setRequestURL(Constants.WHIZ_API_BASE_URL + Constants.WHIZ_API_GET_STATES_URL);
			httpParams.setRequestHeaders(Constants.WHIZ_JSON_APPKEY, Constants.APP_KEY);
			httpParams.setRequestMethod(HttpParams.HTTP_GET);
			
			NetworkAsyncTask asyncTask = new NetworkAsyncTask(view.getContext(), "Connecting", listener, false);
			asyncTask.execute(httpParams);
		}
		else
		{
			listener.sendMessage(listener.obtainMessage(Constants.EXCEPTION, view.getResources().getString(
					R.string.error_no_network_connection)));
		}		
	}
	
	public void getCities(Bundle params,Handler listener,View view) throws Exception
	{
		ConnectivityHandler connHandler = new ConnectivityHandler(view.getContext());
		if(connHandler.isOnline())
		{
			HttpParams httpParams = new HttpParams();
			httpParams.setRequestURL(Constants.WHIZ_API_BASE_URL + Constants.WHIZ_API_GET_CITIES_URL);
			httpParams.setRequestHeaders(Constants.WHIZ_JSON_STATE_ID, params.getString(Constants.WHIZ_JSON_STATE_ID));
			httpParams.setRequestHeaders(Constants.WHIZ_JSON_APPKEY, Constants.APP_KEY);
			httpParams.setRequestMethod(HttpParams.HTTP_GET);
			
			NetworkAsyncTask asyncTask = new NetworkAsyncTask(view.getContext(), "Connecting", listener, false);
			asyncTask.execute(httpParams);
		}
		else
		{
			listener.sendMessage(listener.obtainMessage(Constants.EXCEPTION, view.getResources().getString(
					R.string.error_no_network_connection)));
		}		
	}
	
	public void getCuisines(Bundle params,Handler listener,View view) throws Exception
	{
		ConnectivityHandler connHandler = new ConnectivityHandler(view.getContext());
		if(connHandler.isOnline())
		{
			HttpParams httpParams = new HttpParams();
			httpParams.setRequestURL(Constants.BASE_URL + Constants.URL_GET_CUISINES_REQUEST);
			httpParams.setRequestMethod(HttpParams.HTTP_GET);
			
			NetworkAsyncTask asyncTask = new NetworkAsyncTask(view.getContext(), "Connecting", listener, false);
			asyncTask.execute(httpParams);
		}
		else
		{
			listener.sendMessage(listener.obtainMessage(Constants.EXCEPTION, view.getResources().getString(
					R.string.error_no_network_connection)));
		}		
	}
	
	public void postCategory(Bundle params,Handler listener,View view) throws Exception
	{
		ConnectivityHandler connHandler = new ConnectivityHandler(view.getContext());
		if(connHandler.isOnline())
		{
			HttpParams httpParams = new HttpParams();
			httpParams.setRequestURL(Constants.BASE_URL + Constants.URL_POST_CATEGORY_REQUEST + params.getString(Constants.JSON_RESTAURANT_ID) + ".json");
			httpParams.setRequestMethod(HttpParams.HTTP_POST);
			//String requestData = CommonUtils.createPostdata(params);
			String requestData = params.getString(Constants.JSON_POST_DATA);
			httpParams.setRequestData(requestData);
			Log.v(TAG, "Request Data=====>" + requestData);
			
			NetworkAsyncTask asyncTask = new NetworkAsyncTask(view.getContext(), "Connecting", listener, true);
			asyncTask.execute(httpParams);
		}
		else
		{
			listener.sendMessage(listener.obtainMessage(Constants.EXCEPTION, view.getResources().getString(
					R.string.error_no_network_connection)));
		}		
	}
	
	public void postMenuItem(Bundle params,Handler listener,View view) throws Exception
	{
		ConnectivityHandler connHandler = new ConnectivityHandler(view.getContext());
		if(connHandler.isOnline())
		{
			HttpParams httpParams = new HttpParams();
			httpParams.setRequestURL(Constants.BASE_URL + Constants.URL_POST_MENUITEM_REQUEST + params.getString(Constants.JSON_CATEGORYID) + ".json");
			httpParams.setRequestMethod(HttpParams.HTTP_POST);
			//String requestData = CommonUtils.createPostdata(params);
			String requestData = params.getString(Constants.JSON_POST_DATA);
			httpParams.setRequestData(requestData);
			Log.v(TAG, "Request Data=====>" + requestData);
			
			NetworkAsyncTask asyncTask = new NetworkAsyncTask(view.getContext(), "Connecting", listener, true);
			asyncTask.execute(httpParams);
		}
		else
		{
			listener.sendMessage(listener.obtainMessage(Constants.EXCEPTION, view.getResources().getString(
					R.string.error_no_network_connection)));
		}		
	}
	
	public void postNewOrder(Bundle params,Handler listener,View view) throws Exception
	{
		ConnectivityHandler connHandler = new ConnectivityHandler(view.getContext());
		if(connHandler.isOnline())
		{
			HttpParams httpParams = new HttpParams();
			httpParams.setRequestURL(Constants.BASE_URL + Constants.URL_POST_TESTORDER_REQUEST);
			httpParams.setRequestMethod(HttpParams.HTTP_POST);
			String requestData = params.getString(Constants.JSON_POST_DATA);
			httpParams.setRequestData(requestData);
			Log.v(TAG, "Request Data=====>" + requestData);
			
			NetworkAsyncTask asyncTask = new NetworkAsyncTask(view.getContext(), "Connecting", listener, true);
			asyncTask.execute(httpParams);
		}
		else
		{
			listener.sendMessage(listener.obtainMessage(Constants.EXCEPTION, view.getResources().getString(
					R.string.error_no_network_connection)));
		}		
	}
	
	public void putAcceptOrder(Bundle params,Handler listener,View view) throws Exception
	{
		ConnectivityHandler connHandler = new ConnectivityHandler(view.getContext());
		if(connHandler.isOnline())
		{
			HttpParams httpParams = new HttpParams();
			httpParams.setRequestURL(Constants.BASE_URL + Constants.URL_PUT_ORDER_ACCEPT + params.getString(Constants.JSON_ORDER_ID) + ".json");
			httpParams.setRequestMethod(HttpParams.HTTP_PUT);
			String requestData = params.getString(Constants.JSON_POST_DATA);
			httpParams.setRequestData(requestData);
			Log.v(TAG, "Request Data=====>" + requestData);
			
			NetworkAsyncTask asyncTask = new NetworkAsyncTask(view.getContext(), "Connecting", listener, true);
			asyncTask.execute(httpParams);
		}
		else
		{
			listener.sendMessage(listener.obtainMessage(Constants.EXCEPTION, view.getResources().getString(
					R.string.error_no_network_connection)));
		}		
	}
}