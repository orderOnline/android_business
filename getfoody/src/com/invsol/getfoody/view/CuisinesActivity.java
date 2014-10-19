package com.invsol.getfoody.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.invsol.getfoody.R;
import com.invsol.getfoody.adapters.CuisinesAdapter;
import com.invsol.getfoody.dataobjects.CuisinesItems;

public class CuisinesActivity extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cuisines);
		
		ListView listView_reviews_items = (ListView) findViewById(R.id.listview_cuisines);

		CuisinesItems[] cuisinesDataItems = new CuisinesItems[10];
		for (int i = 0; i < 10; i++) {
			cuisinesDataItems[i] = new CuisinesItems("dummy text");
		}

		CuisinesAdapter adapter = new CuisinesAdapter(
				this, R.layout.activity_cuisines, cuisinesDataItems);
		listView_reviews_items.setAdapter(adapter);
		
		TextView btn_next = (TextView)findViewById(R.id.btn_next);
		btn_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(CuisinesActivity.this,
						MenuActivity.class);
				CuisinesActivity.this.startActivity(screenChangeIntent);
			}
		});

	}
}
