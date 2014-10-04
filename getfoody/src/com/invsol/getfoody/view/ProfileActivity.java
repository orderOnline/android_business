package com.invsol.getfoody.view;

import com.invsol.getfoody.R;
import com.invsol.getfoody.adapters.ProfilePagerAdapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar.Tab;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class ProfileActivity extends ActionBarActivity implements
		ActionBar.TabListener {

	private ViewPager viewPager;
	private ProfilePagerAdapter mAdapter;
	private ActionBar actionBar;
	private Menu profileMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		// Initilization
		viewPager = (ViewPager) findViewById(R.id.profilepager);
		actionBar = getSupportActionBar();
		mAdapter = new ProfilePagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Resources itemTexts = getResources();
		String[] tabs = itemTexts.getStringArray(R.array.profile_tabs_array);

		// Adding Tabs
		for (String tab_name : tabs) {
			Tab newTab = actionBar.newTab();
			newTab.setText(tab_name);
			newTab.setTabListener(this);
			actionBar.addTab(newTab);
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

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// on tab selected
		/*
		 * // show respected fragment view MenuItem item =
		 * profileMenu.findItem(R.id.action_profile); switch (tab.getPosition())
		 * { case 0: { item.setIcon(R.drawable.ic_action_content_edit);
		 * item.setTitle(R.string.text_profile_edit_menu); } break; case 1: {
		 * item.setVisible(false); }
		 * 
		 * break; case 2: { item.setIcon(R.drawable.ic_action_content_save);
		 * item.setTitle(R.string.text_profile_savepreferences_menu); }
		 * 
		 * break; default: break; }
		 */
		viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { MenuInflater
	 * inflater = getMenuInflater();
	 * inflater.inflate(R.menu.activity_profile_menu, menu); this.profileMenu =
	 * menu; return super.onCreateOptionsMenu(menu); }
	 * 
	 * Called whenever we call invalidateOptionsMenu()
	 * 
	 * @Override public boolean onPrepareOptionsMenu(Menu menu) {
	 * this.profileMenu = menu; return super.onPrepareOptionsMenu(menu); }
	 * 
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { // Handle
	 * presses on the action bar items switch (item.getItemId()) { case
	 * R.id.action_profile: { Intent screenChangeIntent = null;
	 * screenChangeIntent = new Intent(ProfileActivity.this,
	 * EditProfileActivity.class);
	 * ProfileActivity.this.startActivity(screenChangeIntent); return true; }
	 * default: return super.onOptionsItemSelected(item); } }
	 */
}