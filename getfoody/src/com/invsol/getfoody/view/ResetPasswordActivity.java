package com.invsol.getfoody.view;

import com.invsol.getfoody.GetFoodyApplication;
import com.invsol.getfoody.R;
import com.invsol.getfoody.utils.TextValidator;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class ResetPasswordActivity extends ActionBarActivity{
	
	private EditText editText_otpcode, editText_newpassword, editText_confirmpassword;
	private boolean isOTPValid, isNewPasswordValid, isConfirmPasswordValid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resetpwd);
		
		editText_newpassword = (EditText) findViewById(R.id.edittext_reset_password_newpwd);
		editText_confirmpassword = (EditText) findViewById(R.id.edittext_reset_password_confirmpwd);
		editText_otpcode = (EditText) findViewById(R.id.edittext_reset_otpcode);
		
		editText_newpassword.setTypeface(Typeface.SANS_SERIF);
		editText_newpassword
				.setTransformationMethod(new PasswordTransformationMethod());
		
		editText_confirmpassword.setTypeface(Typeface.SANS_SERIF);
		editText_confirmpassword
				.setTransformationMethod(new PasswordTransformationMethod());
		
		editText_newpassword.addTextChangedListener(new TextValidator(
				editText_newpassword) {
			@Override
			public void validate(TextView textView, String text) {
				if (text != null && text.length() >= 6) {
					textView.setCompoundDrawablesWithIntrinsicBounds(
							null,
							null,
							getResources().getDrawable(
									R.drawable.ic_right),
							null);
					isNewPasswordValid = true;
				} else if (text != null && text.length() < 6) {
					textView.setCompoundDrawablesWithIntrinsicBounds(
							null,
							null,
							getResources().getDrawable(
									R.drawable.ic_cross),
							null);
					isNewPasswordValid = false;
				} else {
					textView.setCompoundDrawablesWithIntrinsicBounds(
							null,
							null,
							getResources().getDrawable(
									R.drawable.ic_device_access_accounts), null);
					isNewPasswordValid = false;
				}
			}
		});
		
		editText_confirmpassword.addTextChangedListener(new TextValidator(
				editText_confirmpassword) {
			@Override
			public void validate(TextView textView, String text) {
				if (text != null && text.length() >= 6) {
					textView.setCompoundDrawablesWithIntrinsicBounds(
							null,
							null,
							getResources().getDrawable(
									R.drawable.ic_right),
							null);
					isConfirmPasswordValid = true;
				} else if (text != null && text.length() < 6) {
					textView.setCompoundDrawablesWithIntrinsicBounds(
							null,
							null,
							getResources().getDrawable(
									R.drawable.ic_cross),
							null);
					isConfirmPasswordValid = false;
				} else {
					textView.setCompoundDrawablesWithIntrinsicBounds(
							null,
							null,
							getResources().getDrawable(
									R.drawable.ic_device_access_accounts), null);
					isConfirmPasswordValid = false;
				}
			}
		});
		
		editText_otpcode.addTextChangedListener(new TextValidator(
				editText_otpcode) {
			@Override
			public void validate(TextView textView, String text) {
				if (text != null && text.length() == 6) {
					textView.setCompoundDrawablesWithIntrinsicBounds(
							null,
							null,
							getResources().getDrawable(
									R.drawable.ic_right),
							null);
					isOTPValid = true;
				} else if (text != null && text.length() < 6) {
					textView.setCompoundDrawablesWithIntrinsicBounds(
							null,
							null,
							getResources().getDrawable(
									R.drawable.ic_cross),
							null);
					isOTPValid = false;
				} else {
					textView.setCompoundDrawablesWithIntrinsicBounds(
							null,
							null,
							getResources().getDrawable(
									R.drawable.ic_device_access_accounts), null);
					isOTPValid = false;
				}
			}
		});
		
		// Action on click of Forgot Password Button
				TextView btn_resetpwd_submit = (TextView) findViewById(R.id.btn_resetpwd_submit);
				btn_resetpwd_submit.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View view) {
						ResetPasswordActivity.this.finish();
					}
				});
	}
	
	@Override
	protected void onResume() {
	  super.onResume();
	  GetFoodyApplication.setCurrentActivity(this);
	  GetFoodyApplication.activityResumed();
	}

	@Override
	protected void onPause() {
	  super.onPause();
	  GetFoodyApplication.clearReferences();
	  GetFoodyApplication.activityPaused();
	}
}
