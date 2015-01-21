package com.invsol.getfoody.net;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.controllers.AppEventsController;
import com.invsol.getfoody.dataobjects.NewOrderItems;
import com.invsol.getfoody.models.ConnectionModel;
import com.invsol.getfoody.models.RestaurantModel;

public class NetworkResponseHandler {
	public static final String TAG = "Network Response Handler";

	public static final Handler LOGINUSER_HANDLER = loginUserHandler();
	public static final Handler REGISTERUSER_HANDLER = registerUserHandler();
	public static final Handler REGISTERUSER_VALIDATEOTP_HANDLER = registerUserValidateOTPHandler();
	public static final Handler EDITPROFILE_HANDLER = editProfileHandler();
	public static final Handler WHIZ_STATES_HANDLER = whizStatesHandler();
	public static final Handler WHIZ_CITIES_HANDLER = whizCitiesHandler();
	public static final Handler CUISINES_HANDLER = cuisinesHandler();
	public static final Handler NEWCATEGORY_HANDLER = newcategoryHandler();
	public static final Handler NEWMENUITEM_HANDLER = newMenuItemHandler();
	public static final Handler NEWORDER_HANDLER = newOrderHandler();
	public static final Handler ACCEPTORDER_HANDLER = acceptOrderHandler();
	public static final Handler DECLINEORDER_HANDLER = declineOrderHandler();
	
	private static Handler loginUserHandler() {
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
						if( (resp.get(Constants.JSON_TYPE)).equals(Constants.JSON_SUCCESS) ){
							JSONObject restData = resp.getJSONObject(Constants.JSON_RESPONSE);
							AppEventsController.getInstance().getModelFacade().getResModel().setRestaurantProfileDetails(restData);
							AppEventsController.getInstance().getModelFacade().getResModel().setCuisinesData(restData.getJSONArray(Constants.JSON_CUISINES));
							if( (restData.getJSONArray(Constants.JSON_MENU)).length() > 0 )
								AppEventsController.getInstance().getModelFacade().getResModel().setMenuData(restData.getJSONArray(Constants.JSON_MENU));
							model.setConnectionStatus(ConnectionModel.SUCCESS);
							model.notifyView("Login");
						}
						
					} catch (JSONException e) {
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
	
	private static Handler acceptOrderHandler() {
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
						JSONObject restData = resp.getJSONObject(Constants.JSON_RESPONSE);
						NewOrderItems currentItem = AppEventsController.getInstance().getModelFacade().getResModel().getPendingOrderItems().get(restData.getInt(Constants.JSON_ORDER_ID));
						AppEventsController.getInstance().getModelFacade().getResModel().getPendingOrderItems().remove(restData.getInt(Constants.JSON_ORDER_ID));
						currentItem.setOrder_status(Constants.JSON_ORDER_STATUS_ACCEPTED);
						currentItem.setDeliveryTime(restData.getInt(Constants.JSON_DELIVERYTIME));
						AppEventsController.getInstance().getModelFacade().getResModel().addOrders(currentItem);
						model.setConnectionStatus(ConnectionModel.SUCCESS);
						model.notifyView("AcceptOrder");
					} catch (JSONException e) {
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
	
	private static Handler declineOrderHandler() {
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
						JSONObject restData = resp.getJSONObject(Constants.JSON_RESPONSE);
						NewOrderItems currentItem = AppEventsController.getInstance().getModelFacade().getResModel().getPendingOrderItems().get(restData.getInt(Constants.JSON_ORDER_ID));
						AppEventsController.getInstance().getModelFacade().getResModel().getPendingOrderItems().remove(restData.getInt(Constants.JSON_ORDER_ID));
						currentItem.setOrder_status(Constants.JSON_ORDER_STATUS_DECLINED);
						//currentItem.setDeliveryTime(restData.getInt(Constants.JSON_DELIVERYTIME));
						AppEventsController.getInstance().getModelFacade().getResModel().addOrders(currentItem);
						model.setConnectionStatus(ConnectionModel.SUCCESS);
						model.notifyView("DeclineOrder");
					} catch (JSONException e) {
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

	private static Handler newOrderHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ConnectionModel model = AppEventsController.getInstance()
						.getModelFacade().getConnModel();
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					Log.d("response==", ((JSONObject) msg.obj).toString());
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

	private static Handler newMenuItemHandler() {
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
						JSONObject restData = resp.getJSONObject(Constants.JSON_RESPONSE);
						AppEventsController.getInstance().getModelFacade().getLocalModel().addMenuItem(restData);
						model.setConnectionStatus(ConnectionModel.SUCCESS);
						model.notifyView("MenuItemAdd");
					} catch (JSONException e) {
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

	private static Handler newcategoryHandler() {
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
						JSONObject restData = resp.getJSONObject(Constants.JSON_RESPONSE);
						AppEventsController.getInstance().getModelFacade().getLocalModel().addCategory(restData);
						model.setConnectionStatus(ConnectionModel.SUCCESS);
						model.notifyView("CategoryAdd");
					} catch (JSONException e) {
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

	private static Handler cuisinesHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					Log.d("response==", ((JSONObject) msg.obj).toString());
					try {
						JSONObject resp = ((JSONObject) msg.obj).getJSONObject(Constants.JSON_RESULT);
						JSONArray restData = resp.getJSONArray(Constants.JSON_RESPONSE);
						AppEventsController.getInstance().getModelFacade().getLocalModel().setCuisinesData(restData);
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
					break;
				case Constants.EXCEPTION: {
					/*Exception exceptionObj = (Exception) msg.obj;
					Log.d(TAG, "exception:" + exceptionObj.getMessage());
					model.setConnectionStatus(ConnectionModel.ERROR);
					model.setConnectionErrorMessage(exceptionObj.getMessage());
					model.notifyView("Error");*/
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
						if( (resp.get(Constants.JSON_TYPE)).equals(Constants.JSON_SUCCESS) ){
							model.setConnectionStatus(ConnectionModel.SUCCESS);
							model.notifyView("EditProfile");
						}
						
					} catch (JSONException e) {
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
						String restData = resp.getString(Constants.JSON_RESPONSE);
						if( restData.equals(Constants.JSON_VALID_OTP_CODE) ){
							model.setConnectionStatus(ConnectionModel.SUCCESS);
							model.notifyView("OTPValid");
						}
						
					} catch (JSONException e) {
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
