package com.invsol.getfoody.view;

import com.invsol.getfoody.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CuisinesActivity extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cuisines);
		
		String[] cuisines = new String[5];
		ListView cuisines_listview = (ListView)findViewById(R.id.listview_cuisines);
		ArrayAdapter<String> cuisinesAdapter = new ArrayAdapter<String>(this, R.id.listview_cuisines, cuisines);
		cuisines_listview.setAdapter(cuisinesAdapter);
	}
}
