package com.invsol.getfoody.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.invsol.getfoody.R;
import com.invsol.getfoody.dataobjects.MenuItem;
import com.invsol.getfoody.dialogs.AddCategoryDialog.AddCategoryDialogListener;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuExpandableListAdapter extends BaseExpandableListAdapter{
	
	private Context _context;
	private List<String> _listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, ArrayList<MenuItem>> _listDataChild;
	
	/* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface ExpandableListListener {
        public void onExpandableListGroupClick( int position, String tag );
        public void onExpandableListChildClick( String tag );
    }
    
    // Use this instance of the interface to deliver action events
    ExpandableListListener mListener;
	
	public MenuExpandableListAdapter(Context context, List<String> listDataHeader,
			HashMap<String, ArrayList<MenuItem>> listChildData, ExpandableListListener listener) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listChildData;
		this.mListener = listener;
	}

	@Override
	public int getGroupCount() {
		if(this._listDataHeader != null)
			return this._listDataHeader.size();
		return 0;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		if( this._listDataChild != null )
			return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
		return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		if( this._listDataHeader != null )
			return this._listDataHeader.get(groupPosition);
		return null;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		if( this._listDataChild != null )
			return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition);
		return null;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(final int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.exlist_categories, null);
			TextView lblListHeader = (TextView) convertView
					.findViewById(R.id.lblListHeader);
			lblListHeader.setTypeface(null, Typeface.BOLD);
			lblListHeader.setText(headerTitle.toUpperCase());
			
			ImageView addMenuItem = (ImageView) convertView
					.findViewById(R.id.imageView_catadd);
			addMenuItem.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					Log.d("expandale click", ">>>add>>>"+groupPosition);
					mListener.onExpandableListGroupClick( groupPosition, "Add");
				}
			});
			
			ImageView editCategory = (ImageView) convertView
					.findViewById(R.id.imageView_catedit);
			editCategory.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					Log.d("expandale click", ">>>edit");
					mListener.onExpandableListGroupClick( groupPosition, "Edit");
				}
			});
			
			ImageView deleteCategory = (ImageView) convertView
					.findViewById(R.id.imageView_catdelete);
			deleteCategory.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					Log.d("expandale click", ">>>delete");
					mListener.onExpandableListGroupClick( groupPosition, "Delete");
				}
			});
		}

		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final MenuItem child = (MenuItem) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.exlist_menuitems, null);
			TextView txtListChild = (TextView) convertView
					.findViewById(R.id.lblListItem_name);
			txtListChild.setText(child.getName());
			TextView txtListChildPrice = (TextView) convertView
					.findViewById(R.id.lblListItem_price);
			txtListChildPrice.setText(""+child.getPrice());
		}
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public List<String> get_listDataHeader() {
		return _listDataHeader;
	}

	public HashMap<String, ArrayList<MenuItem>> get_listDataChild() {
		return _listDataChild;
	}

	
}
