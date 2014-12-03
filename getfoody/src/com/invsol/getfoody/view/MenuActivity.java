package com.invsol.getfoody.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.invsol.getfoody.R;
import com.invsol.getfoody.adapters.MenuExpandableListAdapter;
import com.invsol.getfoody.adapters.MenuExpandableListAdapter.ExpandableListListener;
import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.controllers.AppEventsController;
import com.invsol.getfoody.dataobjects.CategoryItem;
import com.invsol.getfoody.dataobjects.MenuItem;
import com.invsol.getfoody.defines.NetworkEvents;
import com.invsol.getfoody.dialogs.AddCategoryDialog;
import com.invsol.getfoody.dialogs.AddCategoryDialog.AddCategoryDialogListener;
import com.invsol.getfoody.dialogs.AddMenuDialog;
import com.invsol.getfoody.dialogs.AddMenuDialog.AddMenuDialogListener;
import com.invsol.getfoody.listeners.ActivityUpdateListener;
import com.invsol.getfoody.models.ConnectionModel;

public class MenuActivity extends ActionBarActivity implements AddCategoryDialogListener, ActivityUpdateListener, ExpandableListListener, AddMenuDialogListener{

	private ExpandableListView expListView;
	private MenuExpandableListAdapter exlistAdapter;
	private List<String> listDataHeader;
	private HashMap<String, ArrayList<MenuItem>> listDataChild;
	private ConnectionModel connModel;
	private int groupPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		connModel = AppEventsController.getInstance().getModelFacade()
				.getConnModel();
		connModel.registerView(this);

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.expandableListView_menu);
		
		TextView btn_addcategory = (TextView)findViewById(R.id.btn_addcategory);
		btn_addcategory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Create an instance of the dialog fragment and show it
		        final DialogFragment dialog = new AddCategoryDialog();
		        dialog.show(getSupportFragmentManager(), "AddCategoryDialogFragment");
		        //((AlertDialog)dialog.getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
		        /*EditText editText_categoryname = (EditText)dialog.getDialog().findViewById(R.id.edittext_add_category);
		        editText_categoryname.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						if( count == 0 )
							((AlertDialog)dialog.getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
						else
							((AlertDialog)dialog.getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count, int after) {
					}
					
					@Override
					public void afterTextChanged(Editable s) {
					}
				});*/
			}
		});
		
		/*TextView btn_addmenuitem = (TextView)findViewById(R.id.btn_addmenuitem);
		btn_addmenuitem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Create an instance of the dialog fragment and show it
		        final DialogFragment dialog = new AddMenuDialog();
		        dialog.show(getSupportFragmentManager(), "AddMenuDialogFragment");
		        //((AlertDialog)dialog.getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
			}
		});*/
	}
	
