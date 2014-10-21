package com.invsol.getfoody.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.invsol.getfoody.R;

public class DrawerAdapter extends ArrayAdapter<String>{
	
	Context context;
	int layoutResourceId;
	int[] icons = null;
	ViewHolder holder = null;
	String[] items = null;

	public DrawerAdapter(Context context, int layoutResourceId,
			String[] items, int[] icons) {
		super(context, layoutResourceId, items);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.icons = icons;
		this.items = items;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		//ViewHolder holder = null;

		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater layout_inflator = ((Activity) context)
					.getLayoutInflater();
			convertView = layout_inflator.inflate(R.layout.drawer_list_item, null);
			holder.dataCell_text = (TextView) convertView.findViewById(R.id.drawer_item);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.dataCell_text.setText(items[position]);
		holder.dataCell_text.setCompoundDrawablesWithIntrinsicBounds(convertView.getResources().getDrawable(icons[position]), null, null, null);
			

		return convertView;
	}

	/**
	 * A class defining the view holder
	 */
	static class ViewHolder {
		private TextView dataCell_text;
	}

}
