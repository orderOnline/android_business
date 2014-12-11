package com.invsol.getfoody.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseArray;
import android.view.View;

import com.invsol.getfoody.R;
import com.invsol.getfoody.controllers.AppEventsController;
import com.invsol.getfoody.dataobjects.NewOrderItems;
import com.invsol.getfoody.fragments.CardBackFragment;
import com.invsol.getfoody.fragments.CardFrontFragment;

public class OrdersActivity extends Activity implements FragmentManager.OnBackStackChangedListener, CardFrontFragment.OnFrontCardOrderSelectedListener, CardBackFragment.OnBackCardOrderSelectedListener{
	
	/**
     * Whether or not we're showing the back of the card (otherwise showing the front).
     */
    private boolean mShowingBack = false;
    /**
     * A handler object, used for deferring UI operations.
     */
    private Handler mHandler = new Handler();
    
    private String orderData;
    
	private SparseArray<NewOrderItems> newItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		
		orderData = getIntent().getStringExtra("ORDER");
		
		if (savedInstanceState == null) {
            // If there is no saved instance state, add a fragment representing the
            // front of the card to this activity. If there is saved instance state,
            // this fragment will have already been added to the activity.
			getFragmentManager().beginTransaction().add(R.id.container, new CardFrontFragment(orderData), "Front").commit();
        } else {
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        }

        // Monitor back stack changes to ensure the action bar shows the appropriate
        // button (either "photo" or "info").
		getFragmentManager().addOnBackStackChangedListener(this);
	}
	
	private void flipCard(String orderStatus) {
		
		newItems = AppEventsController.getInstance().getModelFacade().getResModel().getPendingOrderItems();
        int key = newItems.keyAt(0);
		orderData = newItems.get(key).getOrderJson();
		
		
        if (mShowingBack) {
        	CardFrontFragment frontFragment = (CardFrontFragment)getFragmentManager().findFragmentByTag("Front");
        	frontFragment.setOrderData(orderData);
        	getFragmentManager().popBackStack();
            return;
        }

        // Flip to the back.

        mShowingBack = true;

        // Create and commit a new fragment transaction that adds the fragment for the back of
        // the card, uses custom animations, and is part of the fragment manager's back stack.

        getFragmentManager()
                .beginTransaction()

                // Replace the default fragment animations with animator resources representing
                // rotations when switching to the back of the card, as well as animator
                // resources representing rotations when flipping back to the front (e.g. when
                // the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)

                // Replace any fragments currently in the container view with a fragment
                // representing the next page (indicated by the just-incremented currentPage
                // variable).
                .replace(R.id.container, new CardBackFragment(orderData), "Back")

                // Add this transaction to the back stack, allowing users to press Back
                // to get to the front of the card.
                .addToBackStack(null)

                // Commit the transaction.
                .commit();

        // Defer an invalidation of the options menu (on modern devices, the action bar). This
        // can't be done immediately because the transaction may not yet be committed. Commits
        // are asynchronous in that they are posted to the main thread's message loop.
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

        // When the back stack changes, invalidate the options menu (action bar).
        //invalidateOptionsMenu();
	}

	@Override
	public void onFrontCardOrderSelected(String orderStatus, View view) {
		SparseArray<NewOrderItems> newItems = AppEventsController.getInstance().getModelFacade().getResModel().getPendingOrderItems();
		if( newItems.size() > 0 ){
			flipCard(orderStatus);
			/*int key = newItems.keyAt(0);
			Intent intent = getIntent();
			intent.putExtra("ORDER", newItems.get(key).getOrderJson());
			finish();
			startActivity(intent);*/
		}else{
			Intent screenChangeIntent = null;
			screenChangeIntent = new Intent(OrdersActivity.this,
					HomeActivity.class);
			OrdersActivity.this.startActivity(screenChangeIntent);
			OrdersActivity.this.finish();
		}
	}

	@Override
	public void onBackCardOrderSelected(String orderStatus, View view) {
		SparseArray<NewOrderItems> newItems = AppEventsController.getInstance().getModelFacade().getResModel().getPendingOrderItems();
		if( newItems.size() > 0 ){
			flipCard(orderStatus);
			/*int key = newItems.keyAt(0);
			Intent intent = getIntent();
			intent.putExtra("ORDER", newItems.get(key).getOrderJson());
			finish();
			startActivity(intent);*/
		}else{
			Intent screenChangeIntent = null;
			screenChangeIntent = new Intent(OrdersActivity.this,
					HomeActivity.class);
			OrdersActivity.this.startActivity(screenChangeIntent);
			OrdersActivity.this.finish();
		}
	}
}