/*	
	 * Preparing the list data
	 
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Top 250");
		listDataHeader.add("Now Showing");
		listDataHeader.add("Coming Soon..");

		// Adding child data
		List<String> top250 = new ArrayList<String>();
		top250.add("The Shawshank Redemption");
		top250.add("The Godfather");
		top250.add("The Godfather: Part II");
		top250.add("Pulp Fiction");
		top250.add("The Good, the Bad and the Ugly");
		top250.add("The Dark Knight");
		top250.add("12 Angry Men");

		List<String> nowShowing = new ArrayList<String>();
		nowShowing.add("The Conjuring");
		nowShowing.add("Despicable Me 2");
		nowShowing.add("Turbo");
		nowShowing.add("Grown Ups 2");
		nowShowing.add("Red 2");
		nowShowing.add("The Wolverine");

		List<String> comingSoon = new ArrayList<String>();
		comingSoon.add("2 Guns");
		comingSoon.add("The Smurfs 2");
		comingSoon.add("The Spectacular Now");
		comingSoon.add("The Canyons");
		comingSoon.add("Europa Report");

		listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
		listDataChild.put(listDataHeader.get(1), nowShowing);
		listDataChild.put(listDataHeader.get(2), comingSoon);
	}*/

	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		Dialog dialogView = dialog.getDialog();
		String categoryName = ((EditText)dialogView.findViewById(R.id.edittext_add_category)).getText().toString();

		Bundle eventData = new Bundle();
		JSONObject postData = new JSONObject();
		try {
			postData.put(Constants.JSON_CATEGORYNAME, categoryName);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//eventData.putInt(Constants.JSON_RESTAURANT_ID, AppEventsController.getInstance().getModelFacade().getResModel().getRestaurant_id());
		eventData.putString(Constants.JSON_RESTAURANT_ID, ""+3);
		eventData.putString(Constants.JSON_POST_DATA, postData.toString());
		AppEventsController.getInstance().handleEvent(
				NetworkEvents.EVENT_ID_POST_CATEGORY, eventData, expListView);
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		
	}

	@Override
	public void updateActivity(String tag) {
		if( tag.equals("CategoryAdd") ){
			switch(connModel.getConnectionStatus()){
			case ConnectionModel.SUCCESS:{
				if( AppEventsController.getInstance().getModelFacade().getLocalModel().getCategories().size() == 1){
					listDataHeader = new ArrayList<String>();
					listDataChild = new HashMap<String, ArrayList<MenuItem>>();
					SparseArray<CategoryItem> categories = AppEventsController.getInstance().getModelFacade().getLocalModel().getCategories();
					int catIndex = categories.keyAt(0);
					CategoryItem item = categories.get(catIndex); 
					listDataHeader.add(item.getCategory_name());
					//listDataChild.put(listDataHeader.get(0), new ArrayList<String>());
					exlistAdapter = new MenuExpandableListAdapter(this, listDataHeader, listDataChild, this);
					// setting list adapter
					expListView.setAdapter(exlistAdapter);
				}else{
					SparseArray<CategoryItem> categories = AppEventsController.getInstance().getModelFacade().getLocalModel().getCategories();
					int catIndex = categories.keyAt(categories.size()-1);
					CategoryItem item = categories.get(catIndex); 
					exlistAdapter.get_listDataHeader().add(item.getCategory_name());
					exlistAdapter.notifyDataSetChanged();
					exlistAdapter.notifyDataSetInvalidated();
				}
			}
			break;
			}
		}else if( tag.equals("MenuItemAdd") ){
			switch(connModel.getConnectionStatus()){
			case ConnectionModel.SUCCESS:{
				String groupHeader = (String)exlistAdapter.getGroup(groupPosition);
				ArrayList<MenuItem> newItem = AppEventsController.getInstance().getModelFacade().getLocalModel().getMenuItems().get(groupHeader);
				MenuItem menu = newItem.get(newItem.size() - 1);
				if( exlistAdapter.get_listDataChild().containsKey(groupHeader) ){
					ArrayList<MenuItem> childMenu = exlistAdapter.get_listDataChild().get(groupHeader);
					childMenu.add(menu);
					exlistAdapter.notifyDataSetChanged();
					exlistAdapter.notifyDataSetInvalidated();
				}else{
					exlistAdapter.get_listDataChild().put(groupHeader, newItem);
					exlistAdapter.notifyDataSetChanged();
					exlistAdapter.notifyDataSetInvalidated();
				}
			}
			break;
			}
		}
	}

	@Override
	public void onMenuDialogPositiveClick(DialogFragment dialog) {
		Dialog dialogView = dialog.getDialog();
		String itemName = ((EditText)dialogView.findViewById(R.id.edittext_itemname)).getText().toString();
		String itemPrice = ((EditText)dialogView.findViewById(R.id.edittext_itemprice)).getText().toString();
		RadioGroup radioButtonGroup = (RadioGroup)dialogView.findViewById(R.id.radiogroup_type);
		int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
		RadioButton radioButton = (RadioButton)radioButtonGroup.findViewById(radioButtonID);
		String type = radioButton.getText().toString();

		Bundle eventData = new Bundle();
		JSONObject postData = new JSONObject();
		try {
			postData.put(Constants.JSON_ITEMNAME, itemName);
			postData.put(Constants.JSON_ITEMPRICE, Integer.parseInt(itemPrice));
			postData.put(Constants.JSON_ITEMTYPE, type);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SparseArray<CategoryItem> categories = AppEventsController.getInstance().getModelFacade().getLocalModel().getCategories();
		int key = categories.keyAt(groupPosition);
		int catID = (categories.get(key)).getCategory_id();
		eventData.putString(Constants.JSON_CATEGORYID, ""+catID);
		eventData.putString(Constants.JSON_POST_DATA, postData.toString());
		AppEventsController.getInstance().handleEvent(
				NetworkEvents.EVENT_ID_POST_MENUITEM, eventData, expListView);
	}

	@Override
	public void onExpandableListGroupClick(int position, String tag) {
		groupPosition = position;
		if( tag.equals("Add") ){
			// Create an instance of the dialog fragment and show it
	        final DialogFragment dialog = new AddMenuDialog();
	        dialog.show(getSupportFragmentManager(), "AddMenuDialogFragment");
	        //((AlertDialog)dialog.getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
		}
	}

	@Override
	public void onExpandableListChildClick(String tag) {
		
	}

	@Override
	public void onMenuDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}
}
