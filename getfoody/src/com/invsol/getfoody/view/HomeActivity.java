package com.invsol.getfoody.view;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.invsol.getfoody.GetFoodyApplication;
import com.invsol.getfoody.R;
import com.invsol.getfoody.adapters.DrawerAdapter;
import com.invsol.getfoody.adapters.NewOrdersAdapter;
import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.controllers.AppEventsController;
import com.invsol.getfoody.dataobjects.CategoryItem;
import com.invsol.getfoody.dataobjects.NewOrderItems;
import com.invsol.getfoody.defines.NetworkEvents;
import com.invsol.getfoody.gcm.WakeLocker;
import com.invsol.getfoody.listeners.ActivityUpdateListener;
import com.invsol.getfoody.models.ConnectionModel;

public class HomeActivity extends ActionBarActivity implements ActivityUpdateListener {

	private ConnectionModel connModel;
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private String[] mDrawerTitles;
    private int[] mDrawerIcons;
    
    private ArrayList<NewOrderItems> orderItems = null;
    private NewOrdersAdapter adapter;
    private ListView list_newOrders;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		Log.d("HomeActivity", "I am here inside on create");

		connModel = AppEventsController.getInstance().getModelFacade()
				.getConnModel();
		connModel.registerView(this);
		
		mDrawerTitles = getResources().getStringArray(R.array.drawer_items_array);
		//mDrawerIcons = getResources().getIntArray(R.array.drawer_icons_array);
		TypedArray imgs = getResources().obtainTypedArray(R.array.drawer_icons_array);
		mDrawerIcons = new int[imgs.length()];
		//get resourceid by index
		for (int j = 0; j < imgs.length(); j++) {
			mDrawerIcons[j] = imgs.getResourceId(j, -1);
		}
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new DrawerAdapter(this, R.layout.activity_home, mDrawerTitles, mDrawerIcons));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }

        list_newOrders = (ListView)findViewById(R.id.listview_orders);
        if( orderItems == null ){
        	SparseArray<NewOrderItems> newItems = AppEventsController.getInstance().getModelFacade().getResModel().getOrderItems();
	        orderItems = new ArrayList<NewOrderItems>();
	        for( int i = 0; i < newItems.size(); i++){
	        	int index = newItems.keyAt(i);
	        	orderItems.add(newItems.get(index));
	        }
			adapter = new NewOrdersAdapter(
					this, R.layout.activity_home, orderItems);
			list_newOrders.setAdapter(adapter);
        }
        
        list_newOrders.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(HomeActivity.this,
						OrderDetailsActivity.class);
				HomeActivity.this.startActivity(screenChangeIntent);
			}
		});
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_menu_home, menu);

		return super.onCreateOptionsMenu(menu);
	}
	
	/* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.d("Search View", "Inside Options Selected");
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
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
	
	/* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	switch(position){
        	case Constants.HOME_LOGOUT:{
        		SharedPreferences prefs = getSharedPreferences(Constants.LOGIN_DATABASE_PREF_NAME,
    		            Context.MODE_PRIVATE);
    			Editor editor = prefs.edit();
    		    editor.putBoolean(Constants.LOGIN_STATUS, false);
    		    editor.commit(); 
    		    
    		    Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(HomeActivity.this,
						LoginActivity.class);
				HomeActivity.this.startActivity(screenChangeIntent);
				HomeActivity.this.finish();
        	}
        	break;
        	case Constants.HOME_TESTORDER:{
        		Bundle eventData = new Bundle();
    			JSONObject postData = new JSONObject();
    			try {
    				postData.put(Constants.JSON_RESTAURANT_ID, 20);
    				postData.put(Constants.JSON_CONSUMER_ID, 0);
    				postData.put(Constants.JSON_TIMESTAMP, SystemClock.currentThreadTimeMillis());
    				postData.put(Constants.JSON_INSTRUCTIONS, "Make Less Spicy.");
    				postData.put(Constants.JSON_ORDERTOTAL, 700);
    				
    				//MenuItems Details
    				JSONArray menuItems = new JSONArray();
    				JSONObject item = new JSONObject();
    				item.put(Constants.JSON_ITEMID, 17);
    				item.put(Constants.JSON_ITEMQTY, 1);
    				menuItems.put(item);
    				
    				JSONObject item2 = new JSONObject();
    				item2.put(Constants.JSON_ITEMID, 18);
    				item2.put(Constants.JSON_ITEMQTY, 1);
    				menuItems.put(item2);
    				
    				JSONObject item3 = new JSONObject();
    				item3.put(Constants.JSON_ITEMID, 20);
    				item3.put(Constants.JSON_ITEMQTY, 1);
    				menuItems.put(item3);
    				
    				postData.put(Constants.JSON_ORDER_ITEMS, menuItems);
    			} catch (JSONException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			eventData.putString(Constants.JSON_POST_DATA, postData.toString());
    			AppEventsController.getInstance().handleEvent(
    					NetworkEvents.EVENT_ID_POST_NEWORDER, eventData, view);
        	}
        	break;
        	}

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }

    private void selectItem(int position) {
        /*// update the main content by replacing fragments
        Fragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();*/
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
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

	@Override
	public void onBackPressed() {
		if(  mDrawerLayout.isDrawerOpen(mDrawerList) ){
			mDrawerLayout.closeDrawer(mDrawerList);
		}else
			super.onBackPressed();
	}
	
	@Override
	protected void onResume() {
		Log.d("HomeActivity", "Inside onResume");
	  super.onResume();
	  GetFoodyApplication.setCurrentActivity(this);
	  GetFoodyApplication.activityResumed();
	  
	  SparseArray<NewOrderItems> newItems = AppEventsController.getInstance().getModelFacade().getResModel().getRecentorderItems();
      for( int i = 0; i < newItems.size(); i++){
      	int index = newItems.keyAt(i);
      	adapter.getNewOrderItems().add(newItems.get(index));
      }
      adapter.notifyDataSetChanged();
      
      AppEventsController.getInstance().getModelFacade().getResModel().getRecentorderItems().clear();
	}

	@Override
	protected void onPause() {
	  super.onPause();
	  GetFoodyApplication.clearReferences();
	  GetFoodyApplication.activityPaused();
	}
}
