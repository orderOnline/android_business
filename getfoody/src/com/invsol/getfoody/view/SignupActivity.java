package com.invsol.getfoody.view;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.invsol.getfoody.R;
import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.controllers.AppEventsController;
import com.invsol.getfoody.defines.NetworkEvents;
import com.invsol.getfoody.listeners.ActivityUpdateListener;
import com.invsol.getfoody.models.ConnectionModel;
import com.invsol.getfoody.utils.TextValidator;

public class SignupActivity extends ActionBarActivity implements ActivityUpdateListener{
	
	private TextView btn_signup;
	private String phonenumber, password;
	private ConnectionModel connModel;
	private EditText editText_phoneno, editText_password;
	private boolean isPhoneNumberValid, isPasswordValid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		editText_phoneno = (EditText) findViewById(R.id.edittext_register_contactno);
		editText_password = (EditText) findViewById(R.id.edittext_register_password);
		editText_password.setTypeface(Typeface.SANS_SERIF);
		editText_password
				.setTransformationMethod(new PasswordTransformationMethod());
		
		editText_password.addTextChangedListener(new TextValidator(
				editText_password) {
			@Override
			public void validate(TextView textView, String text) {
				if (text != null && text.length() >= 6) {
					textView.setCompoundDrawablesWithIntrinsicBounds(
							null,
							null,
							getResources().getDrawable(
									R.drawable.ic_right),
							null);
					isPasswordValid = true;
				} else if (text != null && text.length() < 6) {
					textView.setCompoundDrawablesWithIntrinsicBounds(
							null,
							null,
							getResources().getDrawable(
									R.drawable.ic_cross),
							null);
					isPasswordValid = false;
				} else {
					textView.setCompoundDrawablesWithIntrinsicBounds(
							null,
							null,
							getResources().getDrawable(
									R.drawable.ic_device_access_accounts), null);
					isPasswordValid = false;
				}
			}
		});

		editText_phoneno
				.addTextChangedListener(new TextValidator(editText_phoneno) {
					@Override
					public void validate(TextView textView, String text) {
						if (text != null && text.length() == 10) {
							textView.setCompoundDrawablesWithIntrinsicBounds(
									null,
									null,
									getResources().getDrawable(
											R.drawable.ic_right),
									null);
							isPhoneNumberValid = true;
						} else if (text != null && (text.length() < 10 || text.length() > 10)) {
							textView.setCompoundDrawablesWithIntrinsicBounds(
									null,
									null,
									getResources().getDrawable(
											R.drawable.ic_cross),
									null);
							isPhoneNumberValid = false;
						} else {
							textView.setCompoundDrawablesWithIntrinsicBounds(
									null,
									null,
									getResources().getDrawable(
											R.drawable.ic_device_access_call), null);
							isPhoneNumberValid = false;
						}
					}
				});
		
		connModel = AppEventsController.getInstance().getModelFacade()
				.getConnModel();
		connModel.registerView(this);
		
		btn_signup = (TextView) findViewById(R.id.btn_submit);
		btn_signup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				//requestConnection(view);
				Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(SignupActivity.this,
						FillRestaurantDetailsActivity.class);
				SignupActivity.this.startActivity(screenChangeIntent);
			}
		});
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
		phonenumber = editText_phoneno.getText()
				.toString();
		password = editText_password.getText()
				.toString();
		if( (phonenumber == null || phonenumber.equals("")) || ( password == null || password.equals("")) ){
			AlertDialog.Builder builder = new AlertDialog.Builder(
					SignupActivity.this);
			builder.setTitle(getResources().getString(R.string.info));
			builder.setMessage(getResources().getString(R.string.text_login_empty_credentials));
			builder.setCancelable(false);
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
			if (isPhoneNumberValid && isPasswordValid) {
				Bundle eventData = new Bundle();
				JSONObject postData = new JSONObject();
				try {
					postData.put(Constants.JSON_PHONENUMBER, phonenumber);
					postData.put(Constants.JSON_PASSWORD, password);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				eventData.putString(Constants.JSON_POST_DATA, postData.toString());
				AppEventsController.getInstance().handleEvent(
						NetworkEvents.EVENT_ID_AUTHORIZE, eventData, view);
			}
		}
	}
	
	@Override
	public void updateActivity(String tag) {
		
	}
}
