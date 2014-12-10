package com.invsol.getfoody.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.invsol.getfoody.R;
import com.invsol.getfoody.adapters.CuisinesAdapter;
import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.controllers.AppEventsController;
import com.invsol.getfoody.dataobjects.CuisinesItems;
import com.invsol.getfoody.defines.NetworkEvents;
import com.invsol.getfoody.listeners.ActivityUpdateListener;
import com.invsol.getfoody.models.ConnectionModel;
import com.invsol.getfoody.utils.TextValidator;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
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
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class FillRestaurantDetailsActivity extends ActionBarActivity implements ActivityUpdateListener{
	
	private ConnectionModel connModel;
	private CuisinesAdapter cuisineAdapter;
	private boolean isEmailValid;
	String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
			}
		});
		EditText editeText_email = (EditText)findViewById(R.id.edittext_profile_email);
		editeText_email
		.addTextChangedListener(new TextValidator(editeText_email) {
			@Override
			public void validate(TextView textView, String text) {
				Pattern pattern = Pattern.compile(EMAIL_PATTERN);
				Matcher matcher = pattern.matcher(text);
				if (text != null && matcher.matches()) {
					textView.setCompoundDrawablesWithIntrinsicBounds(
							null,
							null,
							getResources().getDrawable(
									R.drawable.ic_right),
							null);
					isEmailValid = true;
				} else if (text != null && !matcher.matches()) {
					textView.setCompoundDrawablesWithIntrinsicBounds(
							null,
							null,
							getResources().getDrawable(
									R.drawable.ic_cross),
							null);
					isEmailValid = false;
				} else {
					textView.setCompoundDrawablesWithIntrinsicBounds(
							null,
							null,
							getResources().getDrawable(
									R.drawable.ic_content_email), null);
					isEmailValid = false;
				}
			}
		});
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner_profile_cuisines);
		cuisineAdapter = new CuisinesAdapter(this, R.layout.item_cuisine, AppEventsController.getInstance().getModelFacade().getLocalModel().getCuisines());
	    spinner.setAdapter(cuisineAdapter);
		
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
		
		Spinner state_spinner = (Spinner)findViewById(R.id.spinner_profile_state);
		state_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Bundle data = new Bundle();
				data.putString(Constants.WHIZ_JSON_STATE_ID, ""+(position+1));
				AppEventsController.getInstance().handleEvent(
						NetworkEvents.EVENT_ID_GET_CITIES, data, view);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		AppEventsController.getInstance().handleEvent(
				NetworkEvents.EVENT_ID_GET_STATES, null, state_spinner);
		
		//Code to show TimePicker Dialog On Click Of Edit Text
		final EditText service_starttime = (EditText)findViewById(R.id.edittext_profile_service_starttime);
		final EditText service_endtime = (EditText)findViewById(R.id.edittext_profile_service_endtime);
		final TimePickerDialog tpd = new TimePickerDialog(this,
		        new TimePickerDialog.OnTimeSetListener() {
		 
		            @Override
		            public void onTimeSet(TimePicker view, int hourOfDay,
		                    int minute) {
		            	Calendar mCalendar = Calendar.getInstance();
		                mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		                mCalendar.set(Calendar.MINUTE, minute);
		                mCalendar.set(Calendar.SECOND, 0);
		                String time = new SimpleDateFormat("HH:mm:ss").format(mCalendar.getTime());
		            	((EditText) getWindow().getCurrentFocus()).setText(time);
		            }
		        }, 11, 00, true);
		service_starttime.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if( hasFocus )
					tpd.show();
			}
		});
		service_endtime.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if( hasFocus )
					tpd.show();
			}
		});
		
		if(!AppEventsController.getInstance().getModelFacade().getLocalModel().isCuisinesDataReceived()){
			AppEventsController.getInstance().handleEvent(
					NetworkEvents.EVENT_ID_GET_CUISINES, null, service_endtime);
		}
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
		//String phonenumber = ((EditText) findViewById(R.id.edittext_profile_phonenumber)).getText().toString();
		String email = ((EditText) findViewById(R.id.edittext_profile_email)).getText().toString();
		String start_time = ((EditText) findViewById(R.id.edittext_profile_service_starttime)).getText().toString();
		String end_time = ((EditText) findViewById(R.id.edittext_profile_service_endtime)).getText().toString();
		String address = ((EditText) findViewById(R.id.edittext_profile_address)).getText().toString();
		String pincode = ((EditText) findViewById(R.id.edittext_profile_pincode)).getText().toString();
		String state = ((Spinner)findViewById(R.id.spinner_profile_state)).getSelectedItem().toString();
		String city = ((Spinner)findViewById(R.id.spinner_profile_city)).getSelectedItem().toString();
		LinearLayout closedonLayout = (LinearLayout)findViewById(R.id.closedon_layout);
		JSONArray closedOnArray = new JSONArray();
		for(int i = 0; i < 7; i++ ){
			Log.d("closedon>>>", ""+closedonLayout.getChildAt(i).isSelected());
			if( closedonLayout.getChildAt(i).isSelected() )
				closedOnArray.put(1);
			else
				closedOnArray.put(0);
		}
		
		CuisinesItems[] items = AppEventsController.getInstance().getModelFacade().getLocalModel().getCuisines();
		JSONArray cuisinesIDArray = null;
		int cuisinesSelectedCount = 0;
		for(int i = 0; i < items.length; i++ ){
			if( items[i].isChecked() )
				cuisinesSelectedCount++;
		}
		if( cuisinesSelectedCount > 0){
			cuisinesIDArray = new JSONArray();
			for(int i = 0; i < items.length; i++ ){
				Log.d("cuisines>>>", ""+items[i].isChecked());
				if( items[i].isChecked() ){
					cuisinesIDArray.put(items[i].getCuisineID());
				}
			}
		}
		
		
		if( (restaurant_name == null || restaurant_name.equals("")) ||
			( email == null || email.equals("")) || ( start_time == null || start_time.equals("")) ||
			( end_time == null || end_time.equals("")) || ( address == null || address.equals("")) ||
			( pincode == null || pincode.equals("")) || ( state == null || state.equals("")) || 
			( city == null || city.equals("")) || ( cuisinesIDArray == null || cuisinesSelectedCount < 1)){
			AlertDialog.Builder builder = new AlertDialog.Builder(
					FillRestaurantDetailsActivity.this);
			builder.setTitle(getResources().getString(R.string.info));
			builder.setMessage(getResources().getString(R.string.text_fillrestarantdetails_fields_mandatory));
			//builder.setCancelable(false);
			builder.setPositiveButton(getResources().getString(R.string.OK),
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
			AlertDialog alertDialog = builder.create();
			alertDialog.show();
		}else{
			if (isEmailValid) {
				Bundle eventData = new Bundle();
				JSONObject postData = new JSONObject();
				try {
					postData.put(Constants.JSON_NAME, restaurant_name);
					postData.put(Constants.JSON_EMAIL, email);
					postData.put(Constants.JSON_STARTTIME, start_time);
					postData.put(Constants.JSON_ENDTIME, end_time);
					postData.put(Constants.JSON_ADDRESS, address);
					postData.put(Constants.JSON_CITY, city.trim());
					postData.put(Constants.JSON_STATE, state.trim());
					postData.put(Constants.JSON_ZIPCODE, Integer.parseInt(pincode));
					postData.put(Constants.JSON_CUISINES, cuisinesIDArray);
					postData.put(Constants.JSON_CLOSEDON, closedOnArray);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				eventData.putString(Constants.JSON_RESTAURANT_ID, ""+AppEventsController.getInstance().getModelFacade().getResModel().getRestaurant_id());
				eventData.putString(Constants.JSON_POST_DATA, postData.toString());
				AppEventsController.getInstance().handleEvent(
						NetworkEvents.EVENT_ID_EDIT_PROFILE, eventData, view);
			}
		}
	}

	@Override
	public void updateActivity(String tag) {
		if( tag.equals("States") ){
			switch(connModel.getConnectionStatus()){
			case ConnectionModel.SUCCESS:{
				final Spinner spinner = (Spinner) findViewById(R.id.spinner_profile_state);
			    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			        android.R.layout.simple_spinner_item, AppEventsController.getInstance().getModelFacade().getLocalModel().getStatesNames());
			    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			    spinner.setAdapter(adapter);
			}
			break;
			}
		}else if( tag.equals("Cities") ){
			switch(connModel.getConnectionStatus()){
			case ConnectionModel.SUCCESS:{
				final Spinner spinner = (Spinner) findViewById(R.id.spinner_profile_city);
			    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			        android.R.layout.simple_spinner_item, AppEventsController.getInstance().getModelFacade().getLocalModel().getCitiesNames());
			    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			    spinner.setAdapter(adapter);
			}
			break;
			}
		}else if( tag.equals("EditProfile") ){
			switch(connModel.getConnectionStatus()){
			case ConnectionModel.SUCCESS:{
				connModel.unregisterAllView();
				Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(FillRestaurantDetailsActivity.this,
						LegalActivity.class);
				FillRestaurantDetailsActivity.this.startActivity(screenChangeIntent);
				FillRestaurantDetailsActivity.this.finish();
			}
			break;
			}
		}
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
