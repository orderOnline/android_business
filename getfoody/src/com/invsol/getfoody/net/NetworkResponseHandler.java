package com.invsol.getfoody.net;

import org.json.JSONArray;
import org.json.JSONObject;

import com.offonb.R;
import com.offonb.constants.Constants;
import com.offonb.controllers.AppEventsController;
import com.offonb.defines.NetworkEvents;
import com.offonb.exceptions.ApplicationException;
import com.offonb.models.ConnectionModel;
import com.offonb.models.LocalModel;
import com.offonb.models.UserModel;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class NetworkResponseHandler {
	public static final String TAG = "Network Response Handler";

	public static final Handler AUTHENTICATESTORE_HANDLER = authenticateStoreHandler();
	public static final Handler REGISTERSTORE_HANDLER = registerStoreHandler();
	public static final Handler BRANDS_HANDLER = brandsHandler();
	public static final Handler CATEGORIES_HANDLER = categoriesHandler();
	
	private static Handler authenticateStoreHandler() {
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
	
	private static Handler brandsHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ConnectionModel connmodel = AppEventsController.getInstance()
						.getModelFacade().getConnModel();
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					JSONArray response = (JSONArray)msg.obj; 
					Log.d("response==", response.toString());
					LocalModel localModel = AppEventsController
							.getInstance().getModelFacade()
							.getLocalModel();
					try {
						if( response.length() > 0 ){
							localModel.setBrands(response);
						}else{
							connmodel.setConnectionStatus(ConnectionModel.ERROR);
							connmodel.setConnectionErrorMessage("No Data Found.");
						}
					} catch (ApplicationException appEx) {
						// TODO Auto-generated catch block
						appEx.printStackTrace();
					}
					connmodel.notifyView("Brands");
				}
					break;
				case Constants.EXCEPTION: {
					Exception exceptionObj = (Exception) msg.obj;
					Log.d(TAG, "exception:" + exceptionObj.getMessage());
					connmodel.setConnectionStatus(ConnectionModel.ERROR);
					connmodel.setConnectionErrorMessage(exceptionObj.getMessage());
					connmodel.notifyView("Error");
				}
					break;
				}
			}

		};
	}
	
	private static Handler categoriesHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ConnectionModel connmodel = AppEventsController.getInstance()
						.getModelFacade().getConnModel();
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					JSONArray response = (JSONArray)msg.obj; 
					Log.d("response==", response.toString());
					LocalModel localModel = AppEventsController
							.getInstance().getModelFacade()
							.getLocalModel();
					try {
						if( response.length() > 0 ){
							localModel.setCategories(response);
						}else{
							connmodel.setConnectionStatus(ConnectionModel.ERROR);
							connmodel.setConnectionErrorMessage("No Data Found.");
						}
					} catch (ApplicationException appEx) {
						// TODO Auto-generated catch block
						appEx.printStackTrace();
					}
					connmodel.notifyView("Categories");
				}
					break;
				case Constants.EXCEPTION: {
					Exception exceptionObj = (Exception) msg.obj;
					Log.d(TAG, "exception:" + exceptionObj.getMessage());
					connmodel.setConnectionStatus(ConnectionModel.ERROR);
					connmodel.setConnectionErrorMessage(exceptionObj.getMessage());
					connmodel.notifyView("Error");
				}
					break;
				}
			}

		};
	}
	
	private static Handler registerStoreHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ConnectionModel model = AppEventsController.getInstance()
						.getModelFacade().getConnModel();
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					UserModel userModel = AppEventsController
							.getInstance().getModelFacade()
							.getUserModel();
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
