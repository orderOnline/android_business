package com.invsol.getfoody.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.invsol.getfoody.R;
import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.controllers.AppEventsController;
import com.invsol.getfoody.defines.NetworkEvents;
import com.invsol.getfoody.listeners.ActivityUpdateListener;
import com.invsol.getfoody.models.ConnectionModel;
import com.invsol.getfoody.utils.TextValidator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends ActionBarActivity implements ActivityUpdateListener{
	
	private TextView btn_signin;
	private String username, password;
	private boolean keepMeLoggedInBool;
	private ConnectionModel connModel;
	private EditText editText_username, editText_password;
	private boolean isEmailValid, isPasswordValid;
	String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		editText_username = (EditText) findViewById(R.id.edittext_username);
		editText_password = (EditText) findViewById(R.id.edittext_password);
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

		editText_username
				.addTextChangedListener(new TextValidator(editText_username) {
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
		
		connModel = AppEventsController.getInstance().getModelFacade()
				.getConnModel();
		connModel.registerView(this);
		
		btn_signin = (TextView) findViewById(R.id.btn_login);
		btn_signin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				requestConnection(view);
			}
		});

		CheckBox keepMeLoggedIn = (CheckBox) findViewById(R.id.checkbox_rememberme);
		keepMeLoggedIn
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						keepMeLoggedInBool = isChecked;
					}
				});

		// Action on click of Forgot Password Button
		TextView textview_forgotpwd = (TextView) findViewById(R.id.btn_forgotpassword);
		textview_forgotpwd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(LoginActivity.this,
						ForgotPwdActivity.class);
				LoginActivity.this.startActivity(screenChangeIntent);
			}
		});
		
		// Action on click of Create An Account Button
		TextView textview_createaccount = (TextView) findViewById(R.id.btn_register);
		textview_createaccount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(LoginActivity.this,
						SignupActivity.class);
				LoginActivity.this.startActivity(screenChangeIntent);
			}
		});
		
		if( !AppEventsController.getInstance().getModelFacade().getLocalModel().isRetrievedMastedFilterData() ){
			AppEventsController.getInstance().handleEvent(NetworkEvents.EVENT_ID_GET_BRANDS,
				 null, textview_createaccount);
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
		username = editText_username.getText()
				.toString();
		password = editText_password.getText()
				.toString();

		if (isEmailValid && isPasswordValid) {
			Bundle eventData = new Bundle();
			eventData.putString(Constants.TEXT_GRANT_TYPE, Constants.TEXT_PASSWORD);
			eventData.putString(Constants.TEXT_USERNAME, username);
			eventData.putString(Constants.TEXT_PASSWORD, password);
			eventData.putString(Constants.TEXT_CLIENT_ID, Constants.OAUTH_CLIENT_ID);
			eventData.putString(Constants.TEXT_CLIENT_SECRET, Constants.OAUTH_CLIENT_SECRET);
			AppEventsController.getInstance().handleEvent(
					NetworkEvents.EVENT_ID_AUTHENTICATE_STORE, eventData, view);
		}
	}

	@Override
	public void updateActivity(String tag) {
		switch (connModel.getConnectionStatus()) {
		case ConnectionModel.SUCCESS: {
			Log.d("LoginActivity", "Inside onConnection");
			if(tag.equals("Brands")){
				AppEventsController.getInstance().handleEvent(NetworkEvents.EVENT_ID_GET_CATEGORIES,
						 null, btn_signin);
			}else if(tag.equals("Categories")){
				
			}else if(tag.equals("Login")){
				if (keepMeLoggedInBool) {
					SharedPreferences sharedPref = getSharedPreferences(
							Constants.DATABASE_PREF_NAME, MODE_PRIVATE);
					SharedPreferences.Editor editor = sharedPref.edit();
					editor.putString(Constants.TEXT_ACCESSTOKEN, AppEventsController
							.getInstance().getModelFacade().getUserModel()
							.getAccessToken());
					editor.commit();
				}
				connModel.unregisterView(this);
				Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(LoginActivity.this,
						HomeActivity.class);
				LoginActivity.this.startActivity(screenChangeIntent);
				LoginActivity.this.finish();
			}
		}
			break;
		case ConnectionModel.ERROR: {
			AlertDialog.Builder builder = new AlertDialog.Builder(
					LoginActivity.this);
			builder.setTitle(getResources().getString(R.string.error));
			builder.setMessage(connModel.getConnectionErrorMessage());
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
		}
			break;
		}
	}

}
