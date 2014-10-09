package com.invsol.getfoody.view;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.invsol.getfoody.R;
import com.invsol.getfoody.adapters.HomePagerAdapter;
import com.invsol.getfoody.controllers.AppEventsController;
import com.invsol.getfoody.defines.NetworkEvents;
import com.invsol.getfoody.listeners.ActivityUpdateListener;
import com.invsol.getfoody.models.ConnectionModel;

public class HomeActivity extends ActionBarActivity implements
		ActionBar.TabListener, ActivityUpdateListener {

	private ViewPager viewPager;
	private ActionBar actionBar;
	private HomePagerAdapter mAdapter;
	private ConnectionModel connModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getSupportActionBar();
		mAdapter = new HomePagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		connModel = AppEventsController.getInstance().getModelFacade()
				.getConnModel();
		connModel.registerView(this);

		Resources itemTexts = getResources();
		String[] tabs = itemTexts.getStringArray(R.array.home_tabs_array);

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_menu_home, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.d("Search View", "Inside Options Selected");
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (super.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_profile: {
			Intent screenChangeIntent = null;
			screenChangeIntent = new Intent(HomeActivity.this,
					ProfileActivity.class);
			HomeActivity.this.startActivity(screenChangeIntent);
			return true;
		}
		case R.id.action_menu: {
			Intent screenChangeIntent = null;
			screenChangeIntent = new Intent(HomeActivity.this,
					MenuActivity.class);
			HomeActivity.this.startActivity(screenChangeIntent);
			return true;
		}
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	
	@Override
	public void updateActivity(String tag) {
		switch (connModel.getConnectionStatus()) {
		case ConnectionModel.SUCCESS: {
			Log.d("LoginActivity", "Inside onConnection");
			
		}
			break;
		case ConnectionModel.ERROR: {

		}
			break;
		}
	}

}
