package com.invsol.getfoody.view;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

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

	}
}
