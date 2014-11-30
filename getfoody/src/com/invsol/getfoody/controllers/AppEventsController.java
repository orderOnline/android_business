package com.invsol.getfoody.controllers;

import com.invsol.getfoody.defines.NetworkEvents;
import com.invsol.getfoody.models.ModelFacade;
import com.invsol.getfoody.net.NetworkResponseHandler;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class AppEventsController {
	
	public static final String TAG = "Application Controller";
	/**
	 * Singleton Class Reference
	 */
	public static AppEventsController appController;
	/**
	 * ModelFacade Reference
	 */
	private ModelFacade modelFacade;

	// ---------------------------------------------------------------------------------

	/**
	 * Constructor
	 */
	private AppEventsController() {
		modelFacade = new ModelFacade();
	}

	// ---------------------------------------------------------------------------------

	/**
	 * Get Single instance of this class
	 * 
	 * @return ApplicationController single instance
	 */
	public static AppEventsController getInstance() {
		if (appController == null) {
			// creating new instance of application controller
			appController = new AppEventsController();
		}
		return appController;
	}

	// ---------------------------------------------------------------------------------

	/**
	 * Model Facade Reference
	 * 
	 * @return ModelFacade Reference
	 */
	public ModelFacade getModelFacade() {
		return modelFacade;
	}

	// ---------------------------------------------------------------------------------

	/**
	 * Handle Event Based on Event ID and Object
	 * 
	 * @param eventId
	 *            Event raised
	 * @param eventObjects
	 *            It stores the data for the given Event
	 */
	public void handleEvent(int eventId, Bundle eventData, View view) {
		fireEvents(eventId, eventData, view);
	}

	// ---------------------------------------------------------------------------------

	/**
	 * Method to actually handle events
	 */
	private void fireEvents(int eventId, Bundle eventData, View view) {
		switch (eventId) {
		case NetworkEvents.EVENT_ID_REGISTER: {
			try {
				modelFacade.getRemoteModel().registerUser(eventData,
						NetworkResponseHandler.REGISTERUSER_HANDLER, view);
			} catch (Exception ex) {
				Log.d("Application Exception:", ex.getMessage());
			}
		}

			break;
		case NetworkEvents.EVENT_ID_REGISTER_VALIDATEOTP: {
			try {
				modelFacade.getRemoteModel().registerUserValidateOTP(eventData,
						NetworkResponseHandler.REGISTERUSER_VALIDATEOTP_HANDLER, view);
			} catch (Exception ex) {
				Log.d("Application Exception:", ex.getMessage());
			}
		}

			break;
		case NetworkEvents.EVENT_ID_EDIT_PROFILE: {
			try {
				modelFacade.getRemoteModel().editProfile(eventData,
						NetworkResponseHandler.EDITPROFILE_HANDLER, view);
			} catch (Exception ex) {
				Log.d("Application Exception:", ex.getMessage());
			}
		}

			break;
		case NetworkEvents.EVENT_ID_LOGIN: {
			Log.d(TAG, "Creating Bundle");
			try {
				modelFacade.getRemoteModel().authenticateUser(eventData,
						NetworkResponseHandler.AUTHENTICATEUSER_HANDLER, view);
			} catch (Exception ex) {
				Log.d("Application Exception:", ex.getMessage());
			}
		}
			break;
		case NetworkEvents.EVENT_ID_GET_STATES:{
			try {
				modelFacade.getRemoteModel().getStates(eventData,
						NetworkResponseHandler.WHIZ_STATES_HANDLER, view);
			} catch (Exception ex) {
				Log.d("Application Exception:", ex.getMessage());
			}
		}
		break;
		case NetworkEvents.EVENT_ID_GET_CITIES:{
			try {
				modelFacade.getRemoteModel().getCities(eventData,
						NetworkResponseHandler.WHIZ_CITIES_HANDLER, view);
			} catch (Exception ex) {
				Log.d("Application Exception:", ex.getMessage());
			}
		}
		break;
		}
		
	}

	// ---------------------------------------------------------------------------------

}
