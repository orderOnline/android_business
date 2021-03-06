package com.invsol.getfoody.models;

import java.util.Vector;

import com.invsol.getfoody.listeners.ActivityUpdateListener;

public class BaseModel {
	public static final String TAG = "Base Model";
	/**
	 * Vector holding registered managers
	 */
	private Vector<ActivityUpdateListener> registeredView;

	// --------------------------------------------------------------------------------------------

	/**
	 * Constructor
	 */
	public BaseModel() {
		registeredView = new Vector<ActivityUpdateListener>();
	}

	// --------------------------------------------------------------------------------------------

	/**
	 * Register view which should be updated.
	 */
	public void registerView(ActivityUpdateListener view) {
		registeredView.addElement(view);
	}

	// --------------------------------------------------------------------------------------------

	/**
	 * UnRegister view which should be updated.
	 */
	public void unregisterView(ActivityUpdateListener view) {
		registeredView.removeElement(view);
	}

	// --------------------------------------------------------------------------------------------

	/**
	 * UnRegister view which should be updated.
	 */
	public void unregisterAllView() {
		registeredView.removeAllElements();
	}

	// --------------------------------------------------------------------------------------------

	/**
	 * Notify view which should be updated.
	 */
	public void notifyView(String tag) {
		int viewsCount = registeredView.size();
		ActivityUpdateListener view = null;
		for (int i = 0; i < viewsCount; i++) {
			view = (ActivityUpdateListener) registeredView.elementAt(i);
			view.updateActivity(tag);
		}
	}
	// --------------------------------------------------------------------------------------------

	public Vector<ActivityUpdateListener> getRegisteredView() {
		return registeredView;
	}
}