package com.invsol.getfoody.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.invsol.getfoody.R;
import com.invsol.getfoody.controllers.AppEventsController;
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

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	public View getCustomView(final int position, View convertView, ViewGroup parent) {
		//ViewHolder holder = null;

		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater layout_inflator = ((Activity) context)
					.getLayoutInflater();
			convertView = layout_inflator.inflate(R.layout.item_cuisine, parent, false);
			holder.dataCell_cuisinetext = (TextView) convertView.findViewById(R.id.textview_cuisine);
			holder.dataCell_isChecked = (CheckBox)convertView.findViewById(R.id.checkbox_cuisine);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
			
		}

		CuisinesItems item = cuisinesItem[position];
		holder.dataCell_cuisinetext.setText(item.getCuisineName());
		holder.dataCell_isChecked.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				AppEventsController.getInstance().getModelFacade().getLocalModel().getCuisines()[position].setChecked(isChecked);
			}
		});
		return convertView;
	}

	/**
	 * A class defining the view holder
	 */
	static class ViewHolder {
		private TextView dataCell_cuisinetext;
		private CheckBox dataCell_isChecked;
	}

}