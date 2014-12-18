package com.invsol.getfoody.view;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;

import com.invsol.getfoody.GetFoodyApplication;
import com.invsol.getfoody.R;
import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.controllers.AppEventsController;
import com.invsol.getfoody.dataobjects.NewOrderItems;
import com.invsol.getfoody.defines.NetworkEvents;
import com.invsol.getfoody.dialogs.DeclineOrderDialog;
import com.invsol.getfoody.dialogs.DeclineOrderDialog.DeclineOrderDialogListener;
import com.invsol.getfoody.dialogs.ExpectedDeliveryTimeDialog;
import com.invsol.getfoody.dialogs.ExpectedDeliveryTimeDialog.ExpectedDeliveryDialogListener;
import com.invsol.getfoody.fragments.CardBackFragment;
import com.invsol.getfoody.fragments.CardFrontFragment;
import com.invsol.getfoody.listeners.ActivityUpdateListener;
import com.invsol.getfoody.models.ConnectionModel;

public class OrdersActivity extends Activity implements FragmentManager.OnBackStackChangedListener,
		CardFrontFragment.OnFrontCardOrderSelectedListener, CardBackFragment.OnBackCardOrderSelectedListener,
		ExpectedDeliveryDialogListener, DeclineOrderDialogListener, ActivityUpdateListener {

	/**
	 * Whether or not we're showing the back of the card (otherwise showing the
	 * front).
	 */
	private boolean mShowingBack = false;
	/**
	 * A handler object, used for deferring UI operations.
	 */
	private Handler mHandler = new Handler();

	private String orderData;

	private SparseArray<NewOrderItems> newItems;
	
	private ConnectionModel connModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		
		connModel = AppEventsController.getInstance().getModelFacade()
				.getConnModel();
		connModel.registerView(this);

		orderData = getIntent().getStringExtra("ORDER");

		if (savedInstanceState == null) {
			// If there is no saved instance state, add a fragment representing
			// the
			// front of the card to this activity. If there is saved instance
			// state,
			// this fragment will have already been added to the activity.
			getFragmentManager().beginTransaction().add(R.id.container, new CardFrontFragment(orderData), "Front")
					.commit();
		} else {
			mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
		}

		// Monitor back stack changes to ensure the action bar shows the
		// appropriate
		// button (either "photo" or "info").
		getFragmentManager().addOnBackStackChangedListener(this);
	}

	private void flipCard() {

		newItems = AppEventsController.getInstance().getModelFacade().getResModel().getPendingOrderItems();
		int key = newItems.keyAt(0);
		orderData = newItems.get(key).getOrderJson();

		if (mShowingBack) {
			CardFrontFragment frontFragment = (CardFrontFragment) getFragmentManager().findFragmentByTag("Front");
			frontFragment.setOrderData(orderData);
			getFragmentManager().popBackStack();
			return;
		}

		// Flip to the back.

		mShowingBack = true;

		// Create and commit a new fragment transaction that adds the fragment
		// for the back of
		// the card, uses custom animations, and is part of the fragment
		// manager's back stack.

		getFragmentManager().beginTransaction()

		// Replace the default fragment animations with animator resources
		// representing
		// rotations when switching to the back of the card, as well as animator
		// resources representing rotations when flipping back to the front
		// (e.g. when
		// the system Back button is pressed).
				.setCustomAnimations(R.animator.card_flip_right_in, R.animator.card_flip_right_out,
						R.animator.card_flip_left_in, R.animator.card_flip_left_out)

				// Replace any fragments currently in the container view with a
				// fragment
				// representing the next page (indicated by the just-incremented
				// currentPage
				// variable).
				.replace(R.id.container, new CardBackFragment(orderData), "Back")

				// Add this transaction to the back stack, allowing users to
				// press Back
				// to get to the front of the card.
				.addToBackStack(null)

				// Commit the transaction.
				.commit();

		// Defer an invalidation of the options menu (on modern devices, the
		// action bar). This
		// can't be done immediately because the transaction may not yet be
		// committed. Commits
		// are asynchronous in that they are posted to the main thread's message
		// loop.
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				invalidateOptionsMenu();
			}
		});
	}

	@Override
	public void onBackStackChanged() {
		mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);

		// When the back stack changes, invalidate the options menu (action
		// bar).
		// invalidateOptionsMenu();
	}

	@Override
	protected void onResume() {
		super.onResume();
		GetFoodyApplication.activityResumed();
		GetFoodyApplication.setCurrentActivity(this);
		// Clear all notification
		NotificationManager nMgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		nMgr.cancelAll();
	}

	@Override
	protected void onPause() {
		super.onPause();
		GetFoodyApplication.clearReferences();
		GetFoodyApplication.activityPaused();
	}

	@Override
	public void onFrontCardOrderSelected(String orderStatus, View view) {
		if( orderStatus.equals(Constants.JSON_ORDER_STATUS_ACCEPTED) ){
			// Create an instance of the dialog fragment and show it
			final DialogFragment dialog = new ExpectedDeliveryTimeDialog();
			dialog.show(getFragmentManager(), "ExpectedDeliveryDialogFragment");
		}else if( orderStatus.equals(Constants.JSON_ORDER_STATUS_DECLINED) ){
			// Create an instance of the dialog fragment and show it
			final DialogFragment dialog = new DeclineOrderDialog();
			dialog.show(getFragmentManager(), "DeclineOrderDialogFragment");
		}
	}

	@Override
	public void onBackCardOrderSelected(String orderStatus, View view) {
		if( orderStatus.equals(Constants.JSON_ORDER_STATUS_ACCEPTED) ){
			// Create an instance of the dialog fragment and show it
			final DialogFragment dialog = new ExpectedDeliveryTimeDialog();
			dialog.show(getFragmentManager(), "ExpectedDeliveryDialogFragment");
		}else if( orderStatus.equals(Constants.JSON_ORDER_STATUS_DECLINED) ){
			// Create an instance of the dialog fragment and show it
			final DialogFragment dialog = new DeclineOrderDialog();
			dialog.show(getFragmentManager(), "DeclineOrderDialogFragment");
		}
	}

	@Override
	public void onDODialogPositiveClick(DialogFragment dialog) {
		SparseArray<NewOrderItems> newItems = AppEventsController.getInstance().getModelFacade().getResModel()
				.getPendingOrderItems();
		if (newItems.size() > 0) {
			flipCard();
		} else {
			// Clear all notification
			NotificationManager nMgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			nMgr.cancelAll();
			Intent screenChangeIntent = null;
			screenChangeIntent = new Intent(OrdersActivity.this, HomeActivity.class);
			OrdersActivity.this.startActivity(screenChangeIntent);
			OrdersActivity.this.finish();
		}
	}

	@Override
	public void onEDDialogPositiveClick(DialogFragment dialog) {
		Dialog dialogView = dialog.getDialog();
		EditText editTextDeliveryTime = (EditText)dialogView.findViewById(R.id.edittext_delivery_time);
		String deliveryTime = editTextDeliveryTime.getText().toString();
		
		Bundle eventData = new Bundle();
		JSONObject postData = new JSONObject();
		try {
			JSONObject order = new JSONObject(orderData);
			postData.put(Constants.JSON_DELIVERYTIME, Integer.parseInt(deliveryTime));
			postData.put(Constants.JSON_CONSUMER_ID, order.getInt(Constants.JSON_CONSUMER_ID));
			eventData.putString(Constants.JSON_POST_DATA, postData.toString());
			eventData.putString(Constants.JSON_ORDER_ID, ""+order.getInt(Constants.JSON_ORDER_ID));
			AppEventsController.getInstance().handleEvent(
					NetworkEvents.EVENT_ID_ACCEPT_ORDER, eventData, editTextDeliveryTime);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateActivity(String tag) {
		if( tag.equals("AcceptOrder") ){
			SparseArray<NewOrderItems> newItems = AppEventsController.getInstance().getModelFacade().getResModel()
					.getPendingOrderItems();
			if (newItems.size() > 0) {
				flipCard();
			} else {
				// Clear all notification
				NotificationManager nMgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				nMgr.cancelAll();
				Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(OrdersActivity.this, HomeActivity.class);
				OrdersActivity.this.startActivity(screenChangeIntent);
				OrdersActivity.this.finish();
			}
		}
	}
}
