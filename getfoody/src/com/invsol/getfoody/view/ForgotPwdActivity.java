package com.invsol.getfoody.view;

import com.invsol.getfoody.R;
import com.invsol.getfoody.utils.TextValidator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotPwdActivity extends ActionBarActivity{
	
	private EditText editText_phoneno;
	private boolean isPhoneNumberValid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgotpwd);
		
		editText_phoneno = (EditText) findViewById(R.id.edittext_forgotpwd_contactno);
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
				} else if (text != null && text.length() < 10) {
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
		
		// Action on click of Forgot Password Button
				TextView btn_forgotpwd_submit = (TextView) findViewById(R.id.btn_forgot_pwd_submit);
				btn_forgotpwd_submit.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View view) {
						Intent screenChangeIntent = null;
						screenChangeIntent = new Intent(ForgotPwdActivity.this,
								ResetPasswordActivity.class);
						ForgotPwdActivity.this.finish();
						ForgotPwdActivity.this.startActivity(screenChangeIntent);
					}
				});
	}
}
