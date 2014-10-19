package com.invsol.getfoody.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.invsol.getfoody.R;
import com.invsol.getfoody.dataobjects.CuisinesItems;

public class CuisinesAdapter  extends ArrayAdapter<CuisinesItems> {

	Context context;
	int layoutResourceId;
	CuisinesItems[] cuisinesItem = null;
	ViewHolder holder = null;

	public CuisinesAdapter(Context context, int layoutResourceId,
			CuisinesItems[] objects) {
		super(context, layoutResourceId, objects);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.cuisinesItem = objects;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		//ViewHolder holder = null;

		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater layout_inflator = ((Activity) context)
					.getLayoutInflater();
			convertView = layout_inflator.inflate(R.layout.item_cuisine, null);
			holder.dataCell_cuisinetext = (TextView) convertView.findViewById(R.id.textview_cuisine);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
			
		}

		CuisinesItems item = cuisinesItem[position];
		holder.dataCell_cuisinetext.setText(item.getCuisineName());
			

		return convertView;
	}

	/**
	 * A class defining the view holder
	 */
	static class ViewHolder {
		private TextView dataCell_cuisinetext;
	}

}