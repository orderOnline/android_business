package com.invsol.getfoody.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.invsol.getfoody.R;

public class ProfileActivity extends ActionBarActivity{
	
	private Menu profileMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
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