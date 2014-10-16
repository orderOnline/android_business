package com.invsol.getfoody.view;

import com.invsol.getfoody.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class FillRestaurantDetailsActivity extends ActionBarActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_details);
		
		TextView btn_next = (TextView)findViewById(R.id.btn_next);
		btn_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(FillRestaurantDetailsActivity.this,
						LegalActivity.class);
				FillRestaurantDetailsActivity.this.startActivity(screenChangeIntent);
			}
		});
	}

}
