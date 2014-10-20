package com.invsol.getfoody.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.invsol.getfoody.R;
import com.invsol.getfoody.adapters.MenuExpandableListAdapter;
import com.invsol.getfoody.dialogs.AddCategoryDialog;
import com.invsol.getfoody.dialogs.AddMenuDialog;

public class MenuActivity extends ActionBarActivity {

	private ExpandableListView expListView;
	private MenuExpandableListAdapter exlistAdapter;
	private List<String> listDataHeader;
	private HashMap<String, List<String>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.expandableListView_menu);

		// preparing list data
		prepareListData();

		exlistAdapter = new MenuExpandableListAdapter(this, listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(exlistAdapter);
		
		TextView btn_addcategory = (TextView)findViewById(R.id.btn_addcategory);
		btn_addcategory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Create an instance of the dialog fragment and show it
		        DialogFragment dialog = new AddCategoryDialog();
		        dialog.show(getSupportFragmentManager(), "AddCategoryDialogFragment");
			}
		});
		
		TextView btn_addmenuitem = (TextView)findViewById(R.id.btn_addmenuitem);
		btn_addmenuitem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Create an instance of the dialog fragment and show it
		        DialogFragment dialog = new AddMenuDialog();
		        dialog.show(getSupportFragmentManager(), "AddMenuDialogFragment");
			}
		});
	}
	
	/*
	 * Preparing the list data
	 */
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
	}
}
