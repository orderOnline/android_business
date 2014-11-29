package com.invsol.getfoody.view;

import org.json.JSONException;
import org.json.JSONObject;

import com.invsol.getfoody.R;
import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.controllers.AppEventsController;
import com.invsol.getfoody.defines.NetworkEvents;
import com.invsol.getfoody.listeners.ActivityUpdateListener;
import com.invsol.getfoody.models.ConnectionModel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class FillRestaurantDetailsActivity extends ActionBarActivity implements ActivityUpdateListener{
	
	private ConnectionModel connModel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_details);
		
		connModel = AppEventsController.getInstance().getModelFacade()
				.getConnModel();
		connModel.registerView(this);
		
		TextView btn_next = (TextView)findViewById(R.id.btn_next);
		btn_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				requestConnection(view);
				/*Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(FillRestaurantDetailsActivity.this,
						LegalActivity.class);
				FillRestaurantDetailsActivity.this.startActivity(screenChangeIntent);*/
			}
		});
		
		
		TextView mon_btn = (TextView)findViewById(R.id.textview_profile_alphabet_mon);
		mon_btn.setOnClickListener(new ClosedOnClickListener());
		TextView tue_btn = (TextView)findViewById(R.id.textview_profile_alphabet_tue);
		tue_btn.setOnClickListener(new ClosedOnClickListener());
		TextView wed_btn = (TextView)findViewById(R.id.textview_profile_alphabet_wed);
		wed_btn.setOnClickListener(new ClosedOnClickListener());
		TextView thur_btn = (TextView)findViewById(R.id.textview_profile_alphabet_thur);
		thur_btn.setOnClickListener(new ClosedOnClickListener());
		TextView fri_btn = (TextView)findViewById(R.id.textview_profile_alphabet_fri);
		fri_btn.setOnClickListener(new ClosedOnClickListener());
		TextView sat_btn = (TextView)findViewById(R.id.textview_profile_alphabet_sat);
		sat_btn.setOnClickListener(new ClosedOnClickListener());
		TextView sun_btn = (TextView)findViewById(R.id.textview_profile_alphabet_sun);
		sun_btn.setOnClickListener(new ClosedOnClickListener());
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		View view = getCurrentFocus();
		boolean ret = super.dispatchTouchEvent(event);

		if (view instanceof EditText) {
			View w = getCurrentFocus();
			int scrcoords[] = new int[2];
			w.getLocationOnScreen(scrcoords);
			float x = event.getRawX() + w.getLeft() - scrcoords[0];
			float y = event.getRawY() + w.getTop() - scrcoords[1];

			if (event.getAction() == MotionEvent.ACTION_UP
					&& (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w
							.getBottom())) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(getWindow().getCurrentFocus()
						.getWindowToken(), 0);
			}
		}
		return ret;
	}
	
	private void requestConnection(View view) {
		String restaurant_name = ((EditText) findViewById(R.id.edittext_profile_restaurantname)).getText().toString();
		String phonenumber = ((EditText) findViewById(R.id.edittext_profile_phonenumber)).getText().toString();
		String email = ((EditText) findViewById(R.id.edittext_profile_email)).getText().toString();
		String start_time = ((EditText) findViewById(R.id.edittext_profile_service_starttime)).getText().toString();
		String end_time = ((EditText) findViewById(R.id.edittext_profile_service_endtime)).getText().toString();
		String address = ((EditText) findViewById(R.id.edittext_profile_address)).getText().toString();
		String pincode = ((EditText) findViewById(R.id.edittext_profile_pincode)).getText().toString();
	}

	@Override
	public void updateActivity(String tag) {
		// TODO Auto-generated method stub
		
	}
	
	private class ClosedOnClickListener implements OnClickListener{

		@Override
		public void onClick(View view) {
			if(view.isPressed() && !view.isSelected()){
				view.setBackgroundColor(getResources().getColor(R.color.alphabet_pressed_background));
				view.setSelected(true);
			}else if(view.isPressed() && view.isSelected()) {
				view.setBackgroundColor(getResources().getColor(R.color.alphabet_normal_background));
				view.setSelected(false);
			}
		}
		
	}

}
