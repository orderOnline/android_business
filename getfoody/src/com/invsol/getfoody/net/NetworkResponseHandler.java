package com.invsol.getfoody.net;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.controllers.AppEventsController;
import com.invsol.getfoody.exceptions.ApplicationException;
import com.invsol.getfoody.models.ConnectionModel;
import com.invsol.getfoody.models.LocalModel;
import com.invsol.getfoody.models.RestaurantModel;
import com.invsol.getfoody.models.UserModel;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class NetworkResponseHandler {
	public static final String TAG = "Network Response Handler";

	public static final Handler AUTHENTICATEUSER_HANDLER = authenticateUserHandler();
	public static final Handler REGISTERUSER_HANDLER = registerUserHandler();
	public static final Handler REGISTERUSER_VALIDATEOTP_HANDLER = registerUserValidateOTPHandler();
	public static final Handler EDITPROFILE_HANDLER = editProfileHandler();
	public static final Handler WHIZ_STATES_HANDLER = whizStatesHandler();
	public static final Handler WHIZ_CITIES_HANDLER = whizCitiesHandler();
	
	private static Handler authenticateUserHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ConnectionModel model = AppEventsController.getInstance()
						.getModelFacade().getConnModel();
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					Log.d("response==", ((JSONObject) msg.obj).toString());
					UserModel userModel = AppEventsController
							.getInstance().getModelFacade()
							.getUserModel();
					try {
						userModel.setAuthenticationDetails((JSONObject) msg.obj);
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					model.setConnectionStatus(ConnectionModel.SUCCESS);
					model.notifyView("Login");
				}
					break;
				case Constants.EXCEPTION: {
					Exception exceptionObj = (Exception) msg.obj;
					Log.d(TAG, "exception:" + exceptionObj.getMessage());
					model.setConnectionStatus(ConnectionModel.ERROR);
					model.setConnectionErrorMessage(exceptionObj.getMessage());
					model.notifyView("Error");
				}
					break;
				}
			}

		};
	}
	
	private static Handler whizStatesHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ConnectionModel model = AppEventsController.getInstance()
						.getModelFacade().getConnModel();
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					Log.d("response==", ((JSONObject) msg.obj).toString());
					try {
						JSONObject stateResp = ((JSONObject) msg.obj);
						if( stateResp.getInt(Constants.WHIZ_JSON_RESPONSE_CODE) == 0 ){
							JSONArray resp = ((JSONObject) msg.obj).getJSONArray(Constants.WHIZ_JSON_DATA);
							AppEventsController.getInstance().getModelFacade().getLocalModel().setStatesData(resp);
							model.setConnectionStatus(ConnectionModel.SUCCESS);
							model.notifyView("States");
						}
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					break;
				case Constants.EXCEPTION: {
					Exception exceptionObj = (Exception) msg.obj;
					Log.d(TAG, "exception:" + exceptionObj.getMessage());
					model.setConnectionStatus(ConnectionModel.ERROR);
					model.setConnectionErrorMessage(exceptionObj.getMessage());
					model.notifyView("Error");
				}
					break;
				}
			}

		};
	}
	
	private static Handler whizCitiesHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ConnectionModel model = AppEventsController.getInstance()
						.getModelFacade().getConnModel();
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					Log.d("response==", ((JSONObject) msg.obj).toString());
					try {
						JSONObject stateResp = ((JSONObject) msg.obj);
						if( stateResp.getInt(Constants.WHIZ_JSON_RESPONSE_CODE) == 0 ){
							JSONArray resp = ((JSONObject) msg.obj).getJSONArray(Constants.WHIZ_JSON_DATA);
							AppEventsController.getInstance().getModelFacade().getLocalModel().setCitiesData(resp);
							model.setConnectionStatus(ConnectionModel.SUCCESS);
							model.notifyView("Cities");
						}
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					break;
				case Constants.EXCEPTION: {
					Exception exceptionObj = (Exception) msg.obj;
					Log.d(TAG, "exception:" + exceptionObj.getMessage());
					model.setConnectionStatus(ConnectionModel.ERROR);
					model.setConnectionErrorMessage(exceptionObj.getMessage());
					model.notifyView("Error");
				}
					break;
				}
			}

		};
	}

	private static Handler editProfileHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ConnectionModel model = AppEventsController.getInstance()
						.getModelFacade().getConnModel();
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					Log.d("response==", ((JSONObject) msg.obj).toString());
					try {
						JSONObject resp = ((JSONObject) msg.obj).getJSONObject(Constants.JSON_RESULT);
						JSONObject restData = (JSONObject)resp.getJSONObject(Constants.JSON_RESPONSE);
						if( (restData.get(Constants.JSON_VALID_OTP_CODE)).equals(Constants.JSON_VALID_OTP_CODE) ){
							model.setConnectionStatus(ConnectionModel.SUCCESS);
							model.notifyView("OTPValid");
						}
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					break;
				case Constants.EXCEPTION: {
					Exception exceptionObj = (Exception) msg.obj;
					Log.d(TAG, "exception:" + exceptionObj.getMessage());
					model.setConnectionStatus(ConnectionModel.ERROR);
					model.setConnectionErrorMessage(exceptionObj.getMessage());
					model.notifyView("Error");
				}
					break;
				}
			}

		};
	}

	private static Handler registerUserValidateOTPHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ConnectionModel model = AppEventsController.getInstance()
						.getModelFacade().getConnModel();
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					Log.d("response==", ((JSONObject) msg.obj).toString());
					try {
						JSONObject resp = ((JSONObject) msg.obj).getJSONObject(Constants.JSON_RESULT);
						JSONObject restData = (JSONObject)resp.getJSONObject(Constants.JSON_RESPONSE);
						if( (restData.get(Constants.JSON_VALID_OTP_CODE)).equals(Constants.JSON_VALID_OTP_CODE) ){
							model.setConnectionStatus(ConnectionModel.SUCCESS);
							model.notifyView("OTPValid");
						}
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					break;
				case Constants.EXCEPTION: {
					Exception exceptionObj = (Exception) msg.obj;
					Log.d(TAG, "exception:" + exceptionObj.getMessage());
					model.setConnectionStatus(ConnectionModel.ERROR);
					model.setConnectionErrorMessage(exceptionObj.getMessage());
					model.notifyView("Error");
				}
					break;
				}
			}

		};
	}

	private static Handler registerUserHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ConnectionModel model = AppEventsController.getInstance()
						.getModelFacade().getConnModel();
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					Log.d("response==", ((JSONObject) msg.obj).toString());
					RestaurantModel restModel = AppEventsController
							.getInstance().getModelFacade()
							.getResModel();
					try {
						JSONObject resp = ((JSONObject) msg.obj).getJSONObject(Constants.JSON_RESULT);
						JSONObject restData = (JSONObject)resp.getJSONObject(Constants.JSON_RESPONSE);
						restModel.setPhonenumber(restData.getLong(Constants.JSON_PHONENUMBER));
						restModel.setRestaurant_id(restData.getInt(Constants.JSON_RESTAURANT_ID));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					model.setConnectionStatus(ConnectionModel.SUCCESS);
					model.notifyView("Register");
				}
					break;
				case Constants.EXCEPTION: {
					Exception exceptionObj = (Exception) msg.obj;
					Log.d(TAG, "exception:" + exceptionObj.getMessage());
					model.setConnectionStatus(ConnectionModel.ERROR);
					model.setConnectionErrorMessage(exceptionObj.getMessage());
					model.notifyView("Error");
				}
					break;
				}
			}

		};
	}
}
